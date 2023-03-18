package com.example.edubin.service;

import com.example.edubin.dto.request.Employee;
import com.example.edubin.dto.request.UserRegister;
import com.example.edubin.enitity.UserEntity;
import com.example.edubin.enitity.role.Role;
import com.example.edubin.exception.UserAlreadyExistException;
import com.example.edubin.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.text.MessageFormat;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Service
@RequiredArgsConstructor
public class EmployeeService {

    private final PasswordEncoder passwordEncoder;
    private final UserRepository userRepository;

    public void hireEmployee(UserEntity user) {
        boolean existedEmail = userRepository.existsByEmail(user.getEmail());
        if (existedEmail){
            throw new UserAlreadyExistException(
                    MessageFormat.format("email {0}  already exist in database ",user.getEmail())
            );
        }else {
            UsernamePasswordAuthenticationToken.authenticated(user, user.getPassword(), user.getAuthorities());
            user.setPassword(passwordEncoder.encode(user.getPassword()));
           userRepository.save(user);
        }
    }

    public void delete(int id) {
        System.out.println("as");
        UserEntity user = userRepository.findById(id).orElseThrow(() -> new UsernameNotFoundException(
                MessageFormat.format("id={0} user was not found in database", id)
        ));
        userRepository.delete(user);
    }

    public UserEntity updateEmployee(int id, Employee employee) {

        UserEntity user = userRepository.findById(id).orElseThrow(() -> new UsernameNotFoundException(
                MessageFormat.format("id={0} is not in database", id))
        );
        if (!employee.getName().equals("") && employee.getName()!=null){
            user.setName(employee.getName());
        }
        if (!employee.getUsername().equals("") && employee.getUsername()!=null){
            user.setUsername(employee.getUsername());
        }
        if (!employee.getPassword().equals("") && employee.getPassword()!=null){
            user.setPassword(passwordEncoder.encode(employee.getPassword()));
        }
        if (!employee.getEmail().equals("") && employee.getEmail()!=null){
            user.setEmail(employee.getEmail());
        }
        if (employee.getBirthday()!=null){
            user.setBirthDay(employee.getBirthday());
        }
        if (employee.getRoles()!=null){
            user.setRoles(employee.getRoles());
        }
        if(employee.getPermissionList()!=null){
            user.setPermission(employee.getPermissionList());
        }
         return userRepository.save(user);
    }

    public List<UserEntity> getAllEmployees() {
        return  Stream.of(getAdminList(),getTeacherList())
                .flatMap(Collection::stream).toList();
    }
    public List<UserEntity> getTeacherList(){
        return  userRepository.findByRolesContains(Role.TEACHER.name())
                .orElseThrow(()->new UsernameNotFoundException("There are not Teacher in database"));
    }
    public List<UserEntity> getAdminList(){
        return  userRepository.findByRolesContains(Role.ADMIN.name())
                .orElseThrow(()->new UsernameNotFoundException("There are not Admin in database"));
    }
}
