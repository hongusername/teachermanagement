package com.management.cn.hao.service;

import com.management.cn.dto.ResultDTO;
import com.management.cn.dto.SurveyContentDTO;
import com.management.cn.dto.TeacherDTO;
import com.management.cn.entity.Result;
import com.management.cn.entity.Teacher;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * @author: GengHao
 * @date: 2019-03-24 09:03
 */
public interface ResultService {

    /**
     * 结果
     * @param resultDTO
     * @return
     */
    int addResult(ResultDTO resultDTO);

    Integer queryResultAVG( Result result);

    List<SurveyContentDTO> queryIndividualOptionAVG(Result result);

    List<TeacherDTO> queryResultByClassIdAndDate(Integer classId, String date);
}
