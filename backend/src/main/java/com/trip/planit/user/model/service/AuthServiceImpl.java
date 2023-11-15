package com.trip.planit.user.model.service;

import com.trip.planit.user.model.dto.Auth;
import com.trip.planit.user.model.mapper.AuthMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthServiceImpl implements AuthService{

    private final AuthMapper jwtMapper;

    @Override
    public int setRefreshToken(Auth auth) {
        return jwtMapper.insertByAuth(auth);
    }

    @Override
    public Auth getRefreshToken(String userId) {
        return jwtMapper.selectByUser(userId);
    }

    @Override
    public int updateRefreshToken(Auth auth) {
        return jwtMapper.updateByAuth(auth);
    }
}
