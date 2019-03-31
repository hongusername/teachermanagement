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
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

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
    public ResponseResult addResult(ResultDTO resultDTO) {
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
        //创建日期
        result.setCreateDate(new Date());
        //调查问卷类型
        result.setSurveyType(resultDTO.getSurveyTypeId());
        //选项
        result.setOptions(JSON.toJSONString(resultDTO.getCheckedList()));
        //意见、建议
        result.setOpinion(resultDTO.getOpinion());
        //昵称
        result.setNickname(resultDTO.getNickname());

        result.setTeacherId(resultDTO.getTeacherId());
        resultMapper.addResult(result);

        ResponseResult responseResult = new ResponseResult();
        responseResult.setStatus(200);
        responseResult.setData(totalScore);
        return responseResult;
    }


    @Override
    public ResponseResult queryIndividualOptionAVG(Result result) {

        SurveyType surveyType = surveyTypeMapper.selectSurveyTypeById(result.getSurveyType());
        String optionsJSON = surveyType.getOptions();
        List<OptionScore> optionScoreList = Arrays.asList(JSON.parseObject(optionsJSON, OptionScore[].class));
        List<Result> results = resultMapper.queryResultAll(result);
        List<SurveyContent> surveyContentList = surveyContentMapper.selectContentBySurveyTypeId(result.getSurveyType());
        //每个问题选项
        Map<String, List> eachQuestionOptions = new HashMap<>();
        //当前结果的选项 Map集合
        for (SurveyContent surveyContent : surveyContentList) {
            eachQuestionOptions.put(surveyContent.getId().toString(), new ArrayList());
        }
        //意见建议
        List<Map<String,Object>> opinionList = new ArrayList<>();
        for (Result i : results) {
            Map<String,Object> map = new HashMap<>();
            map.put("nickname",i.getNickname());
            map.put("opinion",i.getOpinion());
            opinionList.add(map);
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
        List<SurveyContentDTO> surveyContentDTOList = new ArrayList<>();
        for (SurveyContent surveyContent : surveyContentList) {
            SurveyContentDTO surveyContentDTO = new SurveyContentDTO();
            BeanUtils.copyProperties(surveyContent, surveyContentDTO);
            surveyContentDTO.setAvg(eachQuestionAVG.get(surveyContent.getId().toString()));
            surveyContentDTOList.add(surveyContentDTO);
        }
        ResponseResult responseResult = new ResponseResult();
        Map<String, Object> map = new HashMap<>();
        //每一项的平均分
        map.put("surveyContentDTOList", surveyContentDTOList);
        //调查时间
        map.put("date", result.getDate());
        //班级名称
        Classes classes = getClassByClassId(result.getClassid());
        map.put("className", classes.getClass_name());
        //老师
        Teacher teacher = teacherDao.queryTeacherById(result.getTeacherId());
        map.put("teacherName", teacher.getName());
        map.put("teacherType", teacher.getType());
        map.put("opinionList", opinionList);
        responseResult.setData(map);
        return responseResult;
    }

    private Classes getClassByClassId(Integer classId) {
        return classesDao.getClassByClassId(classId);
    }

    private Teacher queryTeacherById(Integer teacherId) {
        return teacherDao.queryTeacherById(teacherId);
    }

    @Override
    public ResponseResult<List<TeacherDTO>> queryResultByClassIdAndDate(Integer classId, String date) {
        ResponseResult<List<TeacherDTO>> responseResult = new ResponseResult<>();
        List<Result> results = resultMapper.queryResultByClassIdAndDate(classId, date);

        if (results == null || results.size() == 0) {
            responseResult.setStatus(200);
            responseResult.setMessage("暂无数据");
            return responseResult;
        }


        List<Integer> list_jy_totalScore = new ArrayList();
        List<Integer> list_bzr_totalScore = new ArrayList();
        Date dateJY = null;
        Date dateBZR = null;
        Integer surveyTypeJY = 0;
        Integer surveyTypeBZR = 0;
        for (Result result : results) {
            Teacher teacher = teacherDao.queryTeacherById(result.getTeacherId());
            //教员
            if (teacher.getType() == 1) {
                list_jy_totalScore.add(result.getTotalScore());
                if (dateJY == null) {
                    dateJY = result.getCreateDate();
                    surveyTypeJY = result.getSurveyType();
                }
            }
            //班主任
            if (teacher.getType() == 2) {
                list_bzr_totalScore.add(result.getTotalScore());
                if (dateBZR == null) {
                    dateBZR = result.getCreateDate();
                    surveyTypeBZR = result.getSurveyType();
                }
            }
        }

        // 班级
        Classes classes = getClassByClassId(classId);
        //班主任
        Teacher teacherBZR = queryTeacherById(classes.getClass_bzr());
        //教员
        Teacher teacherJY = queryTeacherById(classes.getClass_jy());

        //班主任
        TeacherDTO teacherDTO_BZR = new TeacherDTO();
        //教员
        TeacherDTO teacherDTO_JY = new TeacherDTO();

        BeanUtils.copyProperties(teacherJY, teacherDTO_JY);
        BeanUtils.copyProperties(teacherBZR, teacherDTO_BZR);

        /*班主任平均分*/
        teacherDTO_BZR.setAvg(avg(list_bzr_totalScore));
        /*班主任评测时间*/
        teacherDTO_BZR.setDate(dateBZR);
        /*班级id*/
        teacherDTO_BZR.setClassId(classId);
        /*调查问卷类型*/
        teacherDTO_BZR.setSurveyType(surveyTypeBZR);
        /*参加本次测评的人数*/
        teacherDTO_BZR.setTotalNumber(list_bzr_totalScore.size());

        /*教员平均分*/
        teacherDTO_JY.setAvg(avg(list_jy_totalScore));
        /*教员评测时间*/
        teacherDTO_JY.setDate(dateJY);
        /*班级id*/
        teacherDTO_JY.setClassId(classId);
        /*调查问卷类型*/
        teacherDTO_JY.setSurveyType(surveyTypeJY);
        /*参加本次测评的人数*/
        teacherDTO_JY.setTotalNumber(list_jy_totalScore.size());


        List<TeacherDTO> teacherDTOList = new ArrayList<>();
        teacherDTOList.add(teacherDTO_BZR);
        teacherDTOList.add(teacherDTO_JY);

        responseResult.setStatus(200);
        responseResult.setData(teacherDTOList);
        return responseResult;
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
        //去掉一个最高分和一个最低分
        for (int i = 1; i < scoreList.size() - 1; i++) {
            totalScore += scoreList.get(i);
        }
        if (totalScore != 0) {
            avg = totalScore / (scoreList.size() - 2);
        }
        return avg;
    }
}
