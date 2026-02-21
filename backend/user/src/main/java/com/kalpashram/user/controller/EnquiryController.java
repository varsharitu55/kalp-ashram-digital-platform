package com.kalpashram.user.controller;

import com.kalpashram.user.constants.UserConstants;
import com.kalpashram.user.dto.EnquiryRequestDto;
import com.kalpashram.user.dto.ResponseDto;
import com.kalpashram.user.dto.UserDto;
import com.kalpashram.user.service.EmailService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

// Add insert Enquiry , showEnquiry
@RestController
@RequestMapping(path = "/api", produces = {MediaType.APPLICATION_JSON_VALUE})
@CrossOrigin(origins = "http://localhost:4200")
public class EnquiryController {

    private EmailService emailService;

    public EnquiryController(EmailService emailService) {
        this.emailService = emailService;
    }

    @PostMapping("/enquiry")
    @Operation(summary = "Enquiry", description = "Send enquiry with user detail and message")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Enquiry sent successfully"),
            @ApiResponse(responseCode = "400", description = "Validation error")
    })
    public ResponseEntity<ResponseDto> registerUser(@Valid @RequestBody EnquiryRequestDto enquiry) {
        emailService.sendEnquiryEmail(enquiry);
        return ResponseEntity.status(HttpStatus.CREATED).body(new ResponseDto(UserConstants.STATUS_400, UserConstants.MESSAGE_400));
    }
}
