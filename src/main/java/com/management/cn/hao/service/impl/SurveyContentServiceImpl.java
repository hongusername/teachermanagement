package com.management.cn.hao.service.impl;

import com.alibaba.fastjson.JSON;
import com.management.cn.dto.SurveyContentDTO;
import com.management.cn.entity.Option;
import com.management.cn.entity.SurveyContent;
import com.management.cn.hao.dao.OptionMapper;
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

    @Autowired
    private OptionMapper optionMapper;

    @Override
    public List<SurveyContentDTO> getContentBySurveyTypeId(Integer surveyType) {
        /* 每一道题 */
        List<SurveyContent> contentList = surveyContentMapper.selectContentBySurveyTypeId(surveyType);
        List<SurveyContentDTO> contentDTOList = new ArrayList<>();

        /* 遍历每一道 查询每一道题的选项 */
        for (SurveyContent surveyContent : contentList) {
            /* 数据传输对象 */
            SurveyContentDTO surveyContentDTO = new SurveyContentDTO();
            /* 属性拷贝 */
            BeanUtils.copyProperties(surveyContent, surveyContentDTO);
            /* 查询当前题 对应的 选项 */
            List<Option> options = optionMapper.selectOptionListBySurveyContentId(surveyContent.getId());
            /* 设置当前题 对应的 选项 */
            surveyContentDTO.setOptions(options);
            /* 添加到集合 */
            contentDTOList.add(surveyContentDTO);
        }
        return contentDTOList;
    }

    @Override
    public Boolean addSurveyContent(List<SurveyContentDTO> surveyContentDTOList) {
        for (SurveyContentDTO obj : surveyContentDTOList) {
            SurveyContent surveyContent = new SurveyContent();
            //调查问卷id
            surveyContent.setSurveyType(obj.getSurveyType());
            //问题
            surveyContent.setContent(obj.getContent());
            //保存问题
            surveyContentMapper.insertSurveyContent(surveyContent);

            //保存问题选项
            for (Option o : obj.getOptions()) {
                //设置选项所属问题id
                o.setSurveyContentId(surveyContent.getId());
                optionMapper.insertOption(o);
            }
        }
        return true;
    }
}
