package com.example.edubin.data;

import org.springframework.mock.web.MockMultipartFile;
import org.springframework.stereotype.Component;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.testcontainers.shaded.com.google.common.net.MediaType;

import java.util.HashMap;
import java.util.Map;

@Component
public class TestDataHelperContent {

    private static final String ADD_CONTENT_URL = "/api/content/add/{id}";
    private static final String UPDATE_CONTENT_URL = "/api/content/update/{id}";
    private static final String DELETE_CONTENT_URL = "/api/content/delete/{id}";
    private static final String GET_CONTENT_LIST_URL = "/api/content/list";

    public RequestBuilder addContent(){
        MockMultipartFile mockMultipartFileVideo = new MockMultipartFile(
                "video",
                "aa.mp4",
                MediaType.MP4_VIDEO.type(),
                "video data".getBytes()
        );
        MockMultipartFile mockMultipartFileTask = new MockMultipartFile(
                "task",
                "bb.pdf",
                MediaType.PDF.type(),
                "content data".getBytes()
        );
        return MockMvcRequestBuilders
                .multipart(ADD_CONTENT_URL,1)
                .file(mockMultipartFileVideo)
                .file(mockMultipartFileTask)
                .param("title","what is Java ?")
                .param("definition","Java is Object Oriented Programming Language")
                .contentType(org.springframework.http.MediaType.MULTIPART_FORM_DATA_VALUE)
                .accept(org.springframework.http.MediaType.APPLICATION_JSON);
    }

}
