package com.velesmarket.service;

import com.velesmarket.domain.UserDto;

public interface UserService {

    UserDto getUser(String login);

    UserDto registrate(UserDto userDto);

}
