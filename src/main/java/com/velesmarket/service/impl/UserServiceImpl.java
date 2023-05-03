package com.velesmarket.service.impl;

import com.velesmarket.domain.UserDto;
import com.velesmarket.persist.entity.UserEntity;
import com.velesmarket.persist.repository.UserRepository;
import com.velesmarket.service.UserService;
import com.velesmarket.service.mapper.UserMapper;
import com.velesmarket.service.utils.PasswordEncoder;
import com.velesmarket.service.validation.UserValidator;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final UserValidator userValidator;
    private final PasswordEncoder passwordEncoder;
    private final UserMapper userMapper;

    @Override
    public UserDto login(UserDto userDto) {
        return null;
    }

    @Override
    @Transactional
    public UserDto registrate(UserDto userDto) {
        userValidator.validate(userDto);
        UserEntity userEntity = userMapper.mapToEntity(userDto);
        userEntity.setPassword(passwordEncoder.encodePassword(userDto.getPassword()));
        UserEntity savedEntity = userRepository.save(userEntity);
        return userMapper.mapToDto(savedEntity);
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        return null;
    }
}
