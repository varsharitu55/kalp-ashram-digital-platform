package com.kalpashram.user.dto;

import jakarta.persistence.Column;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UserDto {
    private Long userId;


    private String name;


    private String email;


    private String mobileNumber;


    private String userRole;

}
