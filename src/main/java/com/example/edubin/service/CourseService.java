package com.example.edubin.service;

import com.example.edubin.config.JwtService;
import com.example.edubin.dto.request.CourseRequest;
import com.example.edubin.dto.response.MyItems;
import com.example.edubin.enitity.CategoryEntity1;
import com.example.edubin.enitity.CourseEntity1;
import com.example.edubin.enitity.UserEntity1;
import com.example.edubin.exception.RecordNotFoundException;
import com.example.edubin.repository.CourseRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.text.MessageFormat;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class CourseService {
    private final CourseRepository courseRepository;
    private final CategoryService categoryService;
    private final UserService userService;
    private final MediaService mediaService;
    private final JwtService jwtService;

    public void addCourse(CourseRequest course) {
        CategoryEntity1 category = categoryService.findCategory(course.getCategory_di());
        UserEntity1 teacher = userService.findUser(course.getTeacher_id());
        String imageRandomName = mediaService.saveMultiPartFile(course.getImage());
        CourseEntity1 courseEntity1 = CourseEntity1.builder()
                .name(course.getName())
                .courseSummery(course.getCourseSummery())
                .requirements(course.getRequirements())
                .category(category)
                .teacher(List.of(teacher))
                .headline(course.getHeadline())
                .definition(course.getDefinition())
                .price(course.getPrice())
                .image(imageRandomName)
                .build();
        mediaService.savePicture(course.getImage(),imageRandomName);
        // mediaService.createFolder(category.getName()+"\\"+course.getName());
        courseRepository.save(courseEntity1);
    }

    public CourseEntity1 findCourse(int id) {
        return courseRepository.findById(id).orElseThrow(() -> new RecordNotFoundException(
                MessageFormat.format("id = {0} course is not in database", id)
        ));
    }

    public CourseEntity1 getCourseById(int id) {
        return courseRepository.findById(id).orElseThrow(() -> new RecordNotFoundException(
                MessageFormat.format("id={0} course  is not found in database", id)
        ));
    }

    public void updateCourse(int id, CourseRequest courseRequest) {
        CourseEntity1 course = courseRepository.findById(id).orElseThrow(() -> new RecordNotFoundException(
                MessageFormat.format("id = {0} course was not found in database", id)
        ));
        if (courseRequest.getImage() != null) {
            String image = course.getImage();
            mediaService.deleteExistImage(image);
            String randomName = mediaService.saveMultiPartFile(courseRequest.getImage());
            mediaService.savePicture(courseRequest.getImage(), randomName);
            course.setImage(randomName);
        }
        if (!courseRequest.getCourseSummery().equals("") && courseRequest.getCourseSummery() != null) {
            course.setCourseSummery(courseRequest.getCourseSummery());
        }
        if (!courseRequest.getRequirements().equals("") && courseRequest.getRequirements() != null) {
            course.setRequirements(courseRequest.getRequirements());
        }
        if (courseRequest.getCategory_di() != 0) {
            CategoryEntity1 category = categoryService.findCategory(courseRequest.getCategory_di());
            course.setCategory(category);
        }

        if (courseRequest.getTeacher_id() != 0) {
            UserEntity1 teacher = userService.findUser(courseRequest.getTeacher_id());
            List<UserEntity1> teacherList = course.getTeacher();
            Optional<UserEntity1> deletedTeacher = teacherList.stream()
                    .filter(user -> user.getRoles().contains("TEACHER"))
                    .findFirst();
            if (deletedTeacher.isPresent()){
                teacherList.remove(deletedTeacher.get());
            }
            teacherList.add(teacher);
            course.setTeacher(teacherList);
        }
        if (!courseRequest.getName().equals("") && courseRequest.getName() != null) {
            course.setName(courseRequest.getName());
        }
        if(courseRequest.getPrice()!=null){
            course.setPrice(courseRequest.getPrice());
        }
        if (!courseRequest.getDefinition().equals("") && courseRequest.getDefinition()!=null){
            course.setDefinition(courseRequest.getDefinition());
        }
        if (!courseRequest.getHeadline().equals("") && courseRequest.getHeadline()!=null){
            course.setHeadline(courseRequest.getHeadline());
        }
        courseRepository.save(course);
    }

    public void deleteCourse(int id) {
        CourseEntity1 courseEntity1 = courseRepository.findById(id).orElseThrow(() -> new RecordNotFoundException(
                MessageFormat.format("id = {0} course is not in database", id)
        ));
        courseRepository.delete(courseEntity1);
        mediaService.deleteExistImage(courseEntity1.getImage());
    }

    public List<CourseEntity1> getCourseList() {
        return courseRepository.findAll();
    }


    public MyItems getStudentCourses(String token) {
        MyItems myItems = new MyItems();
        String username = jwtService.extractUsername(token.substring(7));
        UserEntity1 user =userService.findUserByUsername(username);
        myItems.setCourses(courseRepository.findByTeacherIn(List.of(user)));
        myItems.setMerchandises(user.getMerchandiseList());
        myItems.setBirthday(user.getBirthDay());
        myItems.setPicture(user.getPicture());
        myItems.setName(user.getName());
        myItems.setEmail(user.getEmail());
        myItems.setUsername(username);
        myItems.setRoles(user.getRoles());
        return myItems;
    }

    public List<CourseEntity1> getPageableCourseList(int start, int size) {
        Pageable pageable = PageRequest.of(start,size, Sort.by("id"));
        return courseRepository.findAll(pageable).getContent();
    }

    public List<CourseEntity1> getCourseByTeacherId(int id) {
        UserEntity1 user = userService.findUser(id);
        return courseRepository.findByTeacherIn(List.of(user));
    }

    public void saveCourseWithoutTeacher(List<CourseEntity1> list){
        courseRepository.saveAll(list);
    }

//    public List<CourseEntity> getRecommendCourseList() {
//        return null;
//    }
}
