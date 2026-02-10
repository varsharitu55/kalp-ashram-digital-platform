package com.kalpashram.user.service;

import com.kalpashram.user.dto.UserDto;
import com.kalpashram.user.entity.UserEntity;
import com.kalpashram.user.exception.ResourceNotFoundException;
import com.kalpashram.user.exception.UserAlreadyExistException;
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
    public void registerUser(UserDto userDto) {

        UserEntity user = UserMapper.mapToEntity(userDto, new UserEntity());
        Optional<UserEntity> userDetail = userRepository.findByMobileNumber(user.getMobileNumber());
        if (userDetail.isPresent())
            throw new UserAlreadyExistException("User already exist with mobile number " + user.getMobileNumber());
        userRepository.save(user);

    }

    @Override
    public UserDto fetchUser(String mobileNumber) {
        UserEntity userDetail = userRepository.findByMobileNumber(mobileNumber).orElseThrow(() -> new ResourceNotFoundException("User with this mobile " + mobileNumber + " does not exit"));
        return UserMapper.mapToUserDto(userDetail, new UserDto());
       }

}
