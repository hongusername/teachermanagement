package com.management.cn.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * @author: GengHao
 * @date: 2019-03-25 20:11
 * state:返回值,一般前端先判断返回值再输出 message
 * message:给前端的信息
 * data:给前端的json数据
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class ResponseResult<T> implements Serializable {

    private Integer status;
    private String message;
    private T data;


}
