package com.kalpashram.user.service;

import com.kalpashram.user.dto.UserDto;
import com.kalpashram.user.entity.UserEntity;
import com.kalpashram.user.exception.ResourceNotFoundException;
import com.kalpashram.user.exception.UserAlreadyExistException;
import com.kalpashram.user.repository.UserRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(SpringExtension.class)
public class UserServiceTest {
    @Mock
    private UserRepository userRepository;
    @InjectMocks
    private UserServiceImpl userService;

    @Test
    void registerUser_Success() {
        UserDto userDto = new UserDto("varsha", "7838834930", "varsharitu55@gmail.com", "admin");
        //mock repo behavior
        when(userRepository.save(any(UserEntity.class))).thenReturn(new UserEntity());
        //Act
        assertDoesNotThrow(() -> userService.registerUser(userDto));
        //verify
        verify(userRepository, times(1)).save(any(UserEntity.class));

    }

    @Test
    void registerUser_AlreadyExist() {
        UserDto userDto = new UserDto("varsha", "7838834930", "varsharitu55@gmail.com", "admin");
        //mock repo behavior
        when(userRepository.findByMobileNumber("7838834930")).thenReturn(Optional.of(new UserEntity()));
        //Act and assert
        assertThrows(UserAlreadyExistException.class, () -> userService.registerUser(userDto));


    }

    @Test
    void fetchUser_Success() {

        UserEntity userEntity = new UserEntity();
        userEntity.setName("varsha");
        userEntity.setEmail("varsharitu55@gmail.com");
        userEntity.setMobileNumber("7838834930");
        userEntity.setUserRole("admin");
        //mock repo behavior
        when(userRepository.findByMobileNumber("7838834930")).thenReturn(Optional.of(userEntity));
        //Act and assert
        UserDto userDto = userService.fetchUser("7838834930");
        assertNotNull(userDto);
        assertEquals("varsha", userDto.getName());
        assertEquals("7838834930", userDto.getMobileNumber());
        assertEquals("varsharitu55@gmail.com", userDto.getEmail());

    }

    @Test
    void fetchUser_UserNotExist() {

        //mock repo behavior
        when(userRepository.findByMobileNumber("7838834930")).thenReturn(Optional.empty());
        //Act

        assertThrows(ResourceNotFoundException.class, () -> userService.fetchUser("7838834930"));


    }


}
