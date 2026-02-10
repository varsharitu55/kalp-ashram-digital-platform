package com.kalpashram.user.controller;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


import com.fasterxml.jackson.databind.ObjectMapper;
import com.kalpashram.user.config.SecurityConfig;
import com.kalpashram.user.constants.UserConstants;
import com.kalpashram.user.dto.ResponseDto;
import com.kalpashram.user.dto.UserDto;
import com.kalpashram.user.exception.ResourceNotFoundException;
import com.kalpashram.user.exception.UserAlreadyExistException;
import com.kalpashram.user.service.IUserService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.ImportAutoConfiguration;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Import;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;

@ExtendWith(SpringExtension.class)
@WebMvcTest(UserController.class)
@Import(SecurityConfig.class)
public class UserControllerTest {

    @Autowired
    MockMvc mockMvc;
    @MockBean
    private IUserService userService;
    @Autowired
    ObjectMapper objectMapper;

    @Test
    @WithMockUser(username = "admin", roles = {"ADMIN"})
    void fetchUserDetail_Success() throws Exception {
        UserDto userDto = new UserDto("varsha", "7838834930", "varsharitu55@gmail.com", "admin");
        when(userService.fetchUser("7838834930")).thenReturn(userDto);
        mockMvc.perform(get("/api/fetchUser") // the base path
                        .param("mobileNumber", "7838834930")) // parameters
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.name").value("varsha"))
                .andExpect(jsonPath("$.email").value("varsharitu55@gmail.com"));


    }

    @Test
    @WithMockUser(username = "admin", roles = {"ADMIN"})
    void createUser_Success() throws Exception {
        UserDto request = new UserDto("varsha", "7838834930", "varsharitu55@gmail.com", "admin");
        ResponseDto responseDto = new ResponseDto(UserConstants.STATUS_201, UserConstants.MESSAGE_201);
        doNothing().when(userService).registerUser(any(UserDto.class));
        mockMvc.perform(post("/api/registerUser")
                        .contentType("application/json").content(objectMapper.writeValueAsString(request)))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.statusCode").value(responseDto.getStatusCode()))
                .andExpect(jsonPath("$.statusMessage").value(responseDto.getStatusMessage()));
    }

    @Test
    @WithMockUser(username = "admin", roles = {"ADMIN"})
    void createUser_UserAlreadyExist() throws Exception {
        UserDto request = new UserDto("varsha", "7838834930", "varsharitu55@gmail.com", "admin");
        doThrow(new UserAlreadyExistException("User already exist with mobile number "+ request.getMobileNumber())).when(userService).registerUser(any(UserDto.class));
         mockMvc.perform(post("/api/registerUser").contentType("application/json").content(objectMapper.writeValueAsString(request)))
                .andExpect(status().isBadRequest())
                .andExpect(jsonPath("$.errorCode").value("BAD_REQUEST"))
                .andExpect(jsonPath("$.errorMessage").value("User already exist with mobile number "+ request.getMobileNumber()));

    }
    @Test
    @WithMockUser(username = "admin", roles = {"ADMIN"})
    void createUser_UserDetailNotValid() throws Exception {
        UserDto request = new UserDto("varsha", "783883493", "varsharitu55@gmail.com", "admin");
        doThrow(new ResourceNotFoundException("Mobile number must be 10 digits")).when(userService).registerUser(any(UserDto.class));
        mockMvc.perform(post("/api/registerUser").contentType("application/json").content(objectMapper.writeValueAsString(request)))
                .andExpect(status().isBadRequest())
                .andExpect(jsonPath("$.mobileNumber").value("Mobile number must be 10 digits"));

    }

    @Test
    void fetchUser_NotFound() throws Exception {
        String mobileNumber = "1234567890";
        when(userService.fetchUser(mobileNumber)).thenThrow(new ResourceNotFoundException("Resource not found"));
        mockMvc.perform(get("/api/fetchUser").param("mobileNumber", mobileNumber))
                .andExpect(status().isBadRequest())
                .andExpect(jsonPath("$.errorCode").value("BAD_REQUEST"))
                .andExpect(jsonPath("$.errorMessage").value("Resource not found"));
    }
    @Test
    void fetchUser_UserNotValid() throws Exception {
        String mobileNumber = "123456789";
        when(userService.fetchUser(mobileNumber)).thenThrow(new ResourceNotFoundException("Mobile number must be 10 digits"));
        mockMvc.perform(get("/api/fetchUser").param("mobileNumber", mobileNumber))
                .andExpect(status().isBadRequest())
                .andExpect(jsonPath("$.mobileNumber").value("Mobile number must be 10 digits"));

    }

}
