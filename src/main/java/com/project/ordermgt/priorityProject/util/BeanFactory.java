package com.project.ordermgt.priorityProject.util;

import org.springframework.beans.factory.xml.XmlBeanDefinitionReader;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.GenericApplicationContext;
import org.springframework.core.io.ClassPathResource;

public class BeanFactory {
	
	private BeanFactory() {}
	
	private static ApplicationContext factory;
	public static ApplicationContext getContext() {
		if(factory == null) {
			initializeBeanFactory();
		}
		return factory;
	}
	
	private static void initializeBeanFactory() {
		GenericApplicationContext genericApplicationContext = new GenericApplicationContext();
		XmlBeanDefinitionReader xmlReader = new XmlBeanDefinitionReader(genericApplicationContext);
		xmlReader.loadBeanDefinitions(new ClassPathResource("spring/backOfficeAutomationDataSource-spring.xml"));
		xmlReader.loadBeanDefinitions(new ClassPathResource("spring/priority-spring.xml"));
		genericApplicationContext.refresh();
		factory = genericApplicationContext;
	}

}
