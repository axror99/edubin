package com.example.edubin.service;

import com.example.edubin.config.JwtService;
import com.example.edubin.dto.request.AdminUpdateEmployee;
import com.example.edubin.dto.request.Employee;
import com.example.edubin.dto.request.EmployeeUpdateHimself;
import com.example.edubin.enitity.CourseEntity1;
import com.example.edubin.enitity.SocialMediaEntity1;
import com.example.edubin.enitity.UserEntity1;
import com.example.edubin.enitity.role.Role;
import com.example.edubin.exception.UserAlreadyExistException;
import com.example.edubin.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.text.MessageFormat;
import java.time.LocalDate;
import java.util.*;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class EmployeeService {
    private final PasswordEncoder passwordEncoder;
    private final UserRepository userRepository;
    private final MediaService mediaService;
    private final JwtService jwtService;
    private final CourseService courseService;

    public void hireEmployee(UserEntity1 user) {
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
    public void addEmployee(AdminUpdateEmployee adminUpdateEmployee){
        String imageRandomName = mediaService.saveMultiPartFile(adminUpdateEmployee.getPicture());
        SocialMediaEntity1 socialMedia = new SocialMediaEntity1();
        socialMedia.setFacebook("Facebook");
        socialMedia.setGoogle("Google");
        socialMedia.setInstagram("Instagram");
        socialMedia.setTwitter("Twitter");
        socialMedia.setLinkedIn("LinkedIn");
        socialMedia.setTelegram("Telegram");
        UserEntity1 user = UserEntity1.builder()
                .username(adminUpdateEmployee.getUsername())
                .password(adminUpdateEmployee.getPassword())
                .name(adminUpdateEmployee.getName())
                .email(adminUpdateEmployee.getEmail())
                .birthDay(adminUpdateEmployee.getBirthday())
                .roles(adminUpdateEmployee.getRoles())
                .permission(adminUpdateEmployee.getPermissionList())
                .picture(imageRandomName)
                .profession(adminUpdateEmployee.getProfession())
                .about(adminUpdateEmployee.getAbout())
                .registeredDate(LocalDate.now())
                .achievement(adminUpdateEmployee.getAchievement())
                .myObjective(adminUpdateEmployee.getMyObjective())
                .socialMedia(socialMedia)
                .build();
        hireEmployee(user);
    }

    public void delete(int id) {
        UserEntity1 user = userRepository.findById(id).orElseThrow(() -> new UsernameNotFoundException(
                MessageFormat.format("id={0} user was not found in database", id)
        ));
        List<CourseEntity1> courseEntity1List = courseService.getCourseByTeacherId(id);
        for (CourseEntity1 course : courseEntity1List){
            course.getTeacher().remove(user);
        }
        courseService.saveCourseWithoutTeacher(courseEntity1List);
        userRepository.delete(user);
    }

    public String updateEmployee(int id, AdminUpdateEmployee adminUpdateEmployee) {

        UserEntity1 user = userRepository.findById(id).orElseThrow(() -> new UsernameNotFoundException(
                MessageFormat.format("id={0} is not in database", id))
        );
        if (adminUpdateEmployee.getBirthday()!=null){
            user.setBirthDay(adminUpdateEmployee.getBirthday());
        }
        if (adminUpdateEmployee.getRoles().size()!=0){
            user.setRoles(adminUpdateEmployee.getRoles());
        }
        if(adminUpdateEmployee.getPermissionList().size()!=0){
            user.setPermission(adminUpdateEmployee.getPermissionList());
        }
        updateEmployeesPersonalInfo(user,adminUpdateEmployee);
//        UserEntity1 savedUser = userRepository.save(user);
        return jwtService.generateToken(user);
    }
    public void updateEmployeesPersonalInfo(UserEntity1 user, Employee employee){
        if (employee.getName()!=null && !employee.getName().equals("")){
            user.setName(employee.getName());
        }
        if (employee.getUsername()!=null && !employee.getUsername().equals("")){
            user.setUsername(employee.getUsername());
        }
        if (employee.getPassword()!=null && !employee.getPassword().equals("")){
            user.setPassword(passwordEncoder.encode(employee.getPassword()));
        }
        if (employee.getEmail()!=null && !employee.getEmail().equals("")){
            user.setEmail(employee.getEmail());
        }
        if (employee.getPicture() != null) {

            String imageName = employee.getPicture().getOriginalFilename();
//            mediaService.deleteExistImage(image);
//            String randomName = mediaService.saveMultiPartFile(employee.getPicture());
            String newName = mediaService.generateRandomName(imageName);
            mediaService.savePicture(employee.getPicture(),newName);
            user.setPicture(newName);
        }
        if (employee.getProfession()!=null && !employee.getProfession().equals("")){
            user.setProfession(employee.getProfession());
        }
        if (employee.getAbout()!=null && !employee.getAbout().equals("")){
            user.setAbout(employee.getAbout());
        }
        if (employee.getAchievement()!=null && !employee.getAchievement().equals("")){
            user.setAchievement(employee.getAchievement());
        }
        if (employee.getMyObjective()!=null && !employee.getMyObjective().equals("")){
            user.setMyObjective(employee.getMyObjective());
        }
        userRepository.save(user);
    }

    public List<UserEntity1> getAllEmployees() {
        List<UserEntity1> userRepositoryAll = userRepository.findAll();
        return userRepositoryAll.stream()
                .filter(user -> !user.getRoles().contains("USER")).toList();
    }
    public List<UserEntity1> getTeacherList(){
        return  userRepository.findByRolesContains(Role.TEACHER.name())
                .orElseThrow(()->new UsernameNotFoundException("There are not Teacher in database"));
    }
    public List<UserEntity1> getAdminList(){
        return  userRepository.findByRolesContains(Role.ADMIN.name())
                .orElseThrow(()->new UsernameNotFoundException("There are not Admin in database"));
    }

    public void updateEmployeeHimself(int id, EmployeeUpdateHimself updateHimself) {
        UserEntity1 user = userRepository.findById(id).orElseThrow(() -> new UsernameNotFoundException(
                MessageFormat.format("id={0} is not in database", id)));
            SocialMediaEntity1 socialMediaEntity1 = updateSocialMedia(user.getSocialMedia(), updateHimself);
            user.setSocialMedia(socialMediaEntity1);
        if (updateHimself.getBirthDay()!=null){
            user.setBirthDay(updateHimself.getBirthDay());
        }
        updateEmployeesPersonalInfo(user,updateHimself);
//        userRepository.save(user);
    }

    private SocialMediaEntity1 updateSocialMedia(SocialMediaEntity1 socialMediaEntity1, EmployeeUpdateHimself updateHimself){
        if (updateHimself.getGoogle()!=null && !updateHimself.getGoogle().equals("")) {
            socialMediaEntity1.setGoogle(updateHimself.getGoogle());
        }
        if (updateHimself.getFacebook()!=null && !updateHimself.getFacebook().equals("")){
            socialMediaEntity1.setFacebook(updateHimself.getFacebook());
        }
        if (updateHimself.getInstagram()!=null && !updateHimself.getInstagram().equals("")){
            socialMediaEntity1.setInstagram(updateHimself.getInstagram());
        }
        if (updateHimself.getLinkedIn()!=null && !updateHimself.getLinkedIn().equals("")){
            socialMediaEntity1.setLinkedIn(updateHimself.getLinkedIn());
        }
        if (updateHimself.getTwitter()!=null && !updateHimself.getTwitter().equals("")){
            socialMediaEntity1.setTwitter(updateHimself.getTwitter());
        }
        if (updateHimself.getTelegram()!=null && !updateHimself.getTelegram().equals("")){
            socialMediaEntity1.setTelegram(updateHimself.getTelegram());
        }
        return socialMediaEntity1;
    }

    public UserEntity1 findById(int id) {
       return userRepository.findById(id).orElseThrow(() -> new UsernameNotFoundException(
                MessageFormat.format("id={0} user is not in database", id)));
    }

    public List<UserEntity1> getTeachersPageableList(int id, int size) {
        int start = 5 * id - 4;
        return getTeacherList().stream()
                .skip(start - 1)
                .limit(size)
                .collect(Collectors.toList());
    }
}
