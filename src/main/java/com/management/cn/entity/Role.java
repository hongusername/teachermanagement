package com.management.cn.entity;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Role {


    private Integer id;
    private String name;
    private String pwd;
    private Integer type;
}
