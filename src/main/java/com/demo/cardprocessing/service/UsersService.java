package com.demo.cardprocessing.service;

import com.demo.cardprocessing.dto.UserDto;
import org.springframework.security.core.userdetails.UserDetailsService;

public interface UsersService extends UserDetailsService {
    UserDto createUsers(UserDto userDetails);

    UserDto getUserDetailsByEmail(String email);

    UserDto getUserById(String userId, String authorization);
}
