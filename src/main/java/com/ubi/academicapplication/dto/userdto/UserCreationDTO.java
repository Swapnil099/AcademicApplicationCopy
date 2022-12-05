package com.ubi.academicapplication.dto.userdto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.RequiredArgsConstructor;

import java.util.Set;

@Data @RequiredArgsConstructor
@AllArgsConstructor
public class UserCreationDTO {
    private String firstName;
    private String lastName;
    private String username;
    private String password;
    private Set<String> roles;
}
