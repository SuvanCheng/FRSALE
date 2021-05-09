package com.chlx.frsale.dao.impl;

import com.chlx.frsale.dao.SaleDao;
import com.chlx.frsale.pojo.SCM;
import com.chlx.frsale.pojo.Sale;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowCallbackHandler;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

@Repository
public class SaleDaoImpl implements SaleDao {
    @Autowired
    private JdbcTemplate jdbcTemplate;
    @Override
    /**
     * 查询全部销售订单
     */
    public List<SCM> findSaleAll() {
        String sql = "select * from t_sale s,t_customer c,t_medicine m where s.cid = c.cid and s.mid = m.mid";
        return this.jdbcTemplate.query(sql, new RowMapper<SCM>() {
            @Override
            /**
             * 结果集的映射
             */
            public SCM mapRow(ResultSet resultSet, int i) throws SQLException {
                SCM scm = new SCM();
                scm.setSid(resultSet.getInt("sid"));
                scm.setUid(resultSet.getInt("uid"));
                scm.setCid(resultSet.getInt("cid"));
                scm.setMid(resultSet.getInt("mid"));
                scm.setSnum(resultSet.getInt("snum"));
                scm.setSdate(resultSet.getDate("sdate"));
                scm.setSamount(resultSet.getInt("samount"));
                scm.setSflag(resultSet.getInt("sflag"));
                scm.setCname(resultSet.getString("cname"));
                scm.setMname(resultSet.getString("mname"));
                return scm;
            }
        });
    }

    /**
     * 预更新Sale查询
     * @param id
     * @return
     */
    @Override
    public Sale selectSaleById(int id) {
        String sql = "select * from T_sale where sid = "+id;
        Sale sale = new Sale();
        /*RowMapper<Sale> rm = BeanPropertyRowMapper.newInstance(Sale.class);
        Sale sale = this.jdbcTemplate.queryForObject(sql,rm,Sale.class);*/
        this.jdbcTemplate.query(sql, new RowCallbackHandler() {
            @Override
            public void processRow(ResultSet resultSet) throws SQLException {
                sale.setSid(resultSet.getInt("sid"));
                sale.setUid(resultSet.getInt("uid"));
                sale.setCid(resultSet.getInt("cid"));
                sale.setMid(resultSet.getInt("mid"));
                sale.setSnum(resultSet.getInt("snum"));
                sale.setSdate(resultSet.getDate("sdate"));
                sale.setSamount(resultSet.getInt("samount"));
                sale.setSflag(resultSet.getInt("sflag"));
            }
        });
        return sale;
    }

    /**
     * 更新销售订单
     * @param sale
     */
    @Override
    public void updateSale(Sale sale) {
        String sql = "update t_sale set snum=?,sdate=?,samount=? where sid=?";
        this.jdbcTemplate.update(sql,sale.getSnum(),sale.getSdate(),sale.getSamount(),sale.getSid());
    }

    /**
     * 删除销售订单
     * @param id
     */
    @Override
    public void delSale(int id) {
        String sql = "delete from T_sale where sid=?";
        this.jdbcTemplate.update(sql,id);
    }

