package com.chlx.frsale.service;

import com.chlx.frsale.pojo.SCM;
import com.chlx.frsale.pojo.Sale;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.util.List;

public interface SaleService {
    List<SCM> findSaleAll();
    Sale findSaleById(int id);
    void modifySale(Sale sale);
    void delSale(int id);
    List<SCM> findSale(String cname, String mname, String date1, String date2);
    void addSale(Sale sale);
    XSSFWorkbook exReport();
    List<SCM> findSaleByFlag();
}
