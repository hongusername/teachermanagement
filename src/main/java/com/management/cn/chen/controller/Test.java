package com.management.cn.chen.controller;

import org.quartz.*;
import org.quartz.impl.StdSchedulerFactory;
import org.springframework.context.annotation.Bean;

import java.text.SimpleDateFormat;
import java.util.Date;

public class Test {

    public void run()  {
        //创建一个JobDetail实例，将该实例与MyJob Class绑定。
        JobDetail jobDetail = JobBuilder.newJob(JobTest.class).withIdentity("JobTest", "group1").build();
        //创建一个Trigger实例，定义该Job立即执行，并且每隔两秒钟重复一次，
//            Trigger trigger=TriggerBuilder
//                    .newTrigger()
//                    .withIdentity("myTrigger","group1")
//                    .startNow()
//                    .withSchedule(SimpleScheduleBuilder
//                            .simpleSchedule()
//                            .withIntervalInSeconds(5) .repeatForever())
//                    .build();
        Trigger trigger = TriggerBuilder
                .newTrigger()
                .withIdentity("myTrigger", "group1")
                //每隔5秒执行一次：*/5 * * * * ?
                //0 0 0 1 * ? //每年每月1日00:00:00执行一次
                //0 15 10 15 * ?
                //0 35 18 * * ?
                //0 0 0 27 * ? // 每年每月27日每秒执行一次
                .withSchedule(CronScheduleBuilder.cronSchedule("0 0 0 1 * ?")).build();
        SchedulerFactory sf = new StdSchedulerFactory();
        Scheduler scheduler = null;
        try {
            scheduler = sf.getScheduler();

        scheduler.start();
        Date date = new Date();
        SimpleDateFormat sfd = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");//规范格式
        //System.out.println("当前时间为："+sfd.format(date));
        scheduler.scheduleJob(jobDetail, trigger);
        } catch (SchedulerException e) {
            e.printStackTrace();
        }
    }




}
