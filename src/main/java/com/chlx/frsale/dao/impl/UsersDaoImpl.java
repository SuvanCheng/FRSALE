package com.chlx.frsale.dao.impl;

import com.chlx.frsale.dao.UsersDao;
import com.chlx.frsale.pojo.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowCallbackHandler;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

@Repository
public class UsersDaoImpl implements UsersDao {

    @Autowired
    private JdbcTemplate jdbcTemplate;
    public void insertUsers(User users) {
        String sql = "insert into t_user(username,password,type) values(?,?,?)";
        this.jdbcTemplate.update(sql,users.getUsername(),users.getPassword(),users.getType());
    }

    @Override
    public User login(User user) {
        String sql = "select * from t_user where username = '"+user.getUsername()+"' and password = '"+user.getPassword()+"'";
        User user1 = new User();
        this.jdbcTemplate.query(sql, new RowCallbackHandler() {
            @Override
            public void processRow(ResultSet resultSet) throws SQLException {
                user1.setUid(resultSet.getInt("uid"));
                user1.setUsername(resultSet.getString("username"));
                user1.setPassword(resultSet.getString("password"));
                user1.setType(resultSet.getInt("type"));
            }
        });
        System.out.println("用户名"+user1.getUsername()+"密码"+user1.getPassword());
        if(user1.getUsername()!=null&&user1.getPassword()!=null){
            return user1;
        }
        return null;
    }

    /**
     * 修改用户类型
     * @param user
     */
    @Override
    public void updateUserType(User user) {
        String sql = "update T_user set type=" + user.getType() + " where uid=" + user.getUid();
        this.jdbcTemplate.update(sql);
        System.out.println(sql);
    }

    /**
     * 查询所有用户
     * @return
     */
    @Override
    public List<User> selectAllUser() {
        String sql = "select * from T_user";
        return this.jdbcTemplate.query(sql, new RowMapper<User>() {
            @Override
            public User mapRow(ResultSet rs, int rowNum) throws SQLException {
                User user = new User();
                user.setUid(rs.getInt("uid"));
                user.setUsername(rs.getString("username"));
                user.setPassword(rs.getString("password"));
                user.setType(rs.getInt("type"));
                return user;
            }
        });
    }

    /**
     * 按用户ID查询
     * @param id
     * @return
     */
    @Override
    public User selectUserById(int id) {
        String sql = "select * from T_user where uid=" + id;
        User user = new User();
        this.jdbcTemplate.query(sql, new RowCallbackHandler() {
            @Override
            public void processRow(ResultSet resultSet) throws SQLException {
                user.setUid(resultSet.getInt("uid"));
                user.setUsername(resultSet.getString("username"));
                user.setPassword(resultSet.getString("password"));
                user.setType(resultSet.getInt("type"));
            }
        });
        return user;
    }
}
