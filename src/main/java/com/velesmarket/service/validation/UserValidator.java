package com.velesmarket.service.validation;

import com.velesmarket.domain.UserDto;
import com.velesmarket.exception.ValidationException;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

@Component
public class UserValidator {

    public void validate(UserDto userDto) {
        // написать проверку правильности полей
        if (!StringUtils.hasText(userDto.getLogin())) {
            throw new ValidationException("Incorrect login");
        }
    }
}
