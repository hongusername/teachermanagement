package com.management.cn.hao.service.impl;

import com.alibaba.fastjson.JSON;
import com.management.cn.dto.ResultDTO;
import com.management.cn.dto.SurveyTypeDTO;
import com.management.cn.entity.OptionScore;
import com.management.cn.entity.Result;
import com.management.cn.entity.SurveyType;
import com.management.cn.hao.dao.ResultMapper;
import com.management.cn.hao.dao.SurveyTypeMapper;
import com.management.cn.hao.service.ResultService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.Map;

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
    public Integer queryResultAVG( Result result ) {
        List<Result> results=resultMapper.queryResultAll(result);
        int count = 0;
        int index=0;
        for (Result i:results){
            if(index!=0||index!=results.size()){
                count += i.getTotalScore();
            }
        }
        int avg = count/(results.size()-2);
        System.out.println("平均分："+avg);
        return 0;
    }
}
