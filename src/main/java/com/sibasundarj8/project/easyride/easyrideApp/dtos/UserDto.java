package com.sibasundarj8.project.easyride.easyrideApp.dtos;

import com.sibasundarj8.project.easyride.easyrideApp.entities.enums.Role;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Set;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserDto {
    private String name;
    private String email;
    private Set<Role> roles;
}