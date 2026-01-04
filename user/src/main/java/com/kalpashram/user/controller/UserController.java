package com.kalpashram.user.controller;

import com.kalpashram.user.dto.UserDto;
import com.kalpashram.user.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(path="/api" , produces = {MediaType.APPLICATION_JSON_VALUE})
public class UserController {

    private IUserService iUserService;

    public UserController(IUserService iUserService) {

        this.iUserService = iUserService;
    }

    @GetMapping("/fetchUser")
    public ResponseEntity<UserDto> fetchUserDetail(@RequestParam String mobileNumber)
    {
      UserDto userDetail = iUserService.fetchUser(mobileNumber);
      return  ResponseEntity.status(HttpStatus.OK).body(userDetail);
    }

}
