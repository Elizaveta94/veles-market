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
    @Transactional
    public UserDto update(UserDto updatedUserDto, String userNameToUpdate) {
        userValidator.validate(updatedUserDto);
        Optional<UserEntity> opUserEntity = userRepository.findByLogin(userNameToUpdate);
        UserEntity userEntity = opUserEntity.orElseThrow(() -> new UsernameNotFoundException(userNameToUpdate));

        if (!updatedUserDto.getFirstName().equals(userEntity.getFirstName())) {
            userEntity.setFirstName(updatedUserDto.getFirstName());
        }
        if (!updatedUserDto.getLastName().equals(userEntity.getLastName())) {
            userEntity.setLastName(updatedUserDto.getLastName());
        }
        if (!passwordEncoder.encode(updatedUserDto.getPassword()).equals(userEntity.getPassword())) {
            userEntity.setPassword(passwordEncoder.encode(updatedUserDto.getPassword()));
        }
        if (!updatedUserDto.getEmail().equals(userEntity.getEmail())) {
            userEntity.setEmail(updatedUserDto.getEmail());
        }
        if (!updatedUserDto.getMobileNumber().equals(userEntity.getMobileNumber())) {
            userEntity.setMobileNumber(updatedUserDto.getMobileNumber());
        }
        if (!updatedUserDto.getLogin().equals(userEntity.getLogin())) {
            userEntity.setLogin(updatedUserDto.getLogin());
        }
        UserEntity savedEntity = userRepository.save(userEntity);
        return userMapper.mapToDto(userEntity);
    }
}
