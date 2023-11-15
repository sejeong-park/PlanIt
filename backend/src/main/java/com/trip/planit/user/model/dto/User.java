package com.trip.planit.user.model.dto;


import lombok.Data;

@Data
public class User {
	private String userId;
	private String userPassword;
	private String userName;
	private String email;
}
