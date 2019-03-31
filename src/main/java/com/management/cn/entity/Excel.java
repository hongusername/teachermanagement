package com.management.cn.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

/**
 * @author: GengHao
 * @date: 2019-03-30 15:50
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Excel {

    private Integer id;
    private String title;
    private List<String> options;
}
