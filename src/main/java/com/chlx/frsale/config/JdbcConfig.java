package com.xiaowang.mesqle.config;

import com.alibaba.druid.pool.DruidDataSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;

import javax.sql.DataSource;

/**
 * 数据源的JDBC配置类
 */
//@Configuration
//@EnableConfigurationProperties(JdbcProperties.class)//指定加载哪个配置信息属性类
//@PropertySource("classpath:/jdbc.properties")//加载指定的Properties配置文件
public class JdbcConfig {
   /* @Value("${jdbc.driverClassName}")
    private String driverClassName;
    @Value("${jdbc.url}")
    private String url;
    @Value("${jdbc.username}")
    private String username;
    @Value("${jdbc.password}")
    private  String password;*/
    @Autowired
    private JdbcProperties jdbcProperties;
    /**
     * 实例化Druid
     */
    @Bean
    public DataSource getDataSource(){
        DruidDataSource source = new DruidDataSource();
        source.setPassword(this.jdbcProperties.getPassword());
        source.setUsername(this.jdbcProperties.getUsername());
        source.setDriverClassName(this.jdbcProperties.getDriverClassName());
        source.setUrl(this.jdbcProperties.getUrl());
        return source;
    }
}
