package com.trip.planit.user.model.service;

import java.sql.SQLException;
import java.util.List;

import com.trip.planit.user.model.dto.UserDto;

public interface UserService {
	UserDto loginUser(UserDto userDto) throws SQLException;
	List<UserDto> listUser() throws SQLException;

}
