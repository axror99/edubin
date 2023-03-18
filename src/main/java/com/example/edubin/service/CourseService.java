package com.example.edubin.service;

import com.example.edubin.dto.request.CourseRequest;
import com.example.edubin.enitity.CategoryEntity;
import com.example.edubin.enitity.CourseEntity;
import com.example.edubin.enitity.UserEntity;
import com.example.edubin.exception.RecordNotFoundException;
import com.example.edubin.repository.CourseRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.text.MessageFormat;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class CourseService {
    private final CourseRepository courseRepository;
    private final CategoryService categoryService;
    private final UserService userService;
    private final MediaService mediaService;
    public void addCourse(CourseRequest course) {
        CategoryEntity category = categoryService.findCategory(course.getCategory_di());
        UserEntity teacher = userService.findUser(course.getTeacher_id());
        String imageRandomName = mediaService.saveMultiPartFile(course.getImage());
        CourseEntity courseEntity=CourseEntity.builder()
                .name(course.getName())
                .courseSummery(course.getCourseSummery())
                .requirements(course.getRequirements())
                .category(category)
                .teacher(teacher)
//                .contents(course.getContentEntities())
                .image(imageRandomName)
                .build();

        courseRepository.save(courseEntity);
    }

    public CourseEntity findCourse(int id) {
        return courseRepository.findById(id).orElseThrow(() -> new RecordNotFoundException(
                MessageFormat.format("id = {0} course is not in database", id)
        ));
    }
}
