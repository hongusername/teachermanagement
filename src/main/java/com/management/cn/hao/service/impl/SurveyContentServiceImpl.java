package com.management.cn.hao.service.impl;

import com.alibaba.fastjson.JSON;
import com.management.cn.dto.SurveyContentDTO;
import com.management.cn.entity.SurveyContent;
import com.management.cn.hao.dao.SurveyContentMapper;
import com.management.cn.hao.service.SurveyContentService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * @author: GengHao
 * @date: 2019-03-20 12:40
 */
@Service
public class SurveyContentServiceImpl implements SurveyContentService {

    @Autowired
    private SurveyContentMapper surveyContentMapper;

    @Override
    public List<SurveyContentDTO> getContentBySurveyTypeId(Integer surveyType) {
        List<SurveyContent> contentList = surveyContentMapper.selectContentBySurveyTypeId(surveyType);
        List<SurveyContentDTO> contentDTOList = new ArrayList<>();

        for (SurveyContent item :contentList) {
            SurveyContentDTO surveyContentDTO = new SurveyContentDTO();
            BeanUtils.copyProperties(item,surveyContentDTO);
            surveyContentDTO.setOptions(JSON.parseObject(item.getOptions(), List.class));
            contentDTOList.add(surveyContentDTO);
        }
        return contentDTOList;
    }

    @Override
    public Boolean addSurveyContent(SurveyContent surveyContent) {
        return surveyContentMapper.insertSurveyContent(surveyContent);
    }


}
