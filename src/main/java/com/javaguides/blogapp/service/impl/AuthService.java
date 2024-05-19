package com.javaguides.blogapp.service.impl;

import com.javaguides.blogapp.dto.LoginDto;
import com.javaguides.blogapp.dto.RegisterDto;
import com.javaguides.blogapp.exception.BlogAPIException;
import com.javaguides.blogapp.model.Role;
import com.javaguides.blogapp.model.User;
import com.javaguides.blogapp.repository.IRoleRepository;
import com.javaguides.blogapp.repository.IUserRepository;
import com.javaguides.blogapp.service.IAuthService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Set;

@Service
public class AuthService implements IAuthService {

    private AuthenticationManager authenticationManager;
    private IUserRepository userRepository;
    private IRoleRepository roleRepository;
    private PasswordEncoder passwordEncoder;

    @Autowired
    public AuthService(AuthenticationManager authenticationManager, IUserRepository userRepository, IRoleRepository roleRepository, PasswordEncoder passwordEncoder) {
        this.authenticationManager = authenticationManager;
        this.userRepository = userRepository;
        this.roleRepository = roleRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public String login(LoginDto loginDto) {

        Authentication  authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(loginDto.getUsernameOrEmail(), loginDto.getPassword()));

        SecurityContextHolder.getContext().setAuthentication(authentication);

        return "User Login Successfuly";
    }

    @Override
    public String register(RegisterDto registerDto) {

        //check username exists
        if(userRepository.existsByUsername(registerDto.getUsername())) {
            throw new BlogAPIException(HttpStatus.BAD_REQUEST, "username with: " + registerDto.getUsername() + " already exists");
        }

        //check email exists
        if(userRepository.existsByEmail(registerDto.getEmail())) {
            throw new BlogAPIException(HttpStatus.BAD_REQUEST, "email with : " + registerDto.getEmail() + " already exists");
        }

        User user = new User();
        user.setName(registerDto.getName());
        user.setUsername(registerDto.getUsername());
        user.setEmail(registerDto.getEmail());
        user.setPassword(passwordEncoder.encode(registerDto.getPassword()));

        Set<Role> roles = new HashSet<>();
        Role userRole = roleRepository.findByName("ROLE_USER").get();
        roles.add(userRole);
        user.setRoles(roles);

        userRepository.save(user);

        return "user registered successfuly";
    }
}
