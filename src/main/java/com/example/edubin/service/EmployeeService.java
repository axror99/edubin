package com.example.edubin.service;

import com.example.edubin.config.JwtService;
import com.example.edubin.dto.request.AdminUpdateEmployee;
import com.example.edubin.dto.request.EmployeeUpdateHimself;
import com.example.edubin.enitity.SocialMediaEntity;
import com.example.edubin.enitity.UserEntity;
import com.example.edubin.enitity.role.Role;
import com.example.edubin.exception.UserAlreadyExistException;
import com.example.edubin.repository.SocialMediaRepository;
import com.example.edubin.repository.UserRepository;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.nio.file.Paths;
import java.text.MessageFormat;
import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Service
@RequiredArgsConstructor
public class EmployeeService {
    private final PasswordEncoder passwordEncoder;
    private final UserRepository userRepository;
    private final SocialMediaRepository socialMediaRepository;
    private final MediaService mediaService;
    private final JwtService jwtService;

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
    public void addEmployee(AdminUpdateEmployee adminUpdateEmployee){
        String imageRandomName = mediaService.saveMultiPartFile(adminUpdateEmployee.getPicture());
        SocialMediaEntity socialMedia = new SocialMediaEntity();
        socialMedia.setFacebook("Facebook");
        socialMedia.setGoogle("Google");
        socialMedia.setInstagram("Instagram");
        socialMedia.setTwitter("Twitter");
        socialMedia.setLinkedIn("LinkedIn");
        socialMedia.setTelegram("Telegram");
        UserEntity user = UserEntity.builder()
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
                .achievement(adminUpdateEmployee.getAchievement())
                .myObjective(adminUpdateEmployee.getMyObjective())
                .socialMedia(socialMedia)
                .build();
        hireEmployee(user);
    }

    public void delete(int id) {
        System.out.println("as");
        UserEntity user = userRepository.findById(id).orElseThrow(() -> new UsernameNotFoundException(
                MessageFormat.format("id={0} user was not found in database", id)
        ));
        userRepository.delete(user);
    }

    public String updateEmployee(int id, AdminUpdateEmployee adminUpdateEmployee) {

        UserEntity user = userRepository.findById(id).orElseThrow(() -> new UsernameNotFoundException(
                MessageFormat.format("id={0} is not in database", id))
        );
        if (adminUpdateEmployee.getName()!=null && !adminUpdateEmployee.getName().equals("")){
            user.setName(adminUpdateEmployee.getName());
        }
        if (adminUpdateEmployee.getUsername()!=null && !adminUpdateEmployee.getUsername().equals("")){
            user.setUsername(adminUpdateEmployee.getUsername());
        }
        if (adminUpdateEmployee.getPassword()!=null && !adminUpdateEmployee.getPassword().equals("")){
            user.setPassword(passwordEncoder.encode(adminUpdateEmployee.getPassword()));
        }
        if (adminUpdateEmployee.getEmail()!=null && !adminUpdateEmployee.getEmail().equals("")){
            user.setEmail(adminUpdateEmployee.getEmail());
        }
        if (adminUpdateEmployee.getBirthday()!=null){
            user.setBirthDay(adminUpdateEmployee.getBirthday());
        }
        if (adminUpdateEmployee.getRoles().size()!=0){
            user.setRoles(adminUpdateEmployee.getRoles());
        }
        if(adminUpdateEmployee.getPermissionList().size()!=0){
            user.setPermission(adminUpdateEmployee.getPermissionList());
        }
        if (adminUpdateEmployee.getPicture() != null) {
            String image = "images/" + adminUpdateEmployee.getPicture();
            mediaService.deleteExistFile(image);
            String randomName = mediaService.saveMultiPartFile(adminUpdateEmployee.getPicture());
            user.setPicture(randomName);
        }
        if (adminUpdateEmployee.getProfession()!=null && !adminUpdateEmployee.getProfession().equals("")){
            user.setProfession(adminUpdateEmployee.getProfession());
        }
        if (adminUpdateEmployee.getAbout()!=null && !adminUpdateEmployee.getAbout().equals("")){
            user.setAbout(adminUpdateEmployee.getAbout());
        }
        if (adminUpdateEmployee.getAchievement()!=null && !adminUpdateEmployee.getAchievement().equals("")){
            user.setAchievement(adminUpdateEmployee.getAchievement());
        }
        if (adminUpdateEmployee.getMyObjective()!=null && !adminUpdateEmployee.getMyObjective().equals("")){
            user.setMyObjective(adminUpdateEmployee.getMyObjective());
        }
        UserEntity savedUser = userRepository.save(user);
        return jwtService.generateToken(savedUser);
    }

