package com.kalpashram.user.controller;

import com.kalpashram.user.constants.UserConstants;
import com.kalpashram.user.dto.ResponseDto;
import com.kalpashram.user.dto.UserDto;
import com.kalpashram.user.service.IUserService;
import com.kalpashram.user.service.KeycloakAdminService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Pattern;
import org.keycloak.representations.idm.UserRepresentation;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path = "/api", produces = {MediaType.APPLICATION_JSON_VALUE})
@CrossOrigin(origins = "http://localhost:4200")
public class UserController {

    private IUserService iUserService;
    private final KeycloakAdminService keycloakAdminService;

    public UserController(IUserService iUserService, KeycloakAdminService keycloakAdminService) {

        this.iUserService = iUserService;
        this.keycloakAdminService = new KeycloakAdminService();
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

    @PreAuthorize("hasRole('ADMIN')")
    @GetMapping("/allUsers")
    public List<UserRepresentation> getAllUsers(Authentication auth) {
        //keycloak.realm("myrealm").users().search("username", first, max);
        System.out.println("user: "+auth.getPrincipal());
        return keycloakAdminService.getAllUsers("kalpashramDigital"); // your realm name
    }
//    @GetMapping("/allUsers")
//    public List<UserRepresentation> getAllUsers(Authentication auth) {
//        //keycloak.realm("myrealm").users().search("username", first, max);
//        Jwt jwt = (Jwt) auth.getPrincipal();
//
//        // Get raw token
//        String tokenValue = jwt.getTokenValue();
//
//        // Optional: get claims
//        String username = jwt.getClaimAsString("preferred_username");
//
//        System.out.println("JWT token: " + tokenValue);
//        System.out.println("Username: " + username);
//
//        return keycloakAdminService.getAllUsers("kalpashramDigital"); // your realm name
//    }
    @GetMapping("/debugToken")
    public String debugToken(@RequestHeader("Authorization") String authHeader) {
        System.out.println("Authorization Header: " + authHeader);
        // Remove "Bearer " prefix
        if (authHeader != null && authHeader.startsWith("Bearer ")) {
            String rawJwt = authHeader.substring(7);
            System.out.println("Raw JWT: " + rawJwt);
            return rawJwt;
        }
        return "No token";
    }
}
