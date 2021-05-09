package com.chlx.frsale.dao.impl;


import com.chlx.frsale.dao.PuchaseDao;
import com.chlx.frsale.pojo.CMP;
import com.chlx.frsale.pojo.Customer;
import com.chlx.frsale.pojo.Medicine;
import com.chlx.frsale.pojo.Purchase;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowCallbackHandler;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

/**
 * 采购订单管理持久层
 */
@Repository
public class PurchaseDaoImpl implements PuchaseDao {
    @Autowired
    private JdbcTemplate jdbcTemplate;

    /**
     * 添加订单
     * @param purchase
     */
    @Override
    public void insertPurchase(Purchase purchase) {
        String sql = "insert into T_purchase(uid,cid,mid,pnum,pprice,pdate,pamount,pflag) " +
                "values("+ purchase.getUid() +","+ purchase.getCid() +","+ purchase.getMid()  +
                ","+ purchase.getPnum() +","+ purchase.getPprice() +",'"+ purchase.getPdate() +"',"+ purchase.getPamount() +","+ purchase.getPflag() +")";
        System.out.println(sql);
        this.jdbcTemplate.update(sql);
    }

    /**
     * 按ID查询
     * @param id
     * @return
     */
    @Override
    public Purchase selectPurchaseByID(int id) {
        Purchase purchase = new Purchase();
        String sql = "select * from T_purchase where pid="+ id;
        System.out.println(sql);
        this.jdbcTemplate.query(sql, new RowCallbackHandler() {
            @Override
            public void processRow(ResultSet rs) throws SQLException {
                purchase.setPid(rs.getInt("pid"));
                purchase.setUid(rs.getInt("uid"));
                purchase.setCid(rs.getInt("cid"));
                purchase.setMid(rs.getInt("mid"));
                purchase.setPnum(rs.getInt("pnum"));
                purchase.setPprice(rs.getInt("pprice"));
                purchase.setPdate(Date.valueOf(rs.getString("pdate")));
                purchase.setPamount(rs.getInt("pamount"));
                purchase.setPflag(rs.getInt("pflag"));
            }
        });
        return purchase;
    }

    /**
     * 查询所有采购订单
     * @return
     */
    @Override
    public List<Purchase> selectAllPurchase() {
        String sql = "select * from T_purchase";
        return this.jdbcTemplate.query(sql, new RowMapper<Purchase>() {
                @Override
                public Purchase mapRow(ResultSet rs, int rowNum) throws SQLException {
                    Purchase purchase = new Purchase();
                    purchase.setPid(rs.getInt("pid"));
                    purchase.setUid(rs.getInt("uid"));
                    purchase.setCid(rs.getInt("cid"));
                    purchase.setMid(rs.getInt("mid"));
                    purchase.setPnum(rs.getInt("pnum"));
                    purchase.setPprice(rs.getInt("pprice"));
                    purchase.setPdate(Date.valueOf(rs.getString("pdate")));
                    purchase.setPamount(rs.getInt("pamount"));
                    purchase.setPflag(rs.getInt("pflag"));
                    return purchase;
                }
            });
    }

    /**
     * 更新订单flag
     * @param purchase
     */
    @Override
    public void updatePurchaseFlag(Purchase purchase) {
        String sql = "update T_purchase set pflag=1 where pid="+ purchase.getPid();
        System.out.println(sql);
        this.jdbcTemplate.update(sql);
    }

    /**
     * 修改订单信息
     * @param purchase
     */
    @Override
    public void updatePurchaseInfo(Purchase purchase) {
        String sql = "update T_purchase set uid="+ purchase.getUid()
                +",cid="+ purchase.getCid()+",mid="+ purchase.getMid()+",pnum="+ purchase.getPnum()
                +",pprice="+ purchase.getPprice()+",pdate='"+ purchase.getPdate()
                +"',pamount="+ purchase.getPamount()+" where pid=" + purchase.getPid();
        System.out.println(sql);
        this.jdbcTemplate.update(sql);
    }

