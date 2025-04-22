package com.ssafy.shopApp.config;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;

@Configuration
public class AppConfig {

	private final String DRIVER_CLASS_NAME;
    private final String URL;
    private final String USER;
    private final String PASSWORD;

    
    public AppConfig(@Value("${db.datasoruce.driver.class.name}") String driverClassName, 
    				@Value("${db.datasoruce.url}") String url, 
    				@Value("${db.datasoruce.user}") String user, 
    				@Value("${db.datasoruce.password}") String password) {
		super();
		DRIVER_CLASS_NAME = driverClassName;
		URL = url;
		USER = user;
		PASSWORD = password;
	}

	@Bean
	DataSource hikari() {
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
        return new HikariDataSource(config);
	}
    
//    @Bean
//    public TransactionManager transactionManager(DataSource ds) {
//    	return new DataSourceTransactionManager(ds);
//    }
}
