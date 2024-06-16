package com.loginManagement.lms.service.impl;

import com.loginManagement.lms.dto.LoginDto;
import com.loginManagement.lms.dto.RoleDto;
import com.loginManagement.lms.dto.UserDto;
import com.loginManagement.lms.entity.Role;
import com.loginManagement.lms.entity.User;
import com.loginManagement.lms.exception.DuplicateRecordException;
import com.loginManagement.lms.exception.RecordNotFoundException;
import com.loginManagement.lms.mapper.AuthMapper;
import com.loginManagement.lms.repository.RoleRepo;
import com.loginManagement.lms.repository.UserRepo;
import com.loginManagement.lms.service.AuthService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;
@Service
public class AuthServiceImpl implements AuthService {

    @Autowired
    private RoleRepo roleRepo;
    @Autowired
    private UserRepo userRepo;
    @Autowired
    private PasswordEncoder passwordEncoder;
    @Autowired(required = true)
    private AuthenticationManager authenticationManager;

    /**
     * @param userDto
     * @return
     */
    @Override
    public UserDto registerUser(UserDto userDto) {
        userRepo.findByUserNameOrEmail(userDto.getUserName(), userDto.getEmail()).ifPresent(
                ex->new DuplicateRecordException("User name Or Email is already exist"));

        userDto.setPassword(passwordEncoder.encode(userDto.getPassword()));
        Role role = userDto.getRoleName().stream().map(p -> roleRepo.findByName(p).orElseThrow(
                () -> new RecordNotFoundException("Role " + p + "Not found"))).findFirst().get();
        return  AuthMapper.convertToUserDto(userRepo.save(AuthMapper.convertToUserEntity(userDto,role)));
    }

    /**
     * @return
     */
    @Override
    public List<UserDto> getAllUser() {
        return userRepo.findAll().stream().map(p->AuthMapper.convertToUserDto(p)).collect(Collectors.toList());
    }

    /**
     * @param id
     * @return
     */
    @Override
    public UserDto getUserByUserId(Long id) {
        return AuthMapper.convertToUserDto(userRepo.findById(id).orElseThrow(
                ()-> new RecordNotFoundException("User Id "+ id +" Not Found")
                )

        );
    }

    /**
     * @param userName
     * @return
     */
    @Override
    public UserDto getUserByUserName(String userName) {
        return AuthMapper.convertToUserDto(userRepo.findByUserName(userName).orElseThrow(
                ()->new RecordNotFoundException("User name "+ userName +" not Found")
        ));
    }

    /**
     * @param email
     * @return
     */
    @Override
    public UserDto getUserByEmail(String email) {
        return AuthMapper.convertToUserDto(userRepo.findByEmail(email).orElseThrow(()->
                new RecordNotFoundException("User Email "+email +" Not Found")
                ));
    }

    /**
     * @param username
     * @param email
     * @return
     */
    @Override
    public UserDto getUserByUserNameOrEmail(String username, String email) {
        User user = userRepo.findByUserNameOrEmail(username,email).orElseThrow(
                ()-> new RecordNotFoundException("User Name "+ username +" Or Email "+ email +" Not present"));


        return AuthMapper.convertToUserDto(user);
    }

    /**
     * @param email
     * @return
     */
    @Override
    public Boolean isUserExist(String email) {
        return userRepo.existsByEmail(email);
    }

    /**
     * @param roleDto
     * @return
     */
    @Override
    public RoleDto createRole(RoleDto roleDto) {
        roleRepo.findByName(roleDto.getName()).ifPresent(ex->
                new DuplicateRecordException("Role Name already exist"));
        return AuthMapper.convertToRoleDto(roleRepo.save(AuthMapper.covertToRoleEntity(roleDto)));
    }

    /**
     * @return
     */
    @Override
    public List<RoleDto> getAllRole() {
        return roleRepo.findAll().stream().map(p->AuthMapper.convertToRoleDto(p)).collect(Collectors.toList());
    }

    /**
     * @param id
     * @return
     */
    @Override
    public RoleDto getRoleById(Long id) {
        return AuthMapper.convertToRoleDto(roleRepo.findById(id).
                orElseThrow(()->new RecordNotFoundException("Role are not present "+id)));
    }

    /**
     * @param roleName
     * @return
     */
    @Override
    public RoleDto getRoleByName(String roleName) {
        return AuthMapper.convertToRoleDto(roleRepo.findByName(roleName).orElseThrow(
                (()-> new RecordNotFoundException("Role "+roleName+" Not present"))
        ));
    }

    /**
     * @param roleId
     * @param roleDto
     * @return
     */
    @Override
    public RoleDto updateRole(Long roleId, RoleDto roleDto) {
        Role role = roleRepo.findById(roleId).orElseThrow(()->
                new RecordNotFoundException("Role "+roleId+ " Not Present"));
        roleRepo.findByName(roleDto.getName()).ifPresent(ex->
                new DuplicateRecordException("Role Name already exist"));
        role.setName(roleDto.getName());
        role.setDescription(roleDto.getDescription());
        return AuthMapper.convertToRoleDto(roleRepo.save(role));
    }

    /**
     * @param roleId
     */
    @Override
    public void deleteRole(Long roleId) {
        roleRepo.findById(roleId).orElseThrow(()->
                new RecordNotFoundException("Role "+roleId+ " Not Present"));
        roleRepo.deleteById(roleId);
    }

    /**
     * @param userId
     * @param userDto
     * @return
     */
    @Override
    public UserDto updateUser(Long userId, UserDto userDto) {
        userRepo.findByUserNameOrEmail(userDto.getUserName(), userDto.getEmail()).ifPresent(
                ex->new DuplicateRecordException("User name Or Email is already exist"));
        User user = userRepo.findById(userId).orElseThrow(() ->
                new RecordNotFoundException("User " + userId + " Not present"));
        Role role = userDto.getRoleName().stream().map(p -> roleRepo.findByName(p).orElseThrow(
                () -> new RecordNotFoundException("Role " + p + "Not found"))).findFirst().get();
        Set<Role> roles = new HashSet<>();
        roles.add(role);
        user.setUserName(userDto.getUserName());
        user.setName(userDto.getName());
        user.setEmail(userDto.getEmail());
        user.setPassword(passwordEncoder.encode(userDto.getPassword()));
        user.setRoles(roles);
        return AuthMapper.convertToUserDto(userRepo.save(user));
    }

    /**
     * @param userId
     */
    @Override
    public void deleteUser(Long userId) {
        userRepo.findById(userId).orElseThrow(() ->
                new RecordNotFoundException("User " + userId + " Not present"));
        userRepo.deleteById(userId);
    }

    /**
     * @return
     */
    @Override
    public String login(LoginDto loginDto) {
        Authentication authenticate = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(loginDto.getUserName(), loginDto.getPassword()));
        SecurityContextHolder.getContext().setAuthentication(authenticate);
        return "Login Successfully";
    }
}
