package com.ssafy.shopApp.model.dto;

import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
public class UserDTO {
	private String userId;
	private String password;
	private String name;
	private String email;
	
	public UserDTO(String userid, String password, String name, String email) {
		this(password, name, email);
		this.userId = userid;
	}
	
	@Builder
	public UserDTO(String password, String name, String email) {
		super();
		this.password = password;
		this.name = name;
		this.email = email;
	}
	
}

