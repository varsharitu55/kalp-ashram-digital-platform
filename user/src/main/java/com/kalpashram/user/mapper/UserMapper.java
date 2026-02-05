package com.kalpashram.user.mapper;

import com.kalpashram.user.dto.UserDto;
import com.kalpashram.user.entity.UserEntity;

import java.util.Optional;

public class UserMapper {
    public static UserDto mapToUserDto(UserEntity user, UserDto userDto) {
        userDto.setUserId(user.getUserId());
        userDto.setName(user.getName());
        userDto.setUserRole(user.getUserRole());
        userDto.setEmail(user.getEmail());
        userDto.setMobileNumber(user.getMobileNumber());
        return userDto;
    }

    public static UserEntity mapToEntity(UserDto userDto, UserEntity user) {
        user.setName(userDto.getName());
        user.setEmail(userDto.getEmail());
        user.setUserRole(userDto.getUserRole());
        user.setMobileNumber(userDto.getMobileNumber());
        return user;
    }
}
