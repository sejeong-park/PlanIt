package com.trip.planit.user.model.service;

import com.trip.planit.user.model.dto.Auth;

public interface AuthService {

    int setRefreshToken(Auth auth);
    Auth getRefreshToken(String userId);
    int updateRefreshToken(Auth auth);

}