    public List<UserEntity> getAllEmployees() {
        List<UserEntity> userRepositoryAll = userRepository.findAll();
        return userRepositoryAll.stream()
                .filter(user -> !user.getRoles().contains("USER")).toList();

//        return  Stream.of(getAdminList(),getTeacherList())
//                .flatMap(Collection::stream).toList();
    }
    public List<UserEntity> getTeacherList(){
        return  userRepository.findByRolesContains(Role.TEACHER.name())
                .orElseThrow(()->new UsernameNotFoundException("There are not Teacher in database"));
    }
    public List<UserEntity> getAdminList(){
        return  userRepository.findByRolesContains(Role.ADMIN.name())
                .orElseThrow(()->new UsernameNotFoundException("There are not Admin in database"));
    }

    public void updateEmployeeHimself(int id, EmployeeUpdateHimself updateHimself) {
        UserEntity user = userRepository.findById(id).orElseThrow(() -> new UsernameNotFoundException(
                MessageFormat.format("id={0} is not in database", id)));
        Optional<SocialMediaEntity> socialMedia = socialMediaRepository.findByUserId(id);
        if (socialMedia.isPresent()){
            SocialMediaEntity socialMediaEntity = updateSocialMedia(socialMedia.get(), updateHimself);
            socialMediaRepository.save(socialMediaEntity);
        }else {
            SocialMediaEntity newSocialMedia = updateSocialMedia(new SocialMediaEntity(), updateHimself);
            newSocialMedia.setUser(user);
            socialMediaRepository.save(newSocialMedia);
        }
        if (updateHimself.getPicture()!=null){
            if (user.getPicture()!=null && !user.getPicture().equals("")){
                mediaService.deleteExistImage(user.getPicture());
            }
            String newPictureName= mediaService.generateRandomName(Objects.requireNonNull(updateHimself.getPicture().getOriginalFilename()));
            mediaService.internalWrite(updateHimself.getPicture(), Paths.get("D:\\EduBin\\edubin\\src\\main\\resources\\static\\images\\"+newPictureName));
            user.setPicture(newPictureName);
        }
        if (updateHimself.getAbout()!=null && !updateHimself.getAbout().equals("") ){
            user.setAbout(updateHimself.getAbout());
        }
        if (updateHimself.getAchievement()!=null && !updateHimself.getAchievement().equals("") ){
            user.setAchievement(updateHimself.getAchievement());
        }
        if (updateHimself.getMyObjective()!=null && !updateHimself.getMyObjective().equals("")){
            user.setMyObjective(updateHimself.getMyObjective());
        }
        if (updateHimself.getEmail()!=null && !updateHimself.getEmail().equals("")){
            user.setEmail(updateHimself.getEmail());
        }
        if (updateHimself.getName()!=null && !updateHimself.getName().equals("")){
            user.setName(updateHimself.getName());
        }
        if (updateHimself.getProfession()!=null && !updateHimself.getProfession().equals("")){
            user.setProfession(updateHimself.getProfession());
        }
        if (updateHimself.getBirthDay()!=null){
            user.setBirthDay(updateHimself.getBirthDay());
        }
        if (updateHimself.getPassword()!=null && !updateHimself.getPassword().equals("")){
            user.setPassword(passwordEncoder.encode(updateHimself.getPassword()));
        }
        if (updateHimself.getUsername()!=null && !updateHimself.getUsername().equals("")){
            user.setUsername(updateHimself.getUsername());
        }
        userRepository.save(user);
    }

    private SocialMediaEntity updateSocialMedia(SocialMediaEntity socialMediaEntity,EmployeeUpdateHimself updateHimself){
        if (updateHimself.getGoogle()!=null && !updateHimself.getGoogle().equals("")) {
            socialMediaEntity.setGoogle(updateHimself.getGoogle());
        }
        if (updateHimself.getFacebook()!=null && !updateHimself.getFacebook().equals("")){
            socialMediaEntity.setFacebook(updateHimself.getFacebook());
        }
        if (updateHimself.getInstagram()!=null && !updateHimself.getInstagram().equals("")){
            socialMediaEntity.setInstagram(updateHimself.getInstagram());
        }
        if (updateHimself.getLinkedIn()!=null && !updateHimself.getLinkedIn().equals("")){
            socialMediaEntity.setLinkedIn(updateHimself.getLinkedIn());
        }
        if (updateHimself.getTwitter()!=null && !updateHimself.getTwitter().equals("")){
            socialMediaEntity.setTwitter(updateHimself.getTwitter());
        }
        if (updateHimself.getTelegram()!=null && !updateHimself.getTelegram().equals("")){
            socialMediaEntity.setTelegram(updateHimself.getTelegram());
        }
        return socialMediaEntity;
    }

    public UserEntity findById(int id) {
       return userRepository.findById(id).orElseThrow(() -> new UsernameNotFoundException(
                MessageFormat.format("id={0} user is not in database", id)));
    }

    public List<UserEntity> getTeachersPageableList(int id,int size) {
        int start = 5 * id - 4;
        return getTeacherList().stream()
                .skip(start - 1)
                .limit(size)
                .collect(Collectors.toList());
    }
}
