package com.dw.firstapp;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.ServletRegistrationBean;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class FirstappApplication {

	public static void main(String[] args) {
		SpringApplication.run(FirstappApplication.class, args);
		
		var context = 
				new AnnotationConfigApplicationContext(HelloWorldConfiguration.class);
		System.out.println(context.getBean("personParams"));
	}
	
	@Bean
	public ServletRegistrationBean<MyServlet> myServlet() {
		ServletRegistrationBean<MyServlet> registrationBean = 
				new ServletRegistrationBean<>(new MyServlet(), "/my-servlet");
		return registrationBean;
	}
}
