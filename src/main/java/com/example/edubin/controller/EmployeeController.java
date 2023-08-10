package com.example.edubin.controller;

import com.example.edubin.dto.request.AdminUpdateEmployee;
import com.example.edubin.dto.request.EmployeeUpdateHimself;
import com.example.edubin.dto.response.ApiResponse;
import com.example.edubin.enitity.UserEntity1;
import com.example.edubin.service.EmployeeService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/employee/")
public class EmployeeController {

    private final EmployeeService employeeService;


    @PostMapping("/add")
    @ResponseStatus(HttpStatus.OK)
    private ApiResponse<Void> addEmployee(@ModelAttribute AdminUpdateEmployee adminUpdateEmployee) {
        employeeService.addEmployee(adminUpdateEmployee);
        return new ApiResponse<>("employee was  added successfully in database");
    }

    @DeleteMapping("/delete/{id}")
    private ResponseEntity<?> deleteEmployee(@PathVariable("id") int id) {
        employeeService.delete(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @PutMapping("/update/{id}")
    @ResponseStatus(HttpStatus.OK)
    private ApiResponse<String> updateEmployee(@PathVariable("id") int id, @ModelAttribute AdminUpdateEmployee adminUpdateEmployee) {
        String token = employeeService.updateEmployee(id, adminUpdateEmployee);
        return new ApiResponse<>("updated successfully", token);
    }

    @PutMapping("/update/himself/{id}")
    @ResponseStatus(HttpStatus.OK)
    private ApiResponse<Void> updateEmployeeByHimself(@PathVariable("id") int id, @ModelAttribute EmployeeUpdateHimself updateHimself) {
        employeeService.updateEmployeeHimself(id, updateHimself);
        return new ApiResponse<>("employee was updated successfully");
    }

    @GetMapping("/list")
    @ResponseStatus(HttpStatus.OK)
    private ApiResponse<List<UserEntity1>> getAllEmployeeList() {
        List<UserEntity1> allEmployees = employeeService.getAllEmployees();
        return new ApiResponse<>("all Employees were token successfully ", allEmployees);
    }

    @GetMapping("/listAdmin")
    @ResponseStatus(HttpStatus.OK)
    private ApiResponse<List<UserEntity1>> getAllAdminList() {
        List<UserEntity1> allAdmins = employeeService.getAdminList();
        return new ApiResponse<>("all Admins were token successfully ", allAdmins);
    }

    @GetMapping("/listTeacher")
    @ResponseStatus(HttpStatus.OK)
    private ApiResponse<List<UserEntity1>> getAllTeacherList() {
        List<UserEntity1> allTeachers = employeeService.getTeacherList();
        return new ApiResponse<>("all Teachers were token successfully ", allTeachers);
    }

    @GetMapping("/listTeacher/page/{id}")
    @ResponseStatus(HttpStatus.OK)
    private ApiResponse<List<UserEntity1>> getTeachersPageableList(@PathVariable("id") int id) {
        List<UserEntity1> allTeachers = employeeService.getTeachersPageableList(id,8);
        return new ApiResponse<>("all Teachers were token successfully ", allTeachers);
    }

    @GetMapping("teacher/{id}")
    @ResponseStatus(HttpStatus.OK)
    private ApiResponse<UserEntity1> getTeacherById(@PathVariable("id") int id) {
        return new ApiResponse<>("teacher was token successfully", employeeService.findById(id));
    }
}
