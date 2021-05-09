package com.chlx.frsale.service.impl;
import com.xiaowang.mesqle.dao.PuchaseDao;
import com.xiaowang.mesqle.pojo.CMP;
import com.xiaowang.mesqle.pojo.Purchase;
import com.xiaowang.mesqle.service.PurchaseService;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * 采购管理业务层
 */
@Service
public class PurchaseServiceImpl implements PurchaseService {
    @Autowired
    private PuchaseDao puchaseDao;

    @Override
    @Transactional
    public void addPurchase(Purchase purchase) {
        this.puchaseDao.insertPurchase(purchase);
    }

    @Override
    public List<Purchase> queryAllPurchase() {
        return this.puchaseDao.selectAllPurchase();
    }

    @Override
    public Purchase queryPurchaseById(int id) {
        return this.puchaseDao.selectPurchaseByID(id);
    }

    @Override
    public void modifyPurchaseFlag(Purchase purchase) {
        this.puchaseDao.updatePurchaseFlag(purchase);
    }

    @Override
    public void modifyPurchaseInfo(Purchase purchase) {
        this.puchaseDao.updatePurchaseInfo(purchase);
    }

    @Override
    public List<Purchase> queryPurchaseByCname(String cname) {
        return this.puchaseDao.selectPurchaseByCname(cname);
    }

    @Override
    public List<Purchase> queryPurchaseByMname(String mname) {
        return this.puchaseDao.selectPurchaseByMname(mname);
    }

    @Override
    public List<Purchase> queryPurchaseByPdate(String pdate, String pdateend) {
        return this.puchaseDao.selectPurchaseByPdate(pdate, pdateend);
    }

    @Override
    public List<Purchase> queryPurchaseByAll(String cname, String mname, String date1, String date2) {
        return this.puchaseDao.selectPurchaseByAll(cname, mname, date1, date2);
    }

    @Override
    public void dropPurchaseById(int id) {
        this.puchaseDao.deletePurchaseById(id);
    }

    @Override
    public XSSFWorkbook exReport(List<CMP> list) {
        return this.puchaseDao.exReport(list);
    }

    @Override
    public List<CMP> findPurchaseByFlag() {
        return this.puchaseDao.findPurchaseByFlag();
    }
}
