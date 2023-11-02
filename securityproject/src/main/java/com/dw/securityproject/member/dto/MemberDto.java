package com.dw.securityproject.member.dto;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import com.dw.securityproject.common.status.Gender;

public class MemberDto {
	private long id;
	
	private String loginId;
	
	private String password;
	
	private String name;
	
	private String birthDate;
	
	private String gender;
	
	private String email;

	public MemberDto() {
		super();
	}

	public MemberDto(long id, String loginId, String password, String name, String birthDate, String gender,
			String email) {
		super();
		this.id = id;
		this.loginId = loginId;
		this.password = password;
		this.name = name;
		this.birthDate = birthDate;
		this.gender = gender;
		this.email = email;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getLoginId() {
		return loginId;
	}

	public void setLoginId(String loginId) {
		this.loginId = loginId;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public LocalDate getBirthDate() {
		return LocalDate.parse(birthDate, 
				DateTimeFormatter.ofPattern("yyyy-MM-dd"));
	}

	public void setBirthDate(String birthDate) {
		this.birthDate = birthDate;
	}

	public Gender getGender() {
		return Gender.valueOf(gender);
	}

	public void setGender(String gender) {
		this.gender = gender;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}
	
}