    @Override
    public List<SCM> findSale(String cname, String mname, String date1, String date2) {
        String sql = "select s.sid,s.uid,s.cid,s.mid,s.snum,s.sdate,s.samount,s.sflag,c.cname,m.mname from T_sale s,T_customer c,T_medicine m where s.cid = c.cid and s.mid = m.mid "+"and c.cname='李四' and m.mname='复方甘草含片' and s.sdate>='"+date1+"' and s.sdate<='2020-6-13'";
        //System.out.println("客户名："+cname+"药品名"+mname+"起始时间"+date1+"结束时间"+date2);
        if(cname==""&& mname=="" && date1=="" &&date2==""){
            sql = "select s.sid,s.uid,s.cid,s.mid,s.snum,s.sdate,s.samount,s.sflag,c.cname,m.mname from T_sale s,T_customer c,T_medicine m where s.cid = c.cid and s.mid = m.mid ";
        }
        if(cname==""&& mname=="" && date1=="" &&date2!=""){
            sql = "select s.sid,s.uid,s.cid,s.mid,s.snum,s.sdate,s.samount,s.sflag,c.cname,m.mname from T_sale s,T_customer c,T_medicine m where s.cid = c.cid and s.mid = m.mid "+"and s.sdate<='"+date2+"'";
        }
        if(cname==""&& mname=="" && date1!="" &&date2==""){
            sql = "select s.sid,s.uid,s.cid,s.mid,s.snum,s.sdate,s.samount,s.sflag,c.cname,m.mname from T_sale s,T_customer c,T_medicine m where s.cid = c.cid and s.mid = m.mid "+"and s.sdate>='"+date1+"'";
        }
        if(cname==""&& mname=="" && date1!="" &&date2!=""){
            sql = "select s.sid,s.uid,s.cid,s.mid,s.snum,s.sdate,s.samount,s.sflag,c.cname,m.mname from T_sale s,T_customer c,T_medicine m where s.cid = c.cid and s.mid = m.mid "+"and s.sdate>='"+date1+"' and s.sdate<='"+date2+"'";
        }
        if(cname==""&& mname!="" && date1=="" &&date2==""){
            sql = "select s.sid,s.uid,s.cid,s.mid,s.snum,s.sdate,s.samount,s.sflag,c.cname,m.mname from T_sale s,T_customer c,T_medicine m where s.cid = c.cid and s.mid = m.mid "+"and m.mname='"+mname+"'";
        }
        if(cname==""&& mname!="" && date1=="" &&date2!=""){
            sql = "select s.sid,s.uid,s.cid,s.mid,s.snum,s.sdate,s.samount,s.sflag,c.cname,m.mname from T_sale s,T_customer c,T_medicine m where s.cid = c.cid and s.mid = m.mid "+"and m.mname='"+mname+"' and s.sdate<='"+date2+"'";
        }
        if(cname==""&& mname!="" && date1!="" &&date2==""){
            sql = "select s.sid,s.uid,s.cid,s.mid,s.snum,s.sdate,s.samount,s.sflag,c.cname,m.mname from T_sale s,T_customer c,T_medicine m where s.cid = c.cid and s.mid = m.mid "+"and m.mname='"+mname+"' and s.sdate>='"+date1+"'";
        }
        if(cname==""&& mname!="" && date1!="" &&date2!=""){
            sql = "select s.sid,s.uid,s.cid,s.mid,s.snum,s.sdate,s.samount,s.sflag,c.cname,m.mname from T_sale s,T_customer c,T_medicine m where s.cid = c.cid and s.mid = m.mid "+"and m.mname='"+mname+"' and s.sdate>='"+date1+"' and s.sdate<='"+date2+"'";
        }
        if(cname!=""&& mname=="" && date1=="" &&date2==""){
            sql = "select s.sid,s.uid,s.cid,s.mid,s.snum,s.sdate,s.samount,s.sflag,c.cname,m.mname from T_sale s,T_customer c,T_medicine m where s.cid = c.cid and s.mid = m.mid and c.cname='"+cname+"'";
        }
        if(cname!=""&& mname=="" && date1=="" &&date2!=""){
            sql = "select s.sid,s.uid,s.cid,s.mid,s.snum,s.sdate,s.samount,s.sflag,c.cname,m.mname from T_sale s,T_customer c,T_medicine m where s.cid = c.cid and s.mid = m.mid and c.cname='"+cname+"' and s.sdate<='"+date2+"'";
        }
        if(cname!=""&& mname=="" && date1!="" &&date2==""){
            sql = "select s.sid,s.uid,s.cid,s.mid,s.snum,s.sdate,s.samount,s.sflag,c.cname,m.mname from T_sale s,T_customer c,T_medicine m where s.cid = c.cid and s.mid = m.mid and c.cname='"+cname+"' and s.sdate>='"+date1+"'";
        }
        if(cname!=""&& mname=="" && date1!="" &&date2!=""){
            sql = "select s.sid,s.uid,s.cid,s.mid,s.snum,s.sdate,s.samount,s.sflag,c.cname,m.mname from T_sale s,T_customer c,T_medicine m where s.cid = c.cid and s.mid = m.mid and c.cname='"+cname+"' and s.sdate>='"+date1+"' and s.sdate<='"+date2+"'";
        }
        if(cname!=""&& mname!="" && date1=="" &&date2==""){
            sql = "select s.sid,s.uid,s.cid,s.mid,s.snum,s.sdate,s.samount,s.sflag,c.cname,m.mname from T_sale s,T_customer c,T_medicine m where s.cid = c.cid and s.mid = m.mid and c.cname='"+cname+"' and m.mname='"+mname+"'";
        }
        if(cname!=""&& mname!="" && date1=="" &&date2!=""){
            sql = "select s.sid,s.uid,s.cid,s.mid,s.snum,s.sdate,s.samount,s.sflag,c.cname,m.mname from T_sale s,T_customer c,T_medicine m where s.cid = c.cid and s.mid = m.mid and c.cname='"+cname+"' and m.mname='"+mname+"' and s.sdate<='"+date2+"'";
        }
        if(cname!=""&& mname!="" && date1!="" &&date2==""){
            sql = "select s.sid,s.uid,s.cid,s.mid,s.snum,s.sdate,s.samount,s.sflag,c.cname,m.mname from T_sale s,T_customer c,T_medicine m where s.cid = c.cid and s.mid = m.mid and c.cname='"+cname+"' and m.mname='"+mname+"' and s.sdate>='"+date1+"'";
        }
        if(cname!=""&& mname!="" && date1!="" &&date2!=""){
            sql = "select s.sid,s.uid,s.cid,s.mid,s.snum,s.sdate,s.samount,s.sflag,c.cname,m.mname from T_sale s,T_customer c,T_medicine m where s.cid = c.cid and s.mid = m.mid and c.cname='"+cname+"' and m.mname='"+mname+"' and s.sdate>='"+date1+"' and s.sdate<='"+date2+"'";
        }
        return this.jdbcTemplate.query(sql, new RowMapper<SCM>() {
            @Override
            /**
             * 结果集的映射
             */
            public SCM mapRow(ResultSet resultSet, int i) throws SQLException {
                SCM sale = new SCM();
                sale.setSid(resultSet.getInt("sid"));
                sale.setUid(resultSet.getInt("uid"));
                sale.setCid(resultSet.getInt("cid"));
                sale.setMid(resultSet.getInt("mid"));
                sale.setSnum(resultSet.getInt("snum"));
                sale.setSdate(resultSet.getDate("sdate"));
                sale.setSamount(resultSet.getInt("samount"));
                sale.setSflag(resultSet.getInt("sflag"));
                sale.setCname(resultSet.getString("cname"));
                sale.setMname(resultSet.getString("mname"));
                return sale;
            }
        });
    }

