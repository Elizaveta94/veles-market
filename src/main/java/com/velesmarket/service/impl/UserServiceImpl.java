package com.velesmarket.service.impl;

import com.velesmarket.domain.UserDto;
import com.velesmarket.persist.entity.UserEntity;
import com.velesmarket.persist.repository.UserRepository;
import com.velesmarket.service.UserService;
import com.velesmarket.service.mapper.UserMapper;
import com.velesmarket.service.validation.UserValidator;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService, UserDetailsService {

    private final UserRepository userRepository;
    private final UserValidator userValidator;
    private final PasswordEncoder passwordEncoder;
    private final UserMapper userMapper;

    @Override
    public UserDto getUser(String login) {
        Optional<UserEntity> opUserEntity = userRepository.findByLogin(login);
        UserEntity userEntity = opUserEntity.orElseThrow(() -> new UsernameNotFoundException(login));
        return userMapper.mapToDto(userEntity);
    }

    @Override
    @Transactional
    public UserDto registrate(UserDto userDto) {
        userValidator.validate(userDto);
        UserEntity userEntity = userMapper.mapToEntity(userDto);
        userEntity.setPassword(passwordEncoder.encode(userDto.getPassword()));
        UserEntity savedEntity = userRepository.save(userEntity);
        return userMapper.mapToDto(savedEntity);
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Optional<UserEntity> opUserEntity = userRepository.findByLogin(username);
        UserEntity userEntity = opUserEntity.orElseThrow(() -> new UsernameNotFoundException(username));
        return User.withUsername(username)
                .password(userEntity.getPassword())
                .authorities("USER")
                .build();
    }

    @Override
    public  UserDto update(UserDto userDto) {
        userValidator.validate(userDto);
        UserEntity userEntity = userMapper.mapToEntity(userDto);
        getUser("login");
        if (!userDto.getFirstName().equals(userEntity.getFirstName())) {
            userEntity.setFirstName(userDto.getFirstName());
        }
        if (!userDto.getLastName().equals(userEntity.getLastName())) {
            userEntity.setLastName(userDto.getLastName());
        }
        if (!userDto.getPassword().equals(userEntity.getPassword())) {
            userEntity.setPassword(userDto.getPassword());
        }
        if (!userDto.getEmail().equals(userEntity.getEmail())) {
            userEntity.setEmail(userDto.getEmail());
        }
        if (!userDto.getMobileNumber().equals(userEntity.getMobileNumber())) {
            userEntity.setMobileNumber(userDto.getMobileNumber());
        }

        //return null;
    }
}
