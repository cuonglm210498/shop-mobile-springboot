package com.lecuong.security.jwt.model;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class JwtPayLoad {

    private long id;
    private String userName;
    private String role;
}
