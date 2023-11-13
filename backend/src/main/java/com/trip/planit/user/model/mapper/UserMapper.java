package com.trip.planit.user.model.mapper;

import java.sql.SQLException;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;

import com.trip.planit.user.model.dto.User;

@Mapper
public interface UserMapper {
	User selectByUser(User user) throws SQLException;

	int insertByUser(User user) throws SQLException;

	int deleteByUserId(String userId) throws SQLException;

	User findByUserId(String userID) throws SQLException;

	int updateByUser(Map<String, String> map) throws SQLException;
}
