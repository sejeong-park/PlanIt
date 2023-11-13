package com.trip.planit.user.model.service;

import com.trip.planit.user.model.dto.User;

public interface UserService {
	User loginUser(User user) throws Exception;

	void registUser(User user) throws Exception;

	void deleteUser(String userId) throws Exception;

	User findByUserId(String userId) throws Exception;

	void modifyUser(User user) throws Exception;
}
