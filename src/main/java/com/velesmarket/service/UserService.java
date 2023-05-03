package com.velesmarket.service;

import com.velesmarket.domain.UserDto;
import org.springframework.security.core.userdetails.UserDetailsService;

public interface UserService extends UserDetailsService {

    UserDto login(UserDto userDto);

    UserDto registrate(UserDto userDto);

}
