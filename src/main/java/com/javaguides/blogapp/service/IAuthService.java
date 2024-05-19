package com.javaguides.blogapp.service;

import com.javaguides.blogapp.dto.LoginDto;
import com.javaguides.blogapp.dto.RegisterDto;

public interface IAuthService {
    String login(LoginDto loginDto);
    String register(RegisterDto registerDto);
}
