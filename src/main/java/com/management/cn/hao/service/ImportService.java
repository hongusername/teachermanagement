package com.management.cn.hao.service;

import org.apache.poi.ss.usermodel.Workbook;

import java.io.InputStream;
import java.util.List;

/**
 * @author: GengHao
 * @date: 2019-03-30 16:36
 */
public interface ImportService {

    Workbook getWorkbook(InputStream inStr, String fileName) throws Exception;

    List getBankListByExcel(InputStream in, String fileName) throws Exception;
}
