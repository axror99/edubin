package com.example.edubin.data;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.RequestBuilder;
import org.testcontainers.shaded.com.fasterxml.jackson.core.JsonProcessingException;
import org.testcontainers.shaded.com.fasterxml.jackson.databind.ObjectMapper;

import java.util.HashMap;
import java.util.Map;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;


@Component
public class TestDataHelperComment {

    private static final String ADD_COMMENT_TO_TEACHER_URL="/api/comment/teacher/{id}";
    private static final String ADD_COMMENT_TO_COURSE_URL="/api/comment/course/{id}";
    private static final String ADD_COMMENT_TO_BLOG_URL="/api/comment/blog/{id}";

    @Autowired
    MockMvc mockMvc;

    public RequestBuilder addCommentToTeacher() throws JsonProcessingException {
        Map<String,Object> map = new HashMap<>();
        map.put("firstName","Axror");
        map.put("lastName","Baxromaliyev");
        map.put("date","2020-05-19");
        map.put("text","aaaaaaaaaaaaaaaaaaaaaaaaaaaaa   aaaaaaa");
        return post(ADD_COMMENT_TO_TEACHER_URL,1)
                .contentType(MediaType.APPLICATION_JSON)
                .content(new ObjectMapper().writeValueAsString(map));
    }

    public RequestBuilder addCommentToCourse() throws JsonProcessingException {
        Map<String,Object> map = new HashMap<>();
        map.put("firstName","Jasur");
        map.put("lastName","Umarov");
        map.put("date","2005-09-22");
        map.put("text","bbbbbbbb  b b bbbb        bb");
        return post(ADD_COMMENT_TO_COURSE_URL,1)
                .contentType(MediaType.APPLICATION_JSON)
                .content(new ObjectMapper().writeValueAsString(map));
    }

    public RequestBuilder addCommentToBlog() throws JsonProcessingException {
        Map<String,Object> map = new HashMap<>();
        map.put("firstName","Maqsadbek");
        map.put("lastName","Nazarov");
        map.put("date","2010-12-30");
        map.put("text","cccccc  cc c c  c   c   c  cccc c c ");
        return post(ADD_COMMENT_TO_COURSE_URL,1)
                .contentType(MediaType.APPLICATION_JSON)
                .content(new ObjectMapper().writeValueAsString(map));
    }

}
