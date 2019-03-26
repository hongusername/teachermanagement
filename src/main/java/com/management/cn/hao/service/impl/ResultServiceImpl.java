package com.management.cn.hao.service.impl;

import com.alibaba.fastjson.JSON;
import com.management.cn.chen.dao.IClassesDao;
import com.management.cn.dto.ResultDTO;
import com.management.cn.dto.SurveyContentDTO;
import com.management.cn.dto.TeacherDTO;
import com.management.cn.entity.*;
import com.management.cn.hao.dao.ResultMapper;
import com.management.cn.hao.dao.SurveyContentMapper;
import com.management.cn.hao.dao.SurveyTypeMapper;
import com.management.cn.hao.service.ResultService;
import com.management.cn.hong.dao.TeacherDao;
import io.swagger.models.auth.In;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.thymeleaf.standard.expression.Each;
import sun.plugin.javascript.navig.Link;

import java.util.*;
import java.util.stream.Stream;

/**
 * @author: GengHao
 * @date: 2019-03-24 09:04
 */
@Service
public class ResultServiceImpl implements ResultService {

    @Autowired
    private SurveyTypeMapper surveyTypeMapper;
    @Autowired
    private ResultMapper resultMapper;
    @Autowired
    private SurveyContentMapper surveyContentMapper;
    @Autowired
    private IClassesDao classesDao;
    @Autowired
    private TeacherDao teacherDao;

    @Override
    public int addResult(ResultDTO resultDTO) {
        SurveyType surveyType = surveyTypeMapper.selectSurveyTypeById(resultDTO.getSurveyTypeId());
        String optionsJSON = surveyType.getOptions();
        List<OptionScore> optionScoreList = Arrays.asList(JSON.parseObject(optionsJSON, OptionScore[].class));
        //总分
        int totalScore = 0;
        //选中的选项 集合
        for (Map<String, String> checked : resultDTO.getCheckedList()) {
            if (checked.get("id").equals("0")) {
                continue;
            }
            //选中的选项
            String checkedOption = checked.get("option");
            //根据选项计算总分
            for (OptionScore option : optionScoreList) {
                if (checkedOption.equals(option.getIndex())) {
                    totalScore += Integer.parseInt(option.getScore());
                }
            }
        }
        Result result = new Result();
        //班级编号
        result.setClassid(resultDTO.getClassId());
        //总分
        result.setTotalScore(totalScore);
        //老师类型
        result.setTeacherType(resultDTO.getTeacherType());
        //创建日期
        result.setCreateDate(new Date());
        //调查问卷类型
        result.setSurveyType(resultDTO.getSurveyTypeId());
        //选项
        result.setOptions(JSON.toJSONString(resultDTO.getCheckedList()));
        //意见、建议
        result.setOpinion(resultDTO.getOpinion());
        return resultMapper.addResult(result);
    }

    @Override
    public Integer queryResultAVG(Result result) {
        List<Result> results = resultMapper.queryResultAll(result);
        int count = 0;
        int index = 0;
        for (Result i : results) {
            if (index != 0 || index != results.size()) {
                count += i.getTotalScore();
            }
        }
        int avg = count / (results.size() - 2);
        System.out.println("平均分：" + avg);
        return 0;
    }

