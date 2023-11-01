package com.dw.jdbcproject.model;

public class Member {
	
	private long id;
	
	private String name;

	public Member() {
		super();
	}

	public Member(long id, String name) {
		super();
		this.id = id;
		this.name = name;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}	
	
}
