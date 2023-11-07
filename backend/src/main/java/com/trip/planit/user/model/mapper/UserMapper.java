package com.trip.planit.user.model.mapper;

import java.sql.SQLException;

import org.apache.ibatis.annotations.Mapper;

import com.trip.planit.user.model.dto.User;

@Mapper
public interface UserMapper {
	User selectByUser(User user) throws SQLException;

	void insertByUser(User user) throws SQLException;

	void deleteByUserId(String userId) throws SQLException;

	User findByUserId(String userID) throws SQLException;

	void updateByUser(User user) throws SQLException;
}