    @Override
    public List<Purchase> selectPurchaseByCname(String cname) {
        String sql = "select * from T_purchase where cid=(select cid from T_customer where cname='"+ cname +"')";
        System.out.println(sql);
        return this.jdbcTemplate.query(sql, new RowMapper<Purchase>() {
            @Override
            public Purchase mapRow(ResultSet rs, int rowNum) throws SQLException {
                Purchase purchase = new Purchase();
                purchase.setPid(rs.getInt("pid"));
                purchase.setUid(rs.getInt("uid"));
                purchase.setCid(rs.getInt("cid"));
                purchase.setMid(rs.getInt("mid"));
                purchase.setPnum(rs.getInt("pnum"));
                purchase.setPprice(rs.getInt("pprice"));
                purchase.setPdate(Date.valueOf(rs.getString("pdate")));
                purchase.setPamount(rs.getInt("pamount"));
                purchase.setPflag(rs.getInt("pflag"));
                return purchase;
            }
        });
    }

    @Override
    public List<Purchase> selectPurchaseByMname(String mname) {
        String sql = "select * from T_purchase where mid in (select mid from T_medicine where mname='"+ mname+"')";
        System.out.println(sql);
        return this.jdbcTemplate.query(sql, new RowMapper<Purchase>() {
            @Override
            public Purchase mapRow(ResultSet rs, int rowNum) throws SQLException {
                Purchase purchase = new Purchase();
                purchase.setPid(rs.getInt("pid"));
                purchase.setUid(rs.getInt("uid"));
                purchase.setCid(rs.getInt("cid"));
                purchase.setMid(rs.getInt("mid"));
                purchase.setPnum(rs.getInt("pnum"));
                purchase.setPprice(rs.getInt("pprice"));
                purchase.setPdate(Date.valueOf(rs.getString("pdate")));
                purchase.setPamount(rs.getInt("pamount"));
                purchase.setPflag(rs.getInt("pflag"));
                return purchase;
            }
        });
    }

    @Override
    public List<Purchase> selectPurchaseByPdate(String pdate, String pdateend) {
        String sql = "select * from T_purchase";
        if(pdate=="" && pdateend==""){
            sql = "select * from T_purchase";
        }else if (pdate!="" && pdateend==""){
            sql = "select * from T_purchase where pdate>='"+pdate+"'";
        }else if (pdate=="" && pdateend!=""){
            sql = "select * from T_purchase where pdate<='"+pdateend+"'";
        }else if (pdate!="" && pdate!=""){
            sql = "select * from T_purchase where pdate>='"+ pdate+"' and pdate<='"+ pdateend+"'";
        }
        System.out.println(sql);
        return this.jdbcTemplate.query(sql, new RowMapper<Purchase>() {
            @Override
            public Purchase mapRow(ResultSet rs, int rowNum) throws SQLException {
                Purchase purchase = new Purchase();
                purchase.setPid(rs.getInt("pid"));
                purchase.setUid(rs.getInt("uid"));
                purchase.setCid(rs.getInt("cid"));
                purchase.setMid(rs.getInt("mid"));
                purchase.setPnum(rs.getInt("pnum"));
                purchase.setPprice(rs.getInt("pprice"));
                purchase.setPdate(Date.valueOf(rs.getString("pdate")));
                purchase.setPamount(rs.getInt("pamount"));
                purchase.setPflag(rs.getInt("pflag"));
                return purchase;
            }
        });
    }