    @Override
    public void addSale(Sale sale) {
        String sql = "insert into t_sale(uid,cid,mid,snum,sdate,samount,sflag) values(?,?,?,?,?,?,?)";
        this.jdbcTemplate.update(sql,sale.getUid(),sale.getCid(),sale.getMid(),sale.getSnum(),sale.getSdate(),sale.getSamount(),sale.getSflag());
    }

    @Override
    public XSSFWorkbook exReport() {
        List<SCM> list = this.findSaleAll();
        XSSFWorkbook wb = new XSSFWorkbook();
        Sheet sheet = wb.createSheet("Sales");//创建一张表
        Row titleRow = sheet.createRow(0);//创建第一行，起始为0
        titleRow.createCell(0).setCellValue("序号");//第一列
        titleRow.createCell(1).setCellValue("订单id");
        titleRow.createCell(2).setCellValue("客户id");
        titleRow.createCell(3).setCellValue("客户姓名");
        titleRow.createCell(4).setCellValue("药品id");
        titleRow.createCell(5).setCellValue("药品名");
        titleRow.createCell(6).setCellValue("数量");
        titleRow.createCell(7).setCellValue("时间");
        titleRow.createCell(8).setCellValue("金额");
        titleRow.createCell(9).setCellValue("状态");
        int cell = 1;
        for (SCM scm : list) {
            Row row = sheet.createRow(cell);//从第二行开始保存数据
            row.createCell(0).setCellValue(cell);
            row.createCell(1).setCellValue(scm.getSid());
            row.createCell(2).setCellValue(scm.getCid());
            row.createCell(3).setCellValue(scm.getCname());
            row.createCell(4).setCellValue(scm.getMid());
            row.createCell(5).setCellValue(scm.getMname());
            row.createCell(6).setCellValue(scm.getSnum());
            row.createCell(7).setCellValue(scm.getSdate());
            row.createCell(8).setCellValue(scm.getSamount());
            row.createCell(9).setCellValue(scm.getSflag());

            cell++;
            System.out.println(scm.getSid());
        }
        return wb;
    }

    @Override
    public List<SCM> findSaleByFlag() {
        String sql = "select * from t_sale s,t_customer c,t_medicine m where s.cid = c.cid and s.mid = m.mid and s.sflag=0";
        return this.jdbcTemplate.query(sql, new RowMapper<SCM>() {
            @Override
            /**
             * 结果集的映射
             */
            public SCM mapRow(ResultSet resultSet, int i) throws SQLException {
                SCM scm = new SCM();
                scm.setSid(resultSet.getInt("sid"));
                scm.setUid(resultSet.getInt("uid"));
                scm.setCid(resultSet.getInt("cid"));
                scm.setMid(resultSet.getInt("mid"));
                scm.setSnum(resultSet.getInt("snum"));
                scm.setSdate(resultSet.getDate("sdate"));
                scm.setSamount(resultSet.getInt("samount"));
                scm.setSflag(resultSet.getInt("sflag"));
                scm.setCname(resultSet.getString("cname"));
                scm.setMname(resultSet.getString("mname"));
                return scm;
            }
        });
    }
}
