package com.example.edubin;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.stereotype.Component;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.web.multipart.MultipartFile;
import org.testcontainers.shaded.com.fasterxml.jackson.core.JsonProcessingException;
import org.testcontainers.shaded.com.fasterxml.jackson.databind.ObjectMapper;

import java.time.LocalDate;
import java.util.HashMap;
import java.util.Map;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;

@Component
public class TestDataHelperBlog {

    private static final String ADD_BLOG_URL="/api/blog/add/{id}";
    @Autowired
    private MockMvc mockMvc;

    public RequestBuilder addBlog() {
        MockMultipartFile mockMultipartFile = new MockMultipartFile(
                "picture", // name of the file parameter
                "smf.png", // original file name
                MediaType.IMAGE_PNG_VALUE, // content type
                "picture data".getBytes() // content
        );
        return MockMvcRequestBuilders
                .multipart(ADD_BLOG_URL, 1)
                .file(mockMultipartFile)
                .param("text", "asdfsdf")
                .param("date", "2020-02-20")
                .param("personName", "sdfs")
                .param("headline", "sdsdvds")
                .contentType(MediaType.MULTIPART_FORM_DATA_VALUE)
                .accept(MediaType.APPLICATION_JSON_VALUE);
    }
}
