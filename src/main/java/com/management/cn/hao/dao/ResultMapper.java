package com.management.cn.hao.dao;

import com.management.cn.entity.Result;
import io.swagger.models.auth.In;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.List;

/**
 * @author: GengHao
 * @date: 2019-03-24 09:11
 */
@Component
public interface ResultMapper {

    int addResult(Result result);

    List<Result> queryResultAll(Result result);


    List<Result> queryResultByClassIdAndDate(@Param("classId") Integer classId,@Param("createDate") String date);
}
