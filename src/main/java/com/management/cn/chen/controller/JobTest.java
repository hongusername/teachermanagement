package com.management.cn.chen.controller;

import com.management.cn.chen.controller.util.BaseDao;
import com.management.cn.chen.dao.IClassesDao;
import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;

import javax.annotation.Resource;

public class JobTest implements Job {

    @Resource
    private IClassesDao iClassesDao;

    @Override
    public void execute(JobExecutionContext jobExecutionContext) throws JobExecutionException {

        BaseDao b=new BaseDao();
        b.upd();
    }
}
