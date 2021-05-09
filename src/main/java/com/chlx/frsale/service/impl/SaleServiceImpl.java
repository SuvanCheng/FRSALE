package com.xiaowang.mesqle.service.impl;

import com.xiaowang.mesqle.dao.SaleDao;
import com.xiaowang.mesqle.pojo.SCM;
import com.xiaowang.mesqle.pojo.Sale;
import com.xiaowang.mesqle.service.SaleService;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
/**
 * 销售模块业务层
 */
@Service
public class SaleServiceImpl implements SaleService {
    @Autowired
    private SaleDao saleDao;

    /**
     * 查询所有销售订单
     * @return
     */
    @Override
    public List<SCM> findSaleAll() {
        return saleDao.findSaleAll();
    }

    /**
     * 预更新查询（by id）
     * @param id
     * @return
     */
    @Override
    public Sale findSaleById(int id) {
        Sale sale = this.saleDao.selectSaleById(id);
        return sale;
    }

    /**
     * 更新用户
     * @param sale
     */
    @Override
    @Transactional
    public void modifySale(Sale sale) {
        this.saleDao.updateSale(sale);
    }

    /**
     * 删除销售订单
     * @param id
     */
    @Override
    @Transactional
    public void delSale(int id) {
        this.saleDao.delSale(id);
    }

    @Override
    public List<SCM> findSale(String cname, String mname, String date1, String date2) {
        return this.saleDao.findSale(cname,mname,date1,date2);
    }

    @Override
    @Transactional
    public void addSale(Sale sale) {
        this.saleDao.addSale(sale);
    }

    @Override
    public XSSFWorkbook exReport() {
        return this.saleDao.exReport();
    }

    @Override
    public List<SCM> findSaleByFlag() {
        return this.saleDao.findSaleByFlag();
    }
}
