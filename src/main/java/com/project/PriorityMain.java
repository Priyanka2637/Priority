package com.project;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jsonb.JsonbAutoConfiguration;
import org.springframework.util.StringUtils;

import com.project.ordermgt.priorityProject.util.BeanFactory;

@EnableAutoConfiguration(exclude = {JsonbAutoConfiguration.class})
@SpringBootApplication
public class PriorityMain {
	
	private static Log log = LogFactory.getLog(PriorityMain.class);
	
	public PriorityMain() {
	}
	
	public static void main(String[] args) {
		SpringApplication.run(PriorityMain.class, args);
		
		long batchNumber = 0;
		String batchNumberStr = System.getProperty("batchNumber");
		log.info("PriorityMain.run() :: Running for bathchNumber------------=>"+batchNumberStr);
		if(!StringUtils.isEmpty(batchNumberStr)) {
			batchNumber = Long.parseLong(batchNumberStr);
		}
		while (true) {
			try {
				log.info("Running PriorityMain Class");
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}
}
