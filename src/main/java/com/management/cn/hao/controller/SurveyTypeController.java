package com.management.cn.hao.controller;

import com.management.cn.dto.SurveyTypeDTO;
import com.management.cn.entity.SurveyType;
import com.management.cn.hao.service.SurveyTypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author: GengHao
 * @date: 2019-03-20 16:56
 */
@RestController
@RequestMapping("/survey_type")
public class SurveyTypeController {
    @Autowired
    private SurveyTypeService surveyTypeService;

    @RequestMapping("/getSurveyTypeList")
    public List<SurveyTypeDTO> getSurveyTypeList() {
        List<SurveyTypeDTO> surveyTypeDTOList = surveyTypeService.getSurveyTypeList();
        return surveyTypeDTOList;
    }

    @RequestMapping("/getSurveyTypeById")
    public SurveyTypeDTO getSurveyTypeById(Integer id) {
        SurveyTypeDTO surveyTypeDTO = surveyTypeService.getSurveyTypeById(id);
        return surveyTypeDTO;
    }

    /**
     * 保存调查问卷类型
     *
     * @param surveyType
     * @return
     */
    @RequestMapping("/saveSurveyType")
    public Map<String, Object> saveSurveyType(@RequestBody SurveyType surveyType) {

        System.out.println(surveyType);
        Map<String, Object> map = new HashMap<>();
        Boolean result = false;
        /* 如果没有id就执行添加 */
        if (surveyType.getId() == 0 || surveyType.getId() == null) {
            if (surveyTypeService.addSurveyType(surveyType)) {
                result = true;
            } else {
                result = false;
            }
        } else { /* 如果有id就执行修改 */
            if (surveyTypeService.updateSurveyTypebId(surveyType) > 0) {
                result = true;
            } else {
                result = false;
            }
        }


        if (result) {
            map.put("success", true);
            map.put("message", "保存成功！");
        } else {
            map.put("success", false);
            map.put("message", "保存失败！");
        }
        return map;
    }

    @RequestMapping(value = "/deleteSurveyTypeId")
    public Map<String, Object> deleteSurveyTypeId(@RequestParam("ids") String ids) {
        Map<String, Object> map = new HashMap<>();
        String [] idArray = ids.split(",");
        try {
            for (String id : idArray) {
                surveyTypeService.deleteSurveyTypeId(Integer.parseInt(id));
            }
            map.put("success", true);
            map.put("message", "删除成功！");
        } catch (Exception e) {
            e.printStackTrace();
            map.put("success", false);
            map.put("message", "删除失败！");
        }
        return map;
    }

}
