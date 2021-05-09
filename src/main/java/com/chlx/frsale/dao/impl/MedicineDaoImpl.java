package com.chlx.frsale.dao.impl;

import com.xiaowang.mesqle.dao.MedicineDao;
import com.xiaowang.mesqle.pojo.Medicine;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowCallbackHandler;
import org.springframework.stereotype.Repository;

import java.sql.ResultSet;
import java.sql.SQLException;

@Repository
public class MedicineDaoImpl implements MedicineDao {
    @Autowired
    private JdbcTemplate jdbcTemplate;

    /**
     * 插入药品
     * @param medicine
     */
    @Override
    public void insertMedicine(Medicine medicine) {
        String sql = "insert into T_medicine(mname,mproduce,mprice,freshdate,form,component," +
                "cure,explain) values('"+ medicine.getMname()+"','"+medicine.getMproduce()
                +"',"+ medicine.getMprice()+",'"+ medicine.getFreshdate()+"','"+
                medicine.getForm()+"','"+ medicine.getComponent()+"','"+
                medicine.getCure()+"','"+ medicine.getExplain()+"')";
        this.jdbcTemplate.update(sql);
    }

    /**
     * 按ID查询
     * @param id
     * @return
     */
    @Override
    public Medicine selectMedicineById(int id) {
        Medicine medicine = new Medicine();
        String sql = "select * from T_medicine where mid=" + id;
        System.out.println(sql);
        this.jdbcTemplate.query(sql, new RowCallbackHandler() {
            @Override
            public void processRow(ResultSet rs) throws SQLException {
                medicine.setMid(rs.getInt("mid"));
                medicine.setMname(rs.getString("mname"));
                medicine.setMproduce(rs.getString("mproduce"));
                medicine.setMprice(rs.getInt("mprice"));
                medicine.setFreshdate(rs.getString("freshdate"));
                medicine.setForm(rs.getString("form"));
                medicine.setComponent(rs.getString("component"));
                medicine.setCure(rs.getString("cure"));
                medicine.setExplain(rs.getString("explain"));
            }
        });
        return medicine;
    }
}
