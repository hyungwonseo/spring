package com.dw.firstapp;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;

record Person (String name, int age, Address address) {};
record Address (String firstLine, String city) {};

@Configuration
public class HelloWorldConfiguration {
	
	@Bean
	public String name() {
		return "Sam";
	}
	@Bean
	public int age() {
		return 20;
	}
	@Bean
	public Person person() {
		return new Person("Tom", 25, new Address("Main Street", "NY"));
	}
	@Bean
	@Primary
	public Person person2() {
		return new Person("Steve", 17, new Address("Center Rd", "DJ"));
	}
	@Bean
	public Person personMethodCall() {
		return new Person(name(), age(), address());
	}
	@Bean
	public Person personParams(String name, int age, 
			@Qualifier("addressQualifier") Address address) {
		return new Person(name, age, address);
	}
	@Bean(name = "addressBaker")
	@Qualifier("addressQualifier")
	public Address address() {
		return new Address("Baker Street", "London");
	}
	@Bean
	@Qualifier("address2Qualifier")
	public Address address2() {
		return new Address("Baeul-2ro", "Daejeon");
	}
	
}
