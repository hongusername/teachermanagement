package com.management.cn.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author: GengHao
 * @date: 2019-03-21 20:27
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Option {

    private Integer id;
    private Integer surveyContentId;
    private String option;

}
