package com.ssafy.live.util;

import java.sql.Connection;
import java.sql.SQLException;

import org.springframework.stereotype.Component;

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;

@Component
public class DBUtil {
    private final String driverClassName = "com.mysql.cj.jdbc.Driver";
    private final String url = "jdbc:mysql://localhost:3306/ssafylive?serverTimezone=UTC";
    private final String user = "ssafy";
    private final String password = "ssafy";
    private HikariDataSource ds;

    private DBUtil() {
        init();
    }

    private void init() {
        System.out.println("DB util 초기화");
        HikariConfig config = new HikariConfig();
        config.setJdbcUrl(url);
        config.setUsername(user);
        config.setPassword(password);
        config.setDriverClassName(driverClassName);

        config.setMinimumIdle(3);
        config.setMaximumPoolSize(5);
        config.setIdleTimeout(1000 * 60 * 5);
        config.setConnectionTimeout(1000 * 60 * 10);
        config.addDataSourceProperty("profileSQL", "true");
        ds = new HikariDataSource(config);
    }

    public Connection getConnection() throws SQLException {
        return ds.getConnection();
    }

    public void close(AutoCloseable... closeables) {
        for (AutoCloseable c : closeables) {
            if (c != null) {
                try {
                    c.close();
                } catch (Exception ignore) {

                }
            }
        }
    }

}
