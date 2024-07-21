package com.SpringBoot.api.SpringApi.Users;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.core.io.ResourceLoader;
import org.springframework.stereotype.Service;

import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;

@Service
public class ReportService {

    @Autowired
    private ResourceLoader resourceLoader;

    public byte[] generateReport(Map<String, Object> parameters, List<?> dataSource) throws JRException, IOException {        // Load and compile report template
        
    	Resource resource = resourceLoader.getResource("classpath:/reports/item-report.jrxml");
 		System.out.println("dakal1  " + resource);

        InputStream templateStream = resource.getInputStream();
 		System.out.println("dakal2  " + templateStream);

        JasperReport jasperReport = JasperCompileManager.compileReport(templateStream);
 		System.out.println("dakal3  " + jasperReport);

 		
 		List<Item> x = new ArrayList<>();
 		
 		x.add(new Item("Item 1"));
 		x.add(new Item("Item 2"));
 		x.add(new Item("Item 3"));
 		x.add(new Item("Item 4"));
 		x.add(new Item("Item 5"));


 		
        JasperPrint jasperPrint = JasperFillManager.fillReport(jasperReport, parameters, new JRBeanCollectionDataSource(x));
 		System.out.println("dakal4  " + jasperPrint);

        // Export the report to PDF
        return JasperExportManager.exportReportToPdf(jasperPrint);
    }
}
