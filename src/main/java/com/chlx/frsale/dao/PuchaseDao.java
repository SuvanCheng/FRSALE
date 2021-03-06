package com.chlx.frsale.dao;


import com.chlx.frsale.pojo.CMP;
import com.chlx.frsale.pojo.Purchase;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.util.List;

public interface PuchaseDao {
    void insertPurchase(Purchase purchase);
    Purchase selectPurchaseByID(int id);
    List<Purchase> selectAllPurchase();
    void updatePurchaseFlag(Purchase purchase);
    void updatePurchaseInfo(Purchase purchase);

    //根据客户名称、水果名称、订单日期进行查询
    List<Purchase> selectPurchaseByCname(String cname);
    List<Purchase> selectPurchaseByMname(String mname);
    List<Purchase> selectPurchaseByPdate(String pdate, String pdateend);
    List<Purchase> selectPurchaseByAll(String cname, String mname, String date1, String date2);
    void deletePurchaseById(int id);


    XSSFWorkbook exReport(List<CMP> list);

    List<CMP> findPurchaseByFlag();
}
