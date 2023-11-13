package com.trip.planit.user.model.service;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.trip.planit.user.model.dto.User;
import com.trip.planit.user.model.mapper.UserMapper;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

	private final UserMapper userMapper;

	@Override
	public User loginUser(User user) throws Exception {
		return userMapper.selectByUser(user);
	}


	@Override
	public void registUser(User user) throws Exception {
		userMapper.insertByUser(user);
	}

	@Override
	public void deleteUser(String userId) throws Exception {
		userMapper.deleteByUserId(userId);
	}
	@Override
	public User findByUserId(String userId) throws Exception{
		return userMapper.findByUserId(userId);
	}

	@Override
	public void modifyUser(User user) throws Exception {
		userMapper.updateByUser(user);
	}


}
