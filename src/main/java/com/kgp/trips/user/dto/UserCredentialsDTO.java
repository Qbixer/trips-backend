package com.kgp.trips.user.dto;

import lombok.Data;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;

@Data
public class UserCredentialsDTO {

    @NotEmpty(message = "Email can not be empty")
    @Email(message = "Please provide a valid email id")
    String email;
    @NotEmpty(message = "Password can not be empty")
    String password;
}
