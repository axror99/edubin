package com.example.edubin.service;

import com.example.edubin.dto.request.AA;
import com.example.edubin.dto.request.StudentRequest;
import com.example.edubin.enitity.CourseEntity;
import com.example.edubin.enitity.StudentEntity;
import com.example.edubin.enitity.UserEntity;
import com.example.edubin.exception.RecordNotFoundException;
import com.example.edubin.repository.CourseRepository;
import com.example.edubin.repository.StudentRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;

@Service
@RequiredArgsConstructor
public class StudentService {

    private final StudentRepository studentRepository;
    private final CourseRepository courseRepository;

    public StudentEntity getStudent(int id) {
       return studentRepository.findById(id).orElseThrow(()->new UsernameNotFoundException("user yoq"));
    }

    public void add(StudentRequest request) {
        StudentEntity student = studentRepository.findById(request.getId()).orElseThrow(() -> new UsernameNotFoundException("scsdc"));
        CourseEntity course = courseRepository.findById(request.getCourseId()).orElseThrow(() -> new RecordNotFoundException("sdcsd"));

        List<CourseEntity> courseList = student.getCourseList();
        courseList.add(course);
        student.setCourseList(courseList);
//        List<StudentEntity> student1 = course.getStudent();
//        student1.add(student);
//        course.setStudent(student1);
//        studentRepository.save(student);
        courseRepository.save(course);
    }

    public List<CourseEntity> getCourses(AA aa) {

        List<StudentEntity> studentEntityList = studentRepository.findAllById(aa.getCounts());
        System.out.println(studentEntityList);
      return   courseRepository.findByStudentIn(studentEntityList);
    }
}
