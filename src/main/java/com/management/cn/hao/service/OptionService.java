package com.management.cn.hao.service;

import com.management.cn.entity.Option;

import java.util.List;

/**
 * @author: GengHao
 * @date: 2019-03-21 20:39
 */
public interface OptionService {

    List<Option> getOptionListBySurveyContentId(Integer surveyContentId);

    Boolean addOption(Option option);
}
