package com.example.edubin.controller;

import com.example.edubin.dto.request.CourseRequest;
import com.example.edubin.dto.request.PurchaseRequest;
import com.example.edubin.dto.response.ApiResponse;
import com.example.edubin.dto.response.TokenDTO;
import com.example.edubin.enitity.CourseEntity;
import com.example.edubin.service.CourseService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/course")
@RequiredArgsConstructor
@CrossOrigin
public class CourseController {

    private final CourseService courseService;

    @PostMapping("/add")
    @ResponseStatus(HttpStatus.OK)
//    @PreAuthorize("(hasRole('ADMIN') and hasAuthority('ADD')) or (hasRole('SUPER_ADMIN'))")
    private ApiResponse<TokenDTO> addCourse(@ModelAttribute CourseRequest course) {
        courseService.addCourse(course);
        return new ApiResponse<>("course was saved successfully in database");
    }

    @GetMapping("/get/{id}")
    @ResponseStatus(HttpStatus.OK)
    private ApiResponse<CourseEntity> getCourse(@PathVariable("id") int id) {
        CourseEntity course = courseService.getCourseById(id);
        return new ApiResponse<>("course was taken successfully in database", course);
    }

    @PutMapping("/update/{id}")
    @ResponseStatus(HttpStatus.OK)
    private ApiResponse<Void> updateCourse(@PathVariable("id") int id, @ModelAttribute CourseRequest courseRequest) {
        courseService.updateCourse(id, courseRequest);
        return new ApiResponse<>("course was updated successfully");
    }

    @DeleteMapping("/delete/{id}")
    @ResponseStatus(HttpStatus.OK)
    private ApiResponse<Void> deleteCourse(@PathVariable("id") int id) {
        courseService.deleteCourse(id);
        return new ApiResponse<>("course was deleted successfully in database");
    }

    @GetMapping("/list")
    @ResponseStatus(HttpStatus.OK)
    private ApiResponse<List<CourseEntity>> getCourseList() {
        return new ApiResponse<>("courseList is here", courseService.getCourseList());
    }

//    @GetMapping("/buy/{id}") // course id
//    @ResponseStatus(HttpStatus.OK)
//    private ResponseEntity<Boolean> getCourseList(@PathVariable int id, @RequestBody PurchaseRequest purchase) {
//        boolean buyCourse = courseService.buyCourse(id, purchase);
//        return new ResponseEntity<>(buyCourse, HttpStatus.OK);
//    }

    @GetMapping("/{id}")
    private ApiResponse<List<CourseEntity>> getStudentsCourse(@PathVariable int id) {

        return new ApiResponse<>("client's course",courseService.getStudentCourses(id));

    }
}
