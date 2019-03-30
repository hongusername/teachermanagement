package com.management.cn.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;
import java.util.Map;

/**
 * @author: GengHao
 * @date: 2019-03-22 22:14
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class ResultDTO {

    private Integer classId;
    private Integer surveyTypeId;
    private Integer teacherId;
    private List<Map<String,String>> checkedList;
    private String opinion;
}
