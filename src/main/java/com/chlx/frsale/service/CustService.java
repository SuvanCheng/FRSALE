package com.xiaowang.mesqle.service;

import com.xiaowang.mesqle.pojo.Customer;

public interface CustService {
    Customer findCustById(int cid);
    void addCust(Customer customer);
}