    /**
     *  结合四个查询条件进行查询
     * @param cname
     * @param mname
     * @param date1
     * @param date2
     * @return
     */
    @Override
    public List<Purchase> selectPurchaseByAll(String cname, String mname, String date1, String date2) {
        List<Purchase> list = null;
        String sql = "select * from T_purchase ";
        String bycname = " cid=(select cid from T_customer where cname='"+cname+"') ";
        String bymname = " mid in (select mid from T_medicine where mname='"+ mname+"') ";
        String bydate1 = " pdate>='"+ date1+"' ";
        String bydate2 = " pdate<='"+ date2+"' ";
        if(cname=="" && mname==""){
            list = selectPurchaseByPdate(date1, date2);
            return list;/*
            if (date1!="" && date2=="") {
                sql += "where" + bydate1;
            }else if (date1=="" && date2!=""){
                sql += "where" + bydate2;
            }else if (date1!="" && date2!=""){
                sql += "where" + bydate1 + "and" + bydate2;
            }*/
        }else if (cname=="" && mname!="" && date1=="" && date2==""){
            list = selectPurchaseByMname(mname);
            return list;
            //sql += "where" + bymname;
        }else if (cname!="" && mname=="" && date1=="" && date2==""){
            list = selectPurchaseByCname(cname);
            return list;
            //sql += "where" + bycname;
        }else if (cname!="" && mname!="" && date1=="" && date2==""){
            /*sql = "select * from T_purchase where cid=(select cid from T_customer where cname='"+cname+"') " +
                    "and mid in (select mid from T_medicine where mname='"+ mname+"')";*/
            sql += "where" + bycname + "and" + bymname;
        }else if (cname=="" && mname!="" && date1!="" && date2==""){
            //sql = "select * from T_purchase where mid in (select mid from T_medicine where mname='"+ mname+"') and" + bydate1;
            sql += "where" + bymname + "and" +bydate1;
        }else if (cname=="" && mname!="" && date1=="" && date2!=""){
            sql += "where" + bymname + "and" + bydate2;
        }else if (cname=="" && mname!="" && date1!="" && date2!=""){
            sql += "where" + bymname + "and" + bydate1 + "and" + bydate2;
        }else if (cname!="" && mname=="" && date1!="" && date2==""){
            sql += "where" + bycname + "and" + bydate1;
        }else if (cname!="" && mname=="" && date1=="" && date2!=""){
            sql += "where" + bycname + "and" + bydate2;
        }else if (cname!="" && mname=="" && date1!="" && date2!=""){
            sql += "where" + bycname + "and" + bydate1 + "and" + bydate2;
        }else if (cname!="" && mname!="" && date1!="" && date2==""){
            sql += "where" + bycname + "and" + bymname + "and" + bydate1;
        }else if (cname!="" && mname!="" && date1=="" && date2!=""){
            sql += "where" + bycname + "and" + bymname + "and" + bydate2;
        }else if (cname!="" && mname!="" && date1!="" && date2!=""){
            sql += "where" + bycname + "and" + bymname + "and" + bydate1 + "and" + bydate2;
        }
        System.out.println(sql);
        list = this.jdbcTemplate.query(sql, new RowMapper<Purchase>() {
            @Override
            public Purchase mapRow(ResultSet rs, int rowNum) throws SQLException {
                Purchase purchase = new Purchase();
                purchase.setPid(rs.getInt("pid"));
                purchase.setUid(rs.getInt("uid"));
                purchase.setCid(rs.getInt("cid"));
                purchase.setMid(rs.getInt("mid"));
                purchase.setPnum(rs.getInt("pnum"));
                purchase.setPprice(rs.getInt("pprice"));
                purchase.setPdate(Date.valueOf(rs.getString("pdate")));
                purchase.setPamount(rs.getInt("pamount"));
                purchase.setPflag(rs.getInt("pflag"));
                return purchase;
            }
        });
        System.out.println("条件查询结果个数：" + list.size());
        return list;
    }

    /**
     * 删除订单
     * @param id
     */
    @Override
    public void deletePurchaseById(int id) {
        String sql = "delete from T_purchase where pid="+ id;
        System.out.println(sql);
        this.jdbcTemplate.update(sql);
    }



