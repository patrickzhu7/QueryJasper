package com.keste;

import java.util.HashMap;
import java.util.Map;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

@SpringBootApplication
public class QueryJasperApplication {

	public static void main(String[] args) {
		AnnotationConfigApplicationContext ctx = new AnnotationConfigApplicationContext();
        ctx.register(ReportsConfig.class);
        ctx.refresh();

        ReportGenerator reportGenerator = ctx.getBean(ReportGenerator.class);

        reportGenerator.setReportFileName("demoTB.jrxml");
        reportGenerator.compileReport();

        Map<String, Object> parameters = new HashMap<>();
        parameters.put("condition", "1");

        reportGenerator.setParameters(parameters);
        reportGenerator.fillReport();

        ReportExporter simpleExporter = ctx.getBean(ReportExporter.class);
        simpleExporter.setJasperPrint(reportGenerator.getJasperPrint());

        simpleExporter.exportToPdf("demoTB.pdf", "Keste");
		//SpringApplication.run(QueryJasperApplication.class, args);
	}

}
