package com.kalpashram.user.controller;

import com.kalpashram.user.constants.UserConstants;
import com.kalpashram.user.dto.ResponseDto;
import com.kalpashram.user.dto.UserDto;
import com.kalpashram.user.entity.UserEntity;
import com.kalpashram.user.service.IUserService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Pattern;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(path = "/api", produces = {MediaType.APPLICATION_JSON_VALUE})
@CrossOrigin(origins = "http://localhost:4200")
public class UserController {

    private IUserService iUserService;

    public UserController(IUserService iUserService) {

        this.iUserService = iUserService;
    }

    @GetMapping("/fetchUser")
    @Validated
    @Operation(summary = "Fetch user details", description = "Returns user details for given mobile number")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "User fetched successfully"),
            @ApiResponse(responseCode = "400", description = "User not found")
    })
    public ResponseEntity<UserDto> fetchUserDetail(@RequestParam @Pattern(regexp = "(^$|[0-9]{10})", message = "Mobile number must be 10 digits") String mobileNumber) {
        UserDto userDetail = iUserService.fetchUser(mobileNumber);
        return ResponseEntity.status(HttpStatus.OK).body(userDetail);
    }

    @PostMapping("/registerUser")
    @Operation(summary = "Create a new user", description = "Registers a new user with name and mobile number")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "User created successfully"),
            @ApiResponse(responseCode = "400", description = "Validation error or user already exists")
    })
    public ResponseEntity<ResponseDto> registerUser(@Valid @RequestBody UserDto user) {
        iUserService.registerUser(user);
        return ResponseEntity.status(HttpStatus.CREATED).body(new ResponseDto(UserConstants.STATUS_201, UserConstants.MESSAGE_201));
    }

}
