package com.keste;

import javax.sql.DataSource;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseBuilder;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseType;

@Configuration
public class ReportsConfig {
//	@Bean
//    public DataSource dataSource() {
//        return new EmbeddedDatabaseBuilder().setType(EmbeddedDatabaseType.HSQL).addScript("classpath:employee-schema.sql").build();
//    }

    @Bean
    public ReportGenerator reportFiller() {
        return new ReportGenerator();
    }

    @Bean
    public ReportExporter reportExporter() {
        return new ReportExporter();
    }
}
