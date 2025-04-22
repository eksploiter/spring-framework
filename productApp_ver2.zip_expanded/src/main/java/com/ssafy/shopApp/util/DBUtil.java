package com.ssafy.shopApp.util;

import java.sql.Connection;
import java.sql.SQLException;

import javax.sql.DataSource;

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;

public class DBUtil {
	private static final String DRIVER_CLASS_NAME = "com.mysql.cj.jdbc.Driver";
    private static final String URL = "jdbc:mysql://127.0.0.1:3306/ssafydb";
    private static final String USER = "ssafy";
    private static final String PASSWORD = "ssafy";
    private static DataSource ds = null;
    private static DBUtil util = new DBUtil();
    
    
    public static DBUtil getUtil() {
        return util;
    }

    private DBUtil() {
        init();
    }
    
    public void init() {
        HikariConfig config = new HikariConfig();
        config.setJdbcUrl(URL);
        config.setUsername(USER);
        config.setPassword(PASSWORD);
        config.setDriverClassName(DRIVER_CLASS_NAME);

        config.setMinimumIdle(3);
        config.setMaximumPoolSize(5);
        config.setIdleTimeout(1000 * 60 * 5);
        config.setConnectionTimeout(1000 * 60 * 10);
        config.addDataSourceProperty("profileSQL", "true");
        ds = new HikariDataSource(config);
    }
    public static Connection getConnection() throws SQLException {
        return ds.getConnection();
    }
    
    public static void close(AutoCloseable... closeables) {
    	for (AutoCloseable res : closeables) {
			try {
				res.close();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
    }
}