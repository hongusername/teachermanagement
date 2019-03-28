package com.management.cn.hao.controller;

import com.management.cn.entity.Evaluating;
import com.management.cn.entity.ResponseResult;
import com.management.cn.hao.service.EvaluatingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.List;

/**
 * @author: GengHao
 * @date: 2019-03-27 16:25
 */
@RestController
@RequestMapping("/evaluating")
public class EvaluatingController {

    @Autowired
    private EvaluatingService evaluatingService;
    SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm");
    Calendar calendar = Calendar.getInstance();

    @RequestMapping("/del")
    public ResponseResult del(Integer id) {
        ResponseResult responseResult = new ResponseResult();
        Evaluating evaluating = evaluatingService.getEvaluatingById(id);
        try {
            calendar.setTime(sdf.parse(evaluating.getStartTime()));
            if (calendar.getTime().getTime() < System.currentTimeMillis()) {
                responseResult.setStatus(500);
                responseResult.setMessage("已经开始的测评无法删除！");
            } else {
                int i = evaluatingService.deleteEvaluating(id);
                if (i > 0) {
                    responseResult.setStatus(200);
                    responseResult.setMessage("删除成功！");
                } else {
                    responseResult.setStatus(500);
                    responseResult.setMessage("删除失败！");
                }
            }
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return responseResult;
    }

    @RequestMapping("/getEvaluatingList")
    public List<Evaluating> getEvaluatingList() {
        return evaluatingService.getEvaluatingList();
    }


    @RequestMapping("/getEvaluatingById")
    public ResponseResult getEvaluatingById(Integer id) {
        ResponseResult responseResult = new ResponseResult();
        responseResult.setData(evaluatingService.getEvaluatingById(id));
        return responseResult;
    }

    @RequestMapping("/save")
    public ResponseResult save(@RequestBody Evaluating evaluating) {
        ResponseResult responseResult = new ResponseResult();


        try {
            Long startTime = sdf.parse(sdf.format(sdf.parse(evaluating.getStartTime()))).getTime();
            Long endTime = sdf.parse(sdf.format(sdf.parse(evaluating.getEndTime()))).getTime();

            if (endTime < System.currentTimeMillis()) {
                responseResult.setStatus(500);
                responseResult.setMessage("已经结束的测评无法修改！");
                return responseResult;
            }

            if (startTime > endTime || startTime.equals(endTime)) {
                responseResult.setStatus(500);
                responseResult.setMessage("开始时间必须小于结束时间！");
                return responseResult;
            }
        } catch (ParseException e) {
            e.printStackTrace();
        }
        //添加
        if (evaluating.getId() == null) {
            boolean b = evaluatingService.addEvaluating(evaluating);
            if (b) {
                responseResult.setStatus(200);
                responseResult.setMessage("添加成功！");
            } else {
                responseResult.setStatus(500);
                responseResult.setMessage("添加失败！");
            }
        } else {//修改
            int i = evaluatingService.updateEvaluating(evaluating);
            if (i > 0) {
                responseResult.setStatus(200);
                responseResult.setMessage("修改成功");
            } else {
                responseResult.setStatus(500);
                responseResult.setMessage("修改失败");
            }
        }
        return responseResult;
    }
}
