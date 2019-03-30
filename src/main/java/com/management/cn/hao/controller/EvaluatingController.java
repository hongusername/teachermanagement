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
    /**
     * 最小分钟
     */
    private static final int MINIMUM_MINUTE = 30;
    private static final String TYPE_DEL = "del";
    private static final String TYPE_UPD = "upd";
    SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm");

    @RequestMapping("/del")
    public ResponseResult del(Integer id) {
        ResponseResult responseResult = new ResponseResult();
        Evaluating evaluating = evaluatingService.getEvaluatingById(id);
        ResponseResult validateDate = delAndUpdValidateDate(evaluating, TYPE_DEL);
        //验证时间
        if ((boolean) validateDate.getData() == false) {
            return validateDate;
        }
        int i = evaluatingService.deleteEvaluating(id);
        if (i > 0) {
            responseResult.setStatus(200);
            responseResult.setMessage("删除成功！");
        } else {
            responseResult.setStatus(500);
            responseResult.setMessage("删除失败！");
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
        Evaluating evaluating = evaluatingService.getEvaluatingById(id);
        ResponseResult validateDate = delAndUpdValidateDate(evaluating, TYPE_UPD);
        //验证时间
        if ((boolean) validateDate.getData() == false) {
            return validateDate;
        }
        responseResult.setStatus(200);
        responseResult.setData(evaluating);
        return responseResult;
    }

    @RequestMapping("/save")
    public ResponseResult save(@RequestBody Evaluating evaluating) {
        ResponseResult responseResult = new ResponseResult();
        ResponseResult validateDate = saveValidateDate(evaluating);
        //验证时间
        if ((boolean) validateDate.getData() == false) {
            return validateDate;
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

    /**
     * 验证时间
     *
     * @return
     */
    public ResponseResult saveValidateDate(Evaluating evaluating) {
        ResponseResult responseResult = new ResponseResult();
        try {
            Long startTime = sdf.parse(evaluating.getStartTime()).getTime();
            Long endTime = sdf.parse(evaluating.getEndTime()).getTime();
            Long s = (endTime - startTime) / (1000 * 60);
            //结束时间 小于 当前时间
            if (endTime < System.currentTimeMillis()) {
                responseResult.setData(false);
                responseResult.setStatus(500);
                responseResult.setMessage("结束时间必须大于当前时间");
                return responseResult;
            }

            //开始时间大于结束时间 或者 开始时间等于结束时间
            if (startTime > endTime || startTime.equals(endTime)) {
                responseResult.setData(false);
                responseResult.setStatus(500);
                responseResult.setMessage("开始时间必须小于结束时间");
                return responseResult;
            }
            if (s < MINIMUM_MINUTE) {
                responseResult.setData(false);
                responseResult.setStatus(500);
                responseResult.setMessage("开始时间和结束时间至少相隔30分钟");
                return responseResult;
            }

        } catch (ParseException e) {
            e.printStackTrace();
        }
        responseResult.setData(true);
        return responseResult;
    }


    public ResponseResult delAndUpdValidateDate(Evaluating evaluating, String type) {
        ResponseResult responseResult = new ResponseResult();

        String message = "";
        if (TYPE_DEL.equals(type)) {
            message = "删除";
        }
        if (TYPE_UPD.equals(type)) {
            message = "修改";
        }

        try {
            Long startTime = sdf.parse(evaluating.getStartTime()).getTime();
            Long endTime = sdf.parse(evaluating.getEndTime()).getTime();
            //开始时间小于当前时间
            if (startTime < System.currentTimeMillis()) {
                responseResult.setData(false);
                responseResult.setStatus(500);
                responseResult.setMessage("已经开始的测评无法" + message);
                return responseResult;
            } else if (endTime < System.currentTimeMillis()) {
                //结束时间小于当前时间
                responseResult.setData(false);
                responseResult.setStatus(500);
                responseResult.setMessage("已经结束的测评无法" + message);
                return responseResult;
            }
        } catch (ParseException e) {
            e.printStackTrace();
        }

        responseResult.setData(true);
        return responseResult;
    }
}
