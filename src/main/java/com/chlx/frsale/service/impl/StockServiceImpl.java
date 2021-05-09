package com.chlx.frsale.service.impl;

import com.chlx.frsale.dao.StockDao;
import com.chlx.frsale.pojo.MS;
import com.chlx.frsale.service.StockService;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class StockServiceImpl implements StockService {
    @Autowired
    private StockDao stockDao;

    @Override
    public List<MS> listStock() {
        return this.stockDao.findStockAll();
    }

    @Override
    public List<MS> findStockByMname(String mname) {
        return this.stockDao.findStockByMname(mname);
    }

    @Override
    public void fahuo(int id) {
        this.stockDao.fahuo(id);
    }

    @Override
    public XSSFWorkbook exReport() {
        return this.stockDao.exReport();
    }

    @Override
    public void ruku(int id) {
        this.stockDao.ruku(id);
    }
}
