package com.management.cn.hao.dao;

import com.management.cn.entity.Result;
import org.springframework.stereotype.Component;

/**
 * @author: GengHao
 * @date: 2019-03-24 09:11
 */
@Component
public interface ResultMapper {

    int addResult(Result result);
}