    @Override
    public List<SurveyContentDTO> queryIndividualOptionAVG(Result result) {
        SurveyType surveyType = surveyTypeMapper.selectSurveyTypeById(result.getSurveyType());
        String optionsJSON = surveyType.getOptions();
        List<OptionScore> optionScoreList = Arrays.asList(JSON.parseObject(optionsJSON, OptionScore[].class));
        List<Result> results = resultMapper.queryResultAll(result);

        //每个问题选项
        Map<String, List> eachQuestionOptions = new HashMap<>();


        //获取题号
        String options = results.get(0).getOptions();
        //当前结果的选项 Map集合
        List<Map<String, String>> optionsMap = JSON.parseObject(options, List.class);
        //选项
        for (Map<String, String> option : optionsMap) {
            eachQuestionOptions.put(option.get("id"), new ArrayList());
        }

        for (Result i : results) {
            //当前结果的选项字符串
            String options1 = i.getOptions();
            //当前结果的选项 Map集合
            List<Map<String, String>> optionsMap1 = JSON.parseObject(options1, List.class);
            //选项
            for (Map<String, String> option : optionsMap1) {
                if (eachQuestionOptions.containsKey(option.get("id"))) {
                    List list = eachQuestionOptions.get(option.get("id"));
                    list.add(option.get("option"));
                    eachQuestionOptions.put(option.get("id"), list);
                }
            }
        }


        Map<String, String> optionScoreMap = new HashMap<>();

        //每个问题选项得分
        Map<String, List> optionsForEachQuestionScore = new HashMap<>();
        for (OptionScore optionScore : optionScoreList) {
            optionScoreMap.put(optionScore.getIndex(), optionScore.getScore());
        }
        for (String key : eachQuestionOptions.keySet()) {
            List list = eachQuestionOptions.get(key);
            List list1 = new ArrayList();
            for (Object o : list) {
                boolean b = optionScoreMap.containsKey(o);
                if (b) {
                    String score = optionScoreMap.get(o);
                    list1.add(score);
                    optionsForEachQuestionScore.put(key, list1);
                }
            }
        }

        // 每个问题的平均值
        Map<String, Integer> eachQuestionAVG = new HashMap<>();
        for (String key : optionsForEachQuestionScore.keySet()) {
            List list = optionsForEachQuestionScore.get(key);
            int avg = 0;
            int totalScore = 0;
            for (Object score : list) {
                totalScore += Integer.parseInt(score.toString());
            }
            avg = totalScore / list.size();
            eachQuestionAVG.put(key, avg);
        }
        List<SurveyContent> surveyContentList = surveyContentMapper.selectContentBySurveyTypeId(result.getSurveyType());


        List<SurveyContentDTO> surveyContentDTOList = new ArrayList<>();
        for (SurveyContent surveyContent : surveyContentList) {
            SurveyContentDTO surveyContentDTO = new SurveyContentDTO();
            BeanUtils.copyProperties(surveyContent, surveyContentDTO);
            surveyContentDTO.setAvg(eachQuestionAVG.get(surveyContent.getId().toString()));
            surveyContentDTOList.add(surveyContentDTO);

        }
        return surveyContentDTOList;
    }

    @Override
    public List<TeacherDTO> queryResultByClassIdAndDate(Integer classId, String date) {
        List<Integer> list_jy_totalScore = new ArrayList();
        List<Integer> list_bzr_totalScore = new ArrayList();
        Date dateJY = null;
        Date dateBZR = null;
        List<Result> results = resultMapper.queryResultByClassIdAndDate(classId, date);
        for (Result result : results) {
            //教员
            if (result.getTeacherType() == 1) {
                list_jy_totalScore.add(result.getTotalScore());
                if (dateJY == null) {
                    dateJY = result.getCreateDate();
                }
            }
            //班主任
            if (result.getTeacherType() == 2) {
                list_bzr_totalScore.add(result.getTotalScore());
                if (dateBZR == null) {
                    dateBZR = result.getCreateDate();
                }
            }
        }

        // 班级
        Classes classes = classesDao.getClassByClassId(classId);
        //班主任
        Teacher teacherBZR = teacherDao.queryTeacherById(classes.getClass_bzr());
        //教员
        Teacher teacherJY = teacherDao.queryTeacherById(classes.getClass_jy());

        System.out.println(JSON.toJSON(teacherBZR));
        System.out.println(JSON.toJSON(teacherJY));

        //班主任
        TeacherDTO teacherDTO_BZR = new TeacherDTO();
        //教员
        TeacherDTO teacherDTO_JY = new TeacherDTO();

        BeanUtils.copyProperties(teacherJY, teacherDTO_JY);
        BeanUtils.copyProperties(teacherBZR, teacherDTO_BZR);

        /*班主任平均分*/
        teacherDTO_BZR.setAvg(avg(list_bzr_totalScore));
        /*教员平均分*/
        teacherDTO_JY.setAvg(avg(list_jy_totalScore));
        /*班主任评测时间*/
        teacherDTO_BZR.setDate(dateBZR);
        /*教员评测时间*/
        teacherDTO_JY.setDate(dateJY);
        List<TeacherDTO> teacherDTOList = new ArrayList<>();
        teacherDTOList.add(teacherDTO_BZR);
        teacherDTOList.add(teacherDTO_JY);
        return teacherDTOList;
    }

    /**
     * 平均分
     *
     * @param scoreList
     * @return
     */
    public int avg(List<Integer> scoreList) {
        Collections.sort(scoreList);
        int totalScore = 0;
        int avg = 0;
        for (int i = 1; i < scoreList.size() - 1; i++) {
            totalScore += scoreList.get(i);
        }
        if (totalScore != 0) {
            avg = totalScore / (scoreList.size() - 2);
        }
        return avg;
    }
}
