package com.chlx.frsale.dao.impl;

import com.chlx.frsale.dao.CustDao;
import com.chlx.frsale.pojo.Customer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowCallbackHandler;
import org.springframework.stereotype.Repository;

import java.sql.ResultSet;
import java.sql.SQLException;

@Repository
public class CustDaoImpl implements CustDao {
    @Autowired
    private JdbcTemplate jdbcTemplate;


    @Override
    public Customer findCustById(int cid) {
        String sql = "select * from T_customer where cid = "+cid;
        Customer customer = new Customer();
        /*RowMapper<Sale> rm = BeanPropertyRowMapper.newInstance(Sale.class);
        Sale sale = this.jdbcTemplate.queryForObject(sql,rm,Sale.class);*/
        this.jdbcTemplate.query(sql, new RowCallbackHandler() {
            @Override
            public void processRow(ResultSet resultSet) throws SQLException {
                customer.setCid(resultSet.getInt("cid"));
                customer.setCname(resultSet.getString("cname"));
                customer.setCphonenum(resultSet.getString("cphonenum"));
                customer.setCaddress(resultSet.getString("caddress"));
                customer.setCemail(resultSet.getString("cemail"));
            }
        });
        return customer;
    }

    @Override
    public void addCust(Customer customer) {
        String sql = "insert into t_customer(cname,cphonenum,caddress,cemail) values(?,?,?,?)";
        this.jdbcTemplate.update(sql,customer.getCname(),customer.getCphonenum(),customer.getCaddress(),customer.getCemail());
    }
}
