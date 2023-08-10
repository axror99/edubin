package com.example.edubin.service;

import com.example.edubin.dto.request.CommentRequest;
import com.example.edubin.enitity.*;
import com.example.edubin.repository.CommentRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CommentService {

    private final CommentRepository commentRepository;
    private final UserService userService;
    private final CourseService courseService;
    private final BlogService blogService;
    private final MerchandiseService merchandiseService;

    public void addCommentToTeacher(int id, CommentRequest commentRequest) {
        UserEntity1 teacher = userService.findUser(id);
        CommentEntity1 comment=new CommentEntity1();
        comment.setTeacher(teacher);
        saveAnyTypeComment(commentRequest,comment);
    }

    public void addCommentToCourse(int id, CommentRequest commentRequest) {
        CourseEntity1 course = courseService.findCourse(id);
        CommentEntity1 comment=new CommentEntity1();
        comment.setCourse(course);
        saveAnyTypeComment(commentRequest,comment);
    }

    public void addCommentToBlog(int id, CommentRequest commentRequest) {
        BlogEntity1 blog = blogService.findBlogById(id);
        CommentEntity1 comment=new CommentEntity1();
        comment.setBlog(blog);
        saveAnyTypeComment(commentRequest,comment);
    }
    private void saveAnyTypeComment(CommentRequest commentRequest, CommentEntity1 comment){
        comment.setDate(commentRequest.getDate());
        comment.setText(commentRequest.getText());
        comment.setPersonName(commentRequest.getFirstName()+" "+commentRequest.getLastName());
        commentRepository.save(comment);
    }

    public void addCommentToMerchandise(int id, CommentRequest commentRequest) {
        MerchandiseEntity1 product = merchandiseService.getOneProduct(id);
        CommentEntity1 comment = new CommentEntity1();
        comment.setMerchandise(product);
        saveAnyTypeComment(commentRequest, comment);
    }
}
