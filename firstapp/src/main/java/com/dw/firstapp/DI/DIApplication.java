package com.dw.firstapp.DI;

import java.util.Arrays;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.ComponentScan;

@ComponentScan
public class DIApplication {

	public static void main(String[] args) {
		
		try (var context = new AnnotationConfigApplicationContext(
				DIApplication.class)) {
			Arrays.stream(context.getBeanDefinitionNames())
			.forEach(System.out::println);	
			System.out.println(context.getBean(MyBusiness.class));
		}
	}
}
