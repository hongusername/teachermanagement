package com.management.cn.hao.service.impl;

import com.management.cn.hao.service.ImportService;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.stereotype.Service;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

/**
 * @author: GengHao
 * @date: 2019-03-30 14:56
 */
@Service
public class ImportServiceImpl implements ImportService {

    /**
     * 处理上传的文件
     *
     * @param in
     * @param fileName
     * @return
     * @throws Exception
     */
    @Override
    public List getBankListByExcel(InputStream in, String fileName) throws Exception {
        List list = new ArrayList<>();
        //创建Excel工作薄
        Workbook work = this.getWorkbook(in, fileName);
        if (null == work) {
            throw new Exception("创建Excel工作薄为空！");
        }
        Sheet sheet = null;
        Row row = null;
        Cell cell = null;
        for (int i = 0; i < work.getNumberOfSheets(); i++) {
            sheet = work.getSheetAt(i);
            if (sheet == null) {
                continue;
            }

            for (int j = sheet.getFirstRowNum(); j <= sheet.getLastRowNum(); j++) {
                row = sheet.getRow(j);
                if (row == null || row.getFirstCellNum() == j) {
                    continue;
                }

                List<Object> li = new ArrayList<>();
                for (int y = row.getFirstCellNum(); y < row.getLastCellNum(); y++) {
                    cell = row.getCell(y);
                    li.add(cell);
                }
                list.add(li);
            }
        }
        work.close();
        return list;
    }

    /**
     * 判断文件格式
     *
     * @param inStr
     * @param fileName
     * @return
     * @throws Exception
     */
    @Override
    public Workbook getWorkbook(InputStream inStr, String fileName) throws Exception {
        Workbook workbook = null;
        String fileType = fileName.substring(fileName.lastIndexOf("."));
        if (".xls".equals(fileType)) {
            workbook = new HSSFWorkbook(inStr);
        } else if (".xlsx".equals(fileType)) {
            workbook = new XSSFWorkbook(inStr);
        } else {
            throw new Exception("请上传excel文件！");
        }
        return workbook;
    }
}
