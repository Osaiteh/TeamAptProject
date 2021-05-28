package com.glory.teamaptproject.service;

import com.glory.teamaptproject.dto.request.LoginRequest;
import com.glory.teamaptproject.dto.request.SignupRequest;
import com.glory.teamaptproject.dto.response.LoginResponse;
import com.glory.teamaptproject.dto.response.SignupResponse;
import com.glory.teamaptproject.exception.UserExistException;
import com.glory.teamaptproject.model.User;
import com.glory.teamaptproject.repository.UserRepository;
import com.glory.teamaptproject.security.JwtTokenProvider;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    private final UserRepository userRepository;
    private final AuthenticationManager authenticationManager;
    private final JwtTokenProvider jwtTokenProvider;
    private final PasswordEncoder passwordEncoder;

    public UserService(UserRepository userRepository, AuthenticationManager authenticationManager, JwtTokenProvider jwtTokenProvider, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.authenticationManager = authenticationManager;
        this.jwtTokenProvider = jwtTokenProvider;
        this.passwordEncoder = passwordEncoder;
    }

    public SignupResponse signup(SignupRequest signupRequest) throws UserExistException {
        User existingUser = userRepository.findByUsername(signupRequest.getUsername());
        if (existingUser != null) {
            throw new UserExistException("User with username exists already");
        }

        User user = new User();
        user.setUsername(signupRequest.getUsername());
        user.setPassword(passwordEncoder.encode(signupRequest.getPassword()));
        User createdUser = userRepository.save(user);

        // set the account number to become the newly generated id
        createdUser.setAccountNumber(user.getId());

        return new SignupResponse(createdUser.getUsername(), createdUser.getAccountNumber());

    }

    public LoginResponse login(LoginRequest loginRequest) throws Exception {
        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(loginRequest.getUsername(), loginRequest.getPassword()));

        SecurityContextHolder.getContext().setAuthentication(authentication);
        User user = (User) authentication.getPrincipal();
        String token = jwtTokenProvider.generateToken(user.getId().toString());

        return new LoginResponse(user.getUsername(), user.getAccountNumber(), token);
    }
}
