package com.keste;

import java.sql.SQLException;

import javax.sql.DataSource;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import oracle.jdbc.pool.OracleDataSource;

@Configuration
public class ReportsConfig {
	@Bean
    public DataSource dataSource() throws SQLException {
		OracleDataSource dataSource = new OracleDataSource();
        dataSource.setUser("apps");
        dataSource.setPassword("apps");
        dataSource.setURL("jdbc:oracle:thin:@ovm1099.keste.com:1521:EBSDB");
        dataSource.setImplicitCachingEnabled(true);
        dataSource.setFastConnectionFailoverEnabled(true);
        return dataSource;
    }

    @Bean
    public ReportGenerator reportFiller() {
        return new ReportGenerator();
    }

    @Bean
    public ReportExporter reportExporter() {
        return new ReportExporter();
    }
}
