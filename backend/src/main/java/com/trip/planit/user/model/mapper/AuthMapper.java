package com.trip.planit.user.model.mapper;

import com.trip.planit.user.model.dto.Auth;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface AuthMapper {
    Auth selectByUser(String userId); // user 존재 여부 확인
    int insertByAuth(Auth auth);
    int updateByAuth(Auth auth);
}
