package com.management.cn.hao.service;

import com.management.cn.dto.ResultDTO;

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
}