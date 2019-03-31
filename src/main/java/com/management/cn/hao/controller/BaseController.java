package com.management.cn.hao.controller;

import com.management.cn.entity.Evaluating;

import java.util.List;

/**
 * @author: GengHao
 * @date: 2019-03-31 22:04
 */
public class BaseController {

    public Evaluating getOneEvaluating(List<Evaluating> evaluatingList, String className) {
        for (Evaluating evaluating : evaluatingList) {
            for (String item : evaluating.getClassNameList()) {
                if (className.equals(item)) {
                    return evaluating;
                }
            }
        }
        return null;
    }

}
