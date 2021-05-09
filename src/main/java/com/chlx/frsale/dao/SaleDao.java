package com.chlx.frsale.dao;

import com.chlx.frsale.pojo.SCM;
import com.chlx.frsale.pojo.Sale;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.util.List;

public interface SaleDao {
    List<SCM> findSaleAll();
    Sale selectSaleById(int id);
    void updateSale(Sale sale);
    void delSale(int id);
    List<SCM> findSale(String cname, String mname, String date1, String date2);
    void addSale(Sale sale);
    XSSFWorkbook exReport();
    List<SCM> findSaleByFlag();
}
