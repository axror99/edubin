package com.example.edubin.controller;

import com.example.edubin.dto.request.CourseRequest;
import com.example.edubin.dto.response.ApiResponse;
import com.example.edubin.dto.response.TokenDTO;
import com.example.edubin.enitity.CourseEntity1;
import com.example.edubin.service.CourseService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
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
    private ApiResponse<TokenDTO> addCourse(@ModelAttribute CourseRequest course) {
        courseService.addCourse(course);
        return new ApiResponse<>("course was saved successfully in database");
    }

    @GetMapping("/get/{id}")
    @ResponseStatus(HttpStatus.OK)
    private ApiResponse<CourseEntity1> getCourse(@PathVariable("id") int id) {
        CourseEntity1 course = courseService.getCourseById(id);
        return new ApiResponse<>("course was taken successfully in database", course);
    }

    @GetMapping("/get/teacher/{id}")
    @ResponseStatus(HttpStatus.OK)
    private ApiResponse<List<CourseEntity1>> getCourseByTeacherId(@PathVariable("id") int id) {
        List<CourseEntity1> list = courseService.getCourseByTeacherId(id);
        return new ApiResponse<>("course list related to TeacherId was taken successfully in database", list);
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
    private ApiResponse<List<CourseEntity1>> getCourseList() {
        return new ApiResponse<>("courseList is here", courseService.getCourseList());
    }

//    @GetMapping("/recommend/list")
//    @ResponseStatus(HttpStatus.OK)
//    private ApiResponse<List<CourseEntity>> getRecommendCourseList() {
//        return new ApiResponse<>("courseList is here", courseService.getRecommendCourseList());
//    }

    @GetMapping("/list/page/{start}")
    @ResponseStatus(HttpStatus.OK)
    private ApiResponse<List<CourseEntity1>> getPageableCourseList(@PathVariable("start") int start) {
        List<CourseEntity1> pageableCourseList = courseService.getPageableCourseList(start-1,6);
        System.out.println(pageableCourseList.size());
        return new ApiResponse<>("pageable courseList is here", pageableCourseList);
    }
}
