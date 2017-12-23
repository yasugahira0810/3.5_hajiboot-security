package com.example.web;

import lombok.Data;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Data
public class UserForm {
    @NotNull
    @Size(min = 1, max = 127)
    private String username;
    @NotNull
    @Size(min = 1, max = 255)
    private String encodedPassword;
}