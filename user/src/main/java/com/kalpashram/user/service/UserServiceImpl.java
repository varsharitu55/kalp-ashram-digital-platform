package com.kalpashram.user.service;

import com.kalpashram.user.dto.UserDto;
import com.kalpashram.user.entity.UserEntity;
import com.kalpashram.user.mapper.UserMapper;
import com.kalpashram.user.repository.UserRepository;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserServiceImpl implements IUserService {

    private UserRepository userRepository;

    public UserServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;


    }

    @Override
    public UserDto fetchUser(String mobileNumber) {
        UserEntity userDetail = userRepository.findByMobileNumber(mobileNumber);
        UserDto user = UserMapper.mapToUserDto(userDetail, new UserDto());
        return user;
    }
}
