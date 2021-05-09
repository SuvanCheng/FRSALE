package com.chlx.frsale.service;

import com.chlx.frsale.pojo.*;
import com.chlx.frsale.pojo.CMP;
import com.chlx.frsale.pojo.Purchase;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.util.List;

public interface PurchaseService {
    void addPurchase(Purchase purchase);
    List<Purchase> queryAllPurchase();
    Purchase queryPurchaseById(int id);
    void modifyPurchaseFlag(Purchase purchase);
    void modifyPurchaseInfo(Purchase purchase);
    List<Purchase> queryPurchaseByCname(String cname);
    List<Purchase> queryPurchaseByMname(String mname);
    List<Purchase> queryPurchaseByPdate(String pdate, String pdateend);
    List<Purchase> queryPurchaseByAll(String cname, String mname, String date1, String date2);
    void dropPurchaseById(int id);


    XSSFWorkbook exReport(List<CMP> list);

    List<CMP> findPurchaseByFlag();
}
