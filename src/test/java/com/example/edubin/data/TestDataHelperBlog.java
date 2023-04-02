package com.example.edubin.data;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.stereotype.Component;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.multipart.MultipartFile;
import org.testcontainers.shaded.com.fasterxml.jackson.core.JsonProcessingException;
import org.testcontainers.shaded.com.fasterxml.jackson.databind.ObjectMapper;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.time.LocalDate;
import java.util.HashMap;
import java.util.Map;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;

@Component
public class TestDataHelperBlog {

    private static final String ADD_BLOG_URL="/api/blog/add/{id}";
    private static final String UPDATE_BLOG_URL="/api/blog/update/{id}";
    private static final String DELETE_BLOG_URL="/api/blog/delete/{id}";
    private static final String LIST_ONE_BLOG_URL="/api/blog/list/{id}";

    public RequestBuilder addBlog(int id) {
        MockMultipartFile mockMultipartFile = new MockMultipartFile(
                "picture", // name of the file parameter
                "smf.png", // original file name
                MediaType.IMAGE_PNG_VALUE, // content type
                "picture data".getBytes() // content
        );
        return MockMvcRequestBuilders
                .multipart(ADD_BLOG_URL, id)
                .file(mockMultipartFile)
                .param("text", "asdfsdf")
                .param("date", "2020-02-20")
                .param("personName", "sdfs")
                .param("headline", "sdsdvds")
                .contentType(MediaType.MULTIPART_FORM_DATA_VALUE)
                .accept(MediaType.APPLICATION_JSON_VALUE);
    }
    public RequestBuilder updateLog(int id) throws Exception {
        MockMultipartFile mockMultipartFile = new MockMultipartFile(
                "picture", // name of the file parameter
                "smf.png", // original file name
                MediaType.IMAGE_PNG_VALUE, // content type
                "picture data".getBytes() // content
        );
        return MockMvcRequestBuilders
                .multipart(UPDATE_BLOG_URL, id)
                .file(mockMultipartFile)
                .param("text", "asdfsdf")
                .param("date", "2020-02-20")
                .param("personName", "sdfs")
                .param("headline", "sdsdvds")
                .contentType(MediaType.MULTIPART_FORM_DATA_VALUE)
                .accept(MediaType.APPLICATION_JSON_VALUE);
    }

    public RequestBuilder deleteBlog(int id){
        return delete(DELETE_BLOG_URL,id);
    }

    public RequestBuilder listOfOneBlog(int id){
        return get(LIST_ONE_BLOG_URL,id);
    }
}
