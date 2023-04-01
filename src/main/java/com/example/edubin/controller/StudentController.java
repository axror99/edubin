package com.example.edubin.controller;

import com.example.edubin.dto.request.AA;
import com.example.edubin.dto.request.StudentRequest;
import com.example.edubin.enitity.CourseEntity;
import com.example.edubin.enitity.StudentEntity;
import com.example.edubin.service.StudentService;
import jakarta.websocket.server.PathParam;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/student")
@RequiredArgsConstructor
public class StudentController {

    private final StudentService studentService;

    @GetMapping("/{id}")
    private StudentEntity getStudent(@PathVariable int id){
       return studentService.getStudent(id);
    }
    @PostMapping("/add")
    private void getStudent(@RequestBody StudentRequest request){
        studentService.add(request);
    }

    @PostMapping("/")
    private List<CourseEntity> getStudentsFromCourse(@RequestBody AA aa){

        return studentService.getCourses(aa);
    }
}
