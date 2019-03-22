package com.management.cn.hao.dao;

import com.management.cn.entity.Option;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * @author: GengHao
 * @date: 2019-03-21 20:34
 */
@Component
public interface OptionMapper {

    List<Option> selectOptionListBySurveyContentId(Integer surveyContentId);

    Boolean insertOption(Option option);
}
