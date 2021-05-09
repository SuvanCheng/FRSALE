package com.chlx.frsale.service;

import com.xiaowang.mesqle.pojo.MS;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.util.List;

public interface StockService {
    List<MS> listStock();
    List<MS> findStockByMname(String mname);
    void fahuo(int id);
    XSSFWorkbook exReport();

    void ruku(int id);
}
