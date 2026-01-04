package com.kalpashram.user.service;

import com.kalpashram.user.dto.UserDto;
import org.springframework.stereotype.Service;

@Service
public interface IUserService {
    UserDto fetchUser(String mobileNumber);
}
