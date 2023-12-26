package com.trip.planit.user.model.service;

import com.trip.planit.user.model.dto.User;

import java.util.Map;

public interface UserService {
	User loginUser(User user) throws Exception;

	int registUser(User user) throws Exception;

	int deleteUser(String userId) throws Exception;

	User findByUserId(String userId) throws Exception;

	boolean modify(Map<String, String> map) throws Exception;
}
