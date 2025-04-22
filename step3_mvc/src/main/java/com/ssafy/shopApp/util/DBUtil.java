package com.ssafy.shopApp.util;

import java.sql.Connection;
import java.sql.SQLException;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.RequestMapping;

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Component
public class DBUtil {
    /** ConnectionPool와 관련된 코드 */
    private final DataSource ds;
  
  /**  싱글톤 관련된 코드 (지금은 필요 없음 - ProductService에서 DI를 진행함) */
//  private static DBUtil util = new DBUtil();
//  public DBUtil getUtil() {return util;}
//  private DBUtil() {init();}
	
    
    public Connection getConnection() throws SQLException {
        return ds.getConnection();
    }
    
    public void close(AutoCloseable... closeables) {
    	for (AutoCloseable res : closeables) {
			try {
				res.close();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
    }
}