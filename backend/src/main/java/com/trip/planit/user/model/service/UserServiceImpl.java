package com.trip.planit.user.model.service;

import java.sql.SQLException;
import java.util.List;

import org.springframework.stereotype.Service;

import com.trip.planit.user.model.dto.UserDto;
import com.trip.planit.user.model.mapper.UserMapper;

@Service
public class UserServiceImpl implements UserService{

	private final UserMapper userMapper;
	
	public UserServiceImpl(UserMapper userMapper) {
		super();
		this.userMapper = userMapper;
	}
	
	

	@Override
	public UserDto loginUser(UserDto userDto) throws SQLException {
		// TODO Auto-generated method stub
		return null;
	}



	@Override
	public List<UserDto> listUser() throws SQLException {
		// TODO Auto-generated method stub
		return userMapper.listUser();
	}

//	@Override
//	public UserDto loginUser(UserDto userDto) throws SQLException {
//		// TODO Auto-generated method stub
//		return userMapper.selectByUser(userDto);
//	}

	
}
