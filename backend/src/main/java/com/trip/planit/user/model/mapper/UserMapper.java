package com.trip.planit.user.model.mapper;

import java.sql.SQLException;
import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.trip.planit.user.model.dto.UserDto;

@Mapper
public interface UserMapper {
//	UserDto selectByUser(UserDto userDto) throws SQLException;
	List<UserDto> listUser() throws SQLException;
}
