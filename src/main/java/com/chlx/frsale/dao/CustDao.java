package com.chlx.frsale.dao;

import com.xiaowang.mesqle.pojo.Customer;

public interface CustDao {
    Customer findCustById(int cid);
    void addCust(Customer customer);
}
