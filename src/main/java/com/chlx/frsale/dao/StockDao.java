package com.xiaowang.mesqle.dao;

import com.xiaowang.mesqle.pojo.MS;
import com.xiaowang.mesqle.pojo.Stock;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.util.List;

public interface StockDao {
    List<MS> findStockAll();
    List<MS> findStockByMname(String mname);
    void fahuo(int id);
    XSSFWorkbook exReport();

    void ruku(int id);
    Stock findStockByMid(int mid);
    boolean isExistStock(int mid);
}
