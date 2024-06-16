package com.loginManagement.lms.controller;

import com.loginManagement.lms.dto.LoginDto;
import com.loginManagement.lms.dto.RoleDto;
import com.loginManagement.lms.dto.UserDto;
import com.loginManagement.lms.service.AuthService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
@CrossOrigin("*")
@RestController
@RequestMapping("/api/auth")
public class AuthController {
    @Autowired
    private AuthService authService;
    @PostMapping("/user")
    public ResponseEntity<UserDto> registerUser(@RequestBody UserDto userDto){
        return new ResponseEntity<>(authService.registerUser(userDto), HttpStatus.CREATED);
    }
    @GetMapping("/user")
    public ResponseEntity<List<UserDto>> getUserList(){
        return ResponseEntity.ok(authService.getAllUser());
    }
    @GetMapping("/user/{userName}")
    public ResponseEntity<UserDto> getUserById(@PathVariable("userName") String userName){
        return ResponseEntity.ok(authService.getUserByUserName(userName));
    }

    @DeleteMapping("/user/{id}")
    public ResponseEntity<String> deleteUser(@PathVariable("id") Long userId){
        authService.deleteUser(userId);
        return ResponseEntity.ok("Delete Successfully");
    }

    @PutMapping("/user{id}")
    public ResponseEntity<UserDto> updateUser(@PathVariable("id") Long userId, @RequestBody UserDto userDto){
        return ResponseEntity.ok(authService.updateUser(userId,userDto));
    }

    @PostMapping("/role")
    public ResponseEntity<RoleDto> createRole(@RequestBody RoleDto roleDto){
        return new ResponseEntity<>(authService.createRole(roleDto),HttpStatus.CREATED);
    }

    @PutMapping("/role/{roleId}")
    public ResponseEntity<RoleDto> updateRole(@PathVariable("id") Long roleId, @RequestBody RoleDto roleDto){
        return ResponseEntity.ok(authService.updateRole(roleId,roleDto));
    }
    @DeleteMapping("/role/{roleId}")
    public ResponseEntity<String> deleteRole(@PathVariable Long id){
        authService.deleteRole(id);
        return ResponseEntity.ok("Role Deleted Successfully "+id);
    }
    @GetMapping("/role")
    public ResponseEntity<List<RoleDto>> getAllRole(){
        return ResponseEntity.ok(authService.getAllRole());
    }
    @GetMapping("/role/{name}")
    public ResponseEntity<RoleDto> getRoleByName(String name){
        return ResponseEntity.ok(authService.getRoleByName(name));
    }

    @PostMapping("/login")
    public ResponseEntity<String> login(@RequestBody LoginDto loginDto){
        return ResponseEntity.ok(authService.login(loginDto));
    }

}
