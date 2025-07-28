package com.emergency.drill;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

@Component
public class DatabaseTest implements CommandLineRunner {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Override
    public void run(String... args) throws Exception {
        System.out.println("测试数据库连接...");
        String sql = "SELECT 1 FROM DUAL";  // 达梦数据库的测试SQL
        jdbcTemplate.queryForObject(sql, Integer.class);
        System.out.println("数据库连接成功！");
    }
}