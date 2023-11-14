package com.dw.jwtauthority.dto;

import java.util.Set;
import java.util.stream.Collectors;

import com.dw.jwtauthority.model.User;
import com.fasterxml.jackson.annotation.JsonProperty;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

public class UserDto {

	@NotNull
	@Size(min = 3, max = 50)
	private String username;

	@JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
	@NotNull
	@Size(min = 3, max = 100)
	private String password;

	@NotNull
	@Size(min = 3, max = 50)
	private String nickname;

	private Set<AuthorityDto> authorityDtoSet;
	
	public UserDto() {
		super();
	}

	public UserDto(@NotNull @Size(min = 3, max = 50) String username,
			@NotNull @Size(min = 3, max = 100) String password, @NotNull @Size(min = 3, max = 50) String nickname,
			Set<AuthorityDto> authorityDtoSet) {
		super();
		this.username = username;
		this.password = password;
		this.nickname = nickname;
		this.authorityDtoSet = authorityDtoSet;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getNickname() {
		return nickname;
	}

	public void setNickname(String nickname) {
		this.nickname = nickname;
	}

	public Set<AuthorityDto> getAuthorityDtoSet() {
		return authorityDtoSet;
	}

	public void setAuthorityDtoSet(Set<AuthorityDto> authorityDtoSet) {
		this.authorityDtoSet = authorityDtoSet;
	}

	public static UserDto from(User user) {
		if(user == null) return null;
		
		Set<AuthorityDto> authorityDtoSet = user.getAuthorities().stream()
				.map(authority -> new AuthorityDto(authority.getAuthorityName()))
				.collect(Collectors.toSet());
		
		return new UserDto(user.getUsername(), null, user.getNickname(), authorityDtoSet);
	}
	
}
