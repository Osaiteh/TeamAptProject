package com.glory.teamaptproject.security;

import com.glory.teamaptproject.model.User;
import com.glory.teamaptproject.repository.UserRepository;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Service
public class CustomUserDetailsService implements UserDetailsService {

    private final UserRepository userRepository;

    public CustomUserDetailsService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String username)  {
        User user = userRepository.findByUsername(username);
        return User.create(user);
    }

    @Transactional
    public UserDetails loadById(Long id) {
        User user = userRepository.getOne(id);
        return User.create(user);
    }
}
