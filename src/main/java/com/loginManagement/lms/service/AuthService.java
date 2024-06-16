package com.loginManagement.lms.service;

import com.loginManagement.lms.dto.LoginDto;
import com.loginManagement.lms.dto.RoleDto;
import com.loginManagement.lms.dto.UserDto;

import java.util.List;

public interface AuthService {

    UserDto registerUser(UserDto userDto);

    List<UserDto> getAllUser();
    UserDto getUserByUserId(Long id);

    UserDto getUserByUserName(String userName);

    UserDto getUserByEmail(String email);

    UserDto getUserByUserNameOrEmail(String username,String email);

    Boolean isUserExist(String email);

    RoleDto createRole(RoleDto roleDto);

    List<RoleDto> getAllRole();

    RoleDto getRoleById(Long id);

    RoleDto getRoleByName(String roleName);

    RoleDto updateRole(Long roleId, RoleDto roleDto);

    void deleteRole(Long roleId);

    UserDto updateUser(Long userId, UserDto userDto);

    void deleteUser(Long userId);

    String login(LoginDto loginDto);

}
