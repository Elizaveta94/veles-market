package com.velesmarket.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class UserDto {
    private Long id;
    private String lastName;
    private String firstName;
    private String email;
    private String login;
    private String password;
    private Long mobileNumber;
}
