package com.keste;

import java.util.HashMap;
import java.util.Map;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

@SpringBootApplication
public class QueryJasperApplication implements CommandLineRunner {
	
	Logger log = LogManager.getLogger(QueryJasperApplication.class);

	public static void main(String[] args) {
		SpringApplication.run(QueryJasperApplication.class, args);
	}
	
	@Override
	public void run(String... args) throws Exception {
		AnnotationConfigApplicationContext ctx = new AnnotationConfigApplicationContext();
        ctx.register(ReportsConfig.class);
        ctx.refresh();

        ReportGenerator reportGenerator = ctx.getBean(ReportGenerator.class);

        reportGenerator.setReportFileName("demoTB.jrxml");
        reportGenerator.compileReport();

        Map<String, Object> parameters = new HashMap<>();
        parameters.put("condition", 1);

        reportGenerator.setParameters(parameters);
        reportGenerator.fillReport();

        ReportExporter simpleExporter = ctx.getBean(ReportExporter.class);
        simpleExporter.setJasperPrint(reportGenerator.getJasperPrint());

        simpleExporter.exportToPdf("demoTB.pdf", "Keste");
        
        log.info("demoTB.pdf is in project folder");
	}

}
