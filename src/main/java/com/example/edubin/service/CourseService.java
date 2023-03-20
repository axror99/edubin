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
import java.util.List;

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
        String imageRandomName = mediaService.saveMultiPartFile(course.getImage(),course.getName(),category.getName());
        CourseEntity courseEntity=CourseEntity.builder()
                .name(course.getName())
                .courseSummery(course.getCourseSummery())
                .requirements(course.getRequirements())
                .category(category)
                .teacher(teacher)
//                .contents(course.getContentEntities())
                .image(imageRandomName)
                .build();
       // mediaService.createFolder(category.getName()+"\\"+course.getName());
        courseRepository.save(courseEntity);
    }

    public CourseEntity findCourse(int id) {
        return courseRepository.findById(id).orElseThrow(() -> new RecordNotFoundException(
                MessageFormat.format("id = {0} course is not in database", id)
        ));
    }

    public CourseEntity getCourseById(int id) {
        return courseRepository.findById(id).orElseThrow(()->new RecordNotFoundException(
                MessageFormat.format("id={0} course  is not found in database",id)
        ));
    }

    public void updateCourse(int id, CourseRequest courseRequest) {
        CourseEntity course = courseRepository.findById(id).orElseThrow(() -> new RecordNotFoundException(
                MessageFormat.format("id = {0} course was not found in database", id)
        ));
        if (courseRequest.getImage()!=null){
            String image = course.getCategory().getName() + "\\" + course.getName() + "\\image\\" + course.getImage();
            mediaService.deleteExistFile(image);
            String randomName = mediaService.saveMultiPartFile(courseRequest.getImage(),course.getName(),course.getCategory().getName());
            course.setImage(randomName);
        }
        if (!courseRequest.getCourseSummery().equals("") && courseRequest.getCourseSummery()!=null){
            course.setCourseSummery(courseRequest.getCourseSummery());
        }
        if (!courseRequest.getRequirements().equals("") && courseRequest.getRequirements()!=null){
            course.setRequirements(courseRequest.getRequirements());
        }
        if (courseRequest.getCategory_di()!=0){
            CategoryEntity category = categoryService.findCategory(courseRequest.getCategory_di());
            course.setCategory(category);
        }
        if (courseRequest.getTeacher_id()!=0){
            UserEntity teacher = userService.findUser(courseRequest.getTeacher_id());
            course.setTeacher(teacher);
        }
        if (!courseRequest.getName().equals("") && courseRequest.getName()!=null){
            course.setName(courseRequest.getName());
        }
        courseRepository.save(course);
    }

    public void deleteCourse(int id) {
        CourseEntity courseEntity = courseRepository.findById(id).orElseThrow(() -> new RecordNotFoundException(
                MessageFormat.format("id = {0} course is not in database", id)
        ));
        courseRepository.delete(courseEntity);
        mediaService.deleteFolder(courseEntity.getCategory().getName()+"\\"+courseEntity.getName());
    }
    public List<CourseEntity> getCourseList() {
        return courseRepository.findAll();
    }
}