    /**
     * 生成报表
     * @return
     */
    public XSSFWorkbook exReport(List<CMP> list) {
        XSSFWorkbook wb = new XSSFWorkbook();
        Sheet sheet = wb.createSheet("Purchases");//创建一张表
        Row titleRow = sheet.createRow(0);//创建第一行，起始为0
        titleRow.createCell(0).setCellValue("序号");//第一列
        titleRow.createCell(1).setCellValue("订单id");
        titleRow.createCell(2).setCellValue("客户id");
        titleRow.createCell(3).setCellValue("客户姓名");
        titleRow.createCell(4).setCellValue("药品id");
        titleRow.createCell(5).setCellValue("药品名称");
        titleRow.createCell(6).setCellValue("采购数量");
        titleRow.createCell(7).setCellValue("采购单价");
        titleRow.createCell(8).setCellValue("采购日期");
        titleRow.createCell(9).setCellValue("采购金额");
        titleRow.createCell(10).setCellValue("订单状态");
        int cell = 1;
        for (CMP cmp : list) {
            Row row = sheet.createRow(cell);//从第二行开始保存数据
            row.createCell(0).setCellValue(cell);
            row.createCell(1).setCellValue(cmp.getPurchase().getPid());
            row.createCell(2).setCellValue(cmp.getPurchase().getCid());
            row.createCell(3).setCellValue(cmp.getCustomer().getCname());
            row.createCell(4).setCellValue(cmp.getPurchase().getMid());
            row.createCell(5).setCellValue(cmp.getMedicine().getMname());
            row.createCell(6).setCellValue(cmp.getPurchase().getPnum());
            row.createCell(7).setCellValue(cmp.getPurchase().getPprice());
            row.createCell(8).setCellValue(cmp.getPurchase().getPdate());
            row.createCell(9).setCellValue(cmp.getPurchase().getPamount());
            row.createCell(10).setCellValue(cmp.getPurchase().getPflag());

            cell++;
            System.out.println(cmp.getPurchase().getPid());
        }
        return wb;
    }

    @Override
    public List<CMP> findPurchaseByFlag() {
        String sql = "select * from t_purchase p,t_customer c,t_medicine m where p.cid = c.cid and p.mid = m.mid and p.pflag=0";
        return this.jdbcTemplate.query(sql, new RowMapper<CMP>() {
            @Override
            public CMP mapRow(ResultSet rs, int rowNum) throws SQLException {
                CMP cmp = new CMP();
                Purchase purchase = new Purchase();
                Customer customer = new Customer();
                Medicine medicine = new Medicine();
                purchase.setPid(rs.getInt("pid"));
                purchase.setUid(rs.getInt("uid"));
                purchase.setCid(rs.getInt("cid"));
                purchase.setMid(rs.getInt("mid"));
                purchase.setPnum(rs.getInt("pnum"));
                purchase.setPprice(rs.getInt("pprice"));
                purchase.setPdate(Date.valueOf(rs.getString("pdate")));
                purchase.setPamount(rs.getInt("pamount"));
                purchase.setPflag(rs.getInt("pflag"));

                customer.setCemail(rs.getString("cemail"));
                customer.setCaddress(rs.getString("caddress"));
                customer.setCphonenum(rs.getString("cphonenum"));
                customer.setCname(rs.getString("cname"));
                customer.setCid(rs.getInt("cid"));

                medicine.setComponent(rs.getString("component"));
                medicine.setCure(rs.getString("cure"));
                medicine.setExplain(rs.getString("explain"));
                medicine.setForm(rs.getString("form"));
                medicine.setFreshdate(rs.getString("freshdate"));
                medicine.setMid(rs.getInt("mid"));
                medicine.setMname(rs.getString("mname"));
                medicine.setMprice(rs.getInt("mprice"));
                medicine.setMproduce(rs.getString("mproduce"));
                cmp.setCustomer(customer);
                cmp.setMedicine(medicine);
                cmp.setPurchase(purchase);
                return cmp;
            }
        });
    }
}
