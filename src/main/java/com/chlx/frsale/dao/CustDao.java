package com.chlx.frsale.dao;

import com.chlx.frsale.pojo.Customer;

public interface CustDao {
    Customer findCustById(int cid);
    void addCust(Customer customer);
}
