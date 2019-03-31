package com.management.cn.hao.controller;

import com.alibaba.fastjson.JSON;
import com.management.cn.dto.SurveyContentDTO;
import com.management.cn.dto.SurveyTypeDTO;
import com.management.cn.entity.*;
import com.management.cn.hao.service.SurveyTypeService;
import com.management.cn.hao.service.impl.ImportServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import javax.servlet.http.HttpServletRequest;
import java.io.InputStream;
import java.util.*;

/**
 * @author: GengHao
 * @date: 2019-03-30 14:56
 */
@Controller
@RequestMapping("/excel")
public class ImportController {

    @Autowired
    private ImportServiceImpl importService;
    @Autowired
    private SurveyTypeService surveyTypeService;


    @RequestMapping("/uploadExcel")
    @ResponseBody
    public ResponseResult uploadExcel(HttpServletRequest request, Integer surveyType) throws Exception {
        ResponseResult responseResult = new ResponseResult();
        Map<String, Object> map = new HashMap<>();
        MultipartHttpServletRequest multipartRequest = (MultipartHttpServletRequest) request;
        MultipartFile file = multipartRequest.getFile("filename");
        if (file == null || file.isEmpty()) {
            map.put("success", false);
            map.put("message", "文件不能为空");
            responseResult.setData(map);
            responseResult.setStatus(500);
            return responseResult;
        }
        List<Excel> excelList = null;
        try {
            InputStream inputStream = file.getInputStream();
            List<List<Object>> list = importService.getBankListByExcel(inputStream, file.getOriginalFilename());
            inputStream.close();
            excelList = new ArrayList<>();
            for (int i = 0; i < list.size(); i++) {
                List optionList = new ArrayList();
                int length = list.get(i).size();
                Excel excel = new Excel();
                for (int j = 2; j < length; j++) {
                    optionList.add(list.get(i).get(j).toString().trim());
                }
                //id
                excel.setId((int) Double.parseDouble(list.get(i).get(0).toString()));
                //问题
                excel.setTitle(list.get(i).get(1).toString().trim());
                //选项
                excel.setOptions(optionList);
                excelList.add(excel);
            }
        } catch (Exception e) {
            e.printStackTrace();
            map.put("success", false);
            map.put("message", e.getMessage());
            responseResult.setStatus(500);
            responseResult.setData(map);
            return responseResult;
        }

        //查询调查问卷
        SurveyTypeDTO surveyTypeDTO = surveyTypeService.getSurveyTypeById(surveyType);
        List<OptionScore> optionScoreList = Arrays.asList(JSON.parseObject(surveyTypeDTO.getOptions(), OptionScore[].class));


        List<SurveyContentDTO> surveyContentDTOList = new ArrayList<>();
        for (Excel excel : excelList) {
            SurveyContentDTO surveyContentDTO = new SurveyContentDTO();
            List<Option> optionList = new ArrayList<>();
            for (int i = 0; i < optionScoreList.size(); i++) {
                Option option = new Option();
                option.setId(i + 1);
                option.setIndex(optionScoreList.get(i).getIndex());
                option.setOption(excel.getOptions().get(i));
                optionList.add(option);
            }
            surveyContentDTO.setSurveyType(surveyType);
            surveyContentDTO.setContent(excel.getTitle());
            surveyContentDTO.setOptions(optionList);
            surveyContentDTOList.add(surveyContentDTO);
        }
        map.put("success", true);
        responseResult.setStatus(200);
        responseResult.setMessage("导入成功");
        responseResult.setData(JSON.toJSON(surveyContentDTOList));
        return responseResult;
    }

    @RequestMapping("/up")
    public String index() {
        return "upload";
    }
}
