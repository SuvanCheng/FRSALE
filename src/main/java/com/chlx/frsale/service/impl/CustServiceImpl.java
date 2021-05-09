package com.chlx.frsale.service.impl;

import com.xiaowang.mesqle.dao.CustDao;
import com.xiaowang.mesqle.pojo.Customer;
import com.xiaowang.mesqle.service.CustService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class CustServiceImpl implements CustService {
    @Autowired
    private CustDao custDao;


    @Override
    public Customer findCustById(int cid) {
        return this.custDao.findCustById(cid);
    }

    @Override
    @Transactional
    public void addCust(Customer customer) {
        this.custDao.addCust(customer);
    }
}
