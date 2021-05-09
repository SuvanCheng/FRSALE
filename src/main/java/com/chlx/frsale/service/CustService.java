package com.chlx.frsale.service;

import com.chlx.frsale.pojo.Customer;

public interface CustService {
    Customer findCustById(int cid);
    void addCust(Customer customer);
}
