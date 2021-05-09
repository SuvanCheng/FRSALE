package com.chlx.frsale.dao.impl;

import com.chlx.frsale.dao.PuchaseDao;
import com.chlx.frsale.dao.SaleDao;
import com.chlx.frsale.dao.StockDao;
import com.chlx.frsale.pojo.*;
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
public class StockDaoImpl implements StockDao {
    @Autowired
    private JdbcTemplate jdbcTemplate;
    @Autowired
    private SaleDao saleDao;
    @Autowired
    private PuchaseDao puchaseDao;
    @Override
    public List<MS> findStockAll() {
        String sql = "select * from t_stock s,t_medicine m where s.mid = m.mid";
        return this.jdbcTemplate.query(sql, new RowMapper<MS>() {
            @Override
            /**
             * 结果集的映射
             */
            public MS mapRow(ResultSet resultSet, int i) throws SQLException {
                MS ms = new MS();
                ms.setStoid(resultSet.getInt("stoid"));
                ms.setMid(resultSet.getInt("mid"));
                ms.setStocknum(resultSet.getInt("stocknum"));
                ms.setMname(resultSet.getString("mname"));
                return ms;
            }
        });
    }

    @Override
    public List<MS> findStockByMname(String mname) {
        String sql = null;
        if(mname==""){
            sql = "select * from t_stock s,t_medicine m where s.mid = m.mid";
        }else{
            sql = "select * from t_stock s,t_medicine m where s.mid = m.mid and m.mname='"+mname+"'";
        }
        return this.jdbcTemplate.query(sql, new RowMapper<MS>() {
            @Override
            /**
             * 结果集的映射
             */
            public MS mapRow(ResultSet resultSet, int i) throws SQLException {
                MS ms = new MS();
                ms.setStoid(resultSet.getInt("stoid"));
                ms.setMid(resultSet.getInt("mid"));
                ms.setStocknum(resultSet.getInt("stocknum"));
                ms.setMname(resultSet.getString("mname"));
                return ms;
            }
        });
    }

    @Override
    public void fahuo(int id) {
        Sale sale = this.saleDao.selectSaleById(id);
        String sql = "update t_sale set sflag=1 where sid="+id;
        Stock stock = this.findStockByMid(sale.getMid());
        if(sale.getSnum() <= stock.getStocknum()) {
            String sql1 = "update T_stock set stocknum="+ (stock.getStocknum()-sale.getSnum())+" where mid=" + stock.getMid();
            this.jdbcTemplate.update(sql1);
            this.jdbcTemplate.update(sql);
        }
    }

    @Override
    public XSSFWorkbook exReport() {
        List<MS> list = this.findStockAll();
        XSSFWorkbook wb = new XSSFWorkbook();
        Sheet sheet = wb.createSheet("Stocks");//创建一张表
        Row titleRow = sheet.createRow(0);//创建第一行，起始为0
        titleRow.createCell(0).setCellValue("序号");//第一列
        titleRow.createCell(1).setCellValue("库存编码");
        titleRow.createCell(2).setCellValue("药品编码");
        titleRow.createCell(3).setCellValue("药品名称");
        titleRow.createCell(4).setCellValue("药品数量");
        int cell = 1;
        for (MS ms : list) {
            Row row = sheet.createRow(cell);//从第二行开始保存数据
            row.createCell(0).setCellValue(cell);
            row.createCell(1).setCellValue(ms.getStoid());
            row.createCell(2).setCellValue(ms.getMid());
            row.createCell(3).setCellValue(ms.getMname());
            row.createCell(4).setCellValue(ms.getStocknum());
            cell++;
        }
        return wb;
    }

    @Override
    public void ruku(int id) {
        Purchase purchase = new Purchase();
        //Stock stock = new Stock();
        String sql = "update t_purchase set pflag=1 where pid="+id;
        String sql1;
        purchase = this.puchaseDao.selectPurchaseByID(id);
        Stock stock = this.findStockByMid(purchase.getMid());
        System.out.println(stock.toString());
        if (stock.getStoid()!=0) {
            sql1 = "update T_stock set stocknum=" + stock.getStocknum() + purchase.getPnum() + " where mid=" + purchase.getMid();
            this.jdbcTemplate.update(sql1);
        }else{
            sql1 = "insert into T_stock(mid, stocknum) values ("+purchase.getMid()+", "+ purchase.getPnum()+")";
            this.jdbcTemplate.update(sql1);
        }
        System.out.println(sql1);
        this.jdbcTemplate.update(sql);
    }

    @Override
    public Stock findStockByMid(int mid) {
        String sql = "select * from T_stock where mid=" + mid;
        Stock stock = new Stock();
        this.jdbcTemplate.query(sql, new RowCallbackHandler() {
            @Override
            public void processRow(ResultSet rs) throws SQLException {
                stock.setStoid(rs.getInt("stoid"));
                stock.setMid(rs.getInt("mid"));
                stock.setStocknum(rs.getInt("stocknum"));
            }
        });
        return stock;
    }

    @Override
    public boolean isExistStock(int mid) {
        String sql = "select * from T_stock where mid=" + mid;
        Stock stock = new Stock();
        final boolean[] is = new boolean[1];
        this.jdbcTemplate.query(sql, new RowCallbackHandler() {
            @Override
            public void processRow(ResultSet rs) throws SQLException {
                stock.setStoid(rs.getInt("stoid"));
                stock.setMid(rs.getInt("mid"));
                stock.setStocknum(rs.getInt("stocknum"));
                if (rs != null) {
                    is[0] = true;
                } else {
                    is[0] = false;
                }
            }
        });
        return is[0];
    }
}
