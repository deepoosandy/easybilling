package com.sanapp.sms.dto;

import lombok.Data;

@Data
public class UserDto {
    private String username;
    private String password;
    private String confirmPassword;
    private String role;
    private Boolean enabled;
    private String firstName;
    private String lastName;
}
