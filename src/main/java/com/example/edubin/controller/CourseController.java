package com.example.edubin.controller;

import com.example.edubin.dto.request.CourseRequest;
import com.example.edubin.dto.response.ApiResponse;
import com.example.edubin.dto.response.TokenDTO;
import com.example.edubin.enitity.CourseEntity;
import com.example.edubin.service.CourseService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/course")
@RequiredArgsConstructor
public final class CourseController {

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
    private ApiResponse<CourseEntity> getCourse(@PathVariable("id") int id){
        CourseEntity course = courseService.getCourseById(id);
        return new ApiResponse<>("course was taken successfully in database",course);
    }
    @PutMapping("/update/{id}")
    @ResponseStatus(HttpStatus.OK)
    private ApiResponse<Void> updateCourse(@PathVariable("id")int id, @ModelAttribute CourseRequest courseRequest){
        courseService.updateCourse(id,courseRequest);
        return new ApiResponse<>("course was updated successfully");
    }
    @DeleteMapping("/delete/{id}")
    @ResponseStatus(HttpStatus.OK)
    private ApiResponse<Void> deleteCourse(@PathVariable("id") int id){
        courseService.deleteCourse(id);
        return new ApiResponse<>("course was deleted successfully in database");
    }

}
