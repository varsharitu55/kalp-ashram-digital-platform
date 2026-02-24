package com.kalpashram.user.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.kalpashram.user.config.SecurityConfig;
import com.kalpashram.user.constants.UserConstants;
import com.kalpashram.user.dto.EnquiryRequestDto;
import com.kalpashram.user.dto.ResponseDto;
import com.kalpashram.user.dto.UserDto;
import com.kalpashram.user.service.EmailService;
import jakarta.validation.Valid;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Import;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.web.bind.annotation.RequestBody;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.doNothing;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith(SpringExtension.class)
@WebMvcTest(EnquiryController.class)
@Import(SecurityConfig.class)
public class EnquiryControllerTest {
    @Autowired
    MockMvc mockMvc;
    @MockBean
    EmailService emailService;
    @Autowired
    ObjectMapper objectMapper;
    @Test
    @WithMockUser(username = "admin", roles = {"ADMIN"})
    void sendEnquirySuccess( ) throws Exception {
        EnquiryRequestDto enquiry = new EnquiryRequestDto();
        enquiry.setEmail("v@gmail.com");
        enquiry.setName("varsha");
        enquiry.setPhone("12345678");
        enquiry.setMessage("Testing");
        ResponseDto responseDto = new ResponseDto(UserConstants.STATUS_200, UserConstants.MESSAGE_200);
        doNothing().when(emailService).sendEnquiryEmail(enquiry);
        mockMvc.perform(post("/api/enquiry")
                        .contentType("application/json").content(objectMapper.writeValueAsString(enquiry)))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.statusCode").value(responseDto.getStatusCode()))
                .andExpect(jsonPath("$.statusMessage").value(responseDto.getStatusMessage()));


    }
}
