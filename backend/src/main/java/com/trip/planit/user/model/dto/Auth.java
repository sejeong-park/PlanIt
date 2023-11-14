package com.trip.planit.user.model.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class Auth {

    private String userId;
    private String refreshToken;

}
