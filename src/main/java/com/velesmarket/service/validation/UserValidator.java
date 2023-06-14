package com.velesmarket.service.validation;

import com.velesmarket.domain.UserDto;
import com.velesmarket.exception.ValidationException;
import com.velesmarket.persist.repository.UserRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

import java.util.regex.Pattern;

@Component
@AllArgsConstructor
public class UserValidator {
    private final UserRepository userRepository;

    public void validate(UserDto userDto) {
        validateFirstName(userDto.getFirstName());
        validateEmail(userDto.getEmail());
        validateLogin(userDto.getLogin());
        validatePassword(userDto.getPassword());
        validatePhoneNumber(userDto.getMobileNumber());
    }

    private void validateFirstName(String firstName) {
        if (!StringUtils.hasText(firstName)) {
            throw new ValidationException("First name is mandatory!");
        }
    }

    private void validateEmail(String email) {
        if (!StringUtils.hasText(email)) {
            throw new ValidationException("Email is mandatory!");
        }
        String rfcEmailPattern = "^[a-zA-Z0-9_!#$%&'*+/=?`{|}~^.-]+@[a-zA-Z0-9.-]+$";
        if (!Pattern.compile(rfcEmailPattern).matcher(email).matches()) {
            throw new ValidationException("Incorrect email format: " + email);
        }
        if (userRepository.findByEmail(email).isPresent()) {
            throw new ValidationException("Email " + email + " already exist.");
        }
    }

    private void validatePassword(String password) {
        if (!StringUtils.hasText(password)) {
            throw new ValidationException("Password is mandatory!");
        }
        if (password.length() < 8
                || !password.matches(".*\\d.*")
                || !password.matches(".*[a-zA-Z].*")
                || !password.matches(".*[A-Z].*")
                || !password.matches(".*[a-z].*")) {
            throw new ValidationException("Password must be at least 8 characters long." +
                    "\nPassword must contain at least one digit." +
                    "\nPassword must contain at least one letter." +
                    "\nPassword must contain at least one uppercase letter." +
                    "\nPassword must contain at least one lowercase letter.");
        }
    }

    private void validateLogin(String login) {
        if (!StringUtils.hasText(login)) {
            throw new ValidationException("Login is mandatory!");
        }
        if (userRepository.findByLogin(login).isPresent()) {
            throw new ValidationException("Login " + login + " already exist.");
        }
    }

    private void validatePhoneNumber(Long phoneNumber) {
        String phoneNumberString = String.valueOf(phoneNumber);
        if (!phoneNumberString.startsWith("375") || phoneNumberString.length() != 12) {
            throw new ValidationException("Invalid Belarusian mobile phone number. Should be 375(25/29/33/44/55/99)XXXXXXX");
        }

        String operatorCode = phoneNumberString.substring(3, 5);
        if (!operatorCode.equals("25") && !operatorCode.equals("29") && !operatorCode.equals("33") &&
                !operatorCode.equals("44") && !operatorCode.equals("55") && !operatorCode.equals("99")) {
            throw new ValidationException("Invalid Belarusian mobile phone number. Should be 375(25/29/33/44/55/99)XXXXXXX");
        }
        if (userRepository.findByMobileNumber(phoneNumber).isPresent()) {
            throw new ValidationException("Phone number " + phoneNumber + " already exist.");
        }
    }
}
