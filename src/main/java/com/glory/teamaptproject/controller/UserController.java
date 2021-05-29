package com.glory.teamaptproject.controller;

import com.glory.teamaptproject.dto.request.LoginRequest;
import com.glory.teamaptproject.dto.request.SignupRequest;
import com.glory.teamaptproject.dto.response.LoginResponse;
import com.glory.teamaptproject.dto.response.SignupResponse;
import com.glory.teamaptproject.exception.UserExistException;
import com.glory.teamaptproject.service.UserService;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/user")
public class UserController {

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @RequestMapping(path = "/signup", method = RequestMethod.POST)
    public SignupResponse signup(@RequestBody SignupRequest signupRequest) throws UserExistException {
        return userService.signup(signupRequest);
    }

    @RequestMapping(path = "/login", method = RequestMethod.POST)
    public LoginResponse login(@RequestBody LoginRequest loginRequest) throws Exception {
        return userService.login(loginRequest);
    }
}
