package com.example.edubin.controller;

import com.example.edubin.dto.request.CommentRequest;
import com.example.edubin.dto.response.ApiResponse;
import com.example.edubin.service.CommentService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.converter.json.GsonBuilderUtils;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/comment/")
@RequiredArgsConstructor
@CrossOrigin
public class CommentController {

    private final CommentService commentService;

    @PostMapping("/teacher/{id}")// teacher id
    @ResponseStatus(HttpStatus.OK)
    private ApiResponse<Void> addCommentToTeacher(@PathVariable("id") int id, @RequestBody CommentRequest commentRequest){
        commentService.addCommentToTeacher(id,commentRequest);
        return new ApiResponse<>("teacher Comment was saved successfully in database");
    }

    @PostMapping("/course/{id}")// course id
    @ResponseStatus(HttpStatus.OK)
    private ApiResponse<Void> addCommentToCourse(@PathVariable("id") int id, @RequestBody CommentRequest commentRequest){
        commentService.addCommentToCourse(id,commentRequest);
        return new ApiResponse<>("Course Comment was saved successfully in database");
    }

    @PostMapping("/blog/{id}")// blog id
    @ResponseStatus(HttpStatus.OK)
    private ApiResponse<Void> addCommentToBlog(@PathVariable("id") int id, @RequestBody CommentRequest commentRequest){
        commentService.addCommentToBlog(id,commentRequest);
        return new ApiResponse<>("blog Comment was saved successfully in database");
    }

    @PostMapping("/merchandise/{id}")// merchandise id
    @ResponseStatus(HttpStatus.OK)
    private ApiResponse<Void> addCommentToMerchandise(@PathVariable("id") int id, @RequestBody CommentRequest commentRequest){
        commentService.addCommentToMerchandise(id,commentRequest);
        return new ApiResponse<>("merchandise Comment was saved successfully in database");
    }
}