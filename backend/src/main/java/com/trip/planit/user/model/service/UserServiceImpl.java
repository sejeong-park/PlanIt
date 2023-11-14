package com.trip.planit.user.model.service;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.trip.planit.user.model.dto.User;
import com.trip.planit.user.model.mapper.UserMapper;

import java.util.Map;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

	private final UserMapper userMapper;

	@Override
	public User loginUser(User user) throws Exception {
		return userMapper.selectByUser(user);
	}


	@Override
	public int registUser(User user) throws Exception {
		return userMapper.insertByUser(user);
	}

	@Override
	public int deleteUser(String userId) throws Exception {
		return userMapper.deleteByUserId(userId);
	}
	@Override
	public User findByUserId(String userId) throws Exception{
		return userMapper.findByUserId(userId);
	}

	@Override
	public boolean modify(Map<String, String> map) throws Exception {
		int result = userMapper.updateByUser(map); // 수정
		return result == 1 ? true : false;
	}


}
