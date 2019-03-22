package com.management.cn.hao.service.impl;

import com.management.cn.entity.Option;
import com.management.cn.hao.dao.OptionMapper;
import com.management.cn.hao.service.OptionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author: GengHao
 * @date: 2019-03-21 20:39
 */
@Service
public class OptionServiceImpl implements OptionService {

    @Autowired
    private OptionMapper optionMapper;

    @Override
    public List<Option> getOptionListBySurveyContentId(Integer surveyContentId) {
        return optionMapper.selectOptionListBySurveyContentId(surveyContentId);
    }

    @Override
    public Boolean addOption(Option option) {
        return optionMapper.insertOption(option);
    }
}
