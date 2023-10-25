package com.dw.firstapp;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

record Person (String name, int age) {};
record Address (String firstLine, String city) {};

@Configuration
public class HelloWorldConfiguration {
	
	@Bean
	public String name() {
		return "This is bean method";
	}
	@Bean
	public int age() {
		return 20;
	}
	@Bean
	public Person person() {
		return new Person("Tom", 25);
	}
	@Bean(name = "address1")
	public Address address() {
		return new Address("Baker Street", "London");
	}
	
}
