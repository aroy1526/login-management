package com.loginManagement.lms.mapper;

import com.loginManagement.lms.dto.RoleDto;
import com.loginManagement.lms.dto.UserDto;
import com.loginManagement.lms.entity.Role;
import com.loginManagement.lms.entity.User;

import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;

public class AuthMapper {

    public static Role covertToRoleEntity(RoleDto roleDto){
        return new Role(roleDto.getId(),roleDto.getName(), roleDto.getDescription());
    }

    public static RoleDto convertToRoleDto(Role role){
        return new RoleDto(role.getId(), role.getName(),role.getDescription());
    }

    public static UserDto convertToUserDto(User user){
        return new UserDto(user.getId(), user.getUserName(), user.getName(), user.getEmail(), user.getPassword(), user.getRoles().stream().map(p->p.getName())
                .collect(Collectors.toSet()));
    }

    public static User convertToUserEntity(UserDto userDto,Role role){
        Set<Role> roles = new HashSet<>();
        roles.add(role);
        return new User(userDto.getId(), userDto.getUserName(), userDto.getName(), userDto.getEmail(), userDto.getPassword(), roles);
    }

}
