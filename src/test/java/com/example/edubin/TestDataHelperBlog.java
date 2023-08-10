package com.example.edubin;//package com.example.edubin;
//
//import com.example.edubin.dto.request.BlogRequest;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.http.MediaType;
//import org.springframework.mock.web.MockMultipartFile;
//import org.springframework.stereotype.Component;
//import org.springframework.test.web.servlet.MockMvc;
//import org.springframework.test.web.servlet.RequestBuilder;
//import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
//import org.springframework.web.multipart.MultipartFile;
//import org.testcontainers.shaded.com.fasterxml.jackson.core.JsonProcessingException;
//import org.testcontainers.shaded.com.fasterxml.jackson.databind.ObjectMapper;
//
//import java.io.IOException;
//import java.time.LocalDate;
//import java.util.HashMap;
//import java.util.Map;
//
//import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
//
//@Component
//public class TestDataHelperBlog {
//
//    private static final String ADD_BLOG_URL="/api/blog/add/{id}";
//    private static final String UPDATE_BLOG_URL="/api/blog/update/{id}";
//    private static final String DELETE_BLOG_URL="/api/blog/delete/{id}";
//    private static final String GET_BLOGS_WITH_ONE_CATEGORY_URL="/api/blog/list/{id}";
//    @Autowired
//    private MockMvc mockMvc;
//
//    public RequestBuilder addBlog() {
//        MockMultipartFile mockMultipartFile = new MockMultipartFile(
//                "picture", // name of the file parameter
//                "smf.png", // original file name
//                MediaType.IMAGE_PNG_VALUE, // content type
//                "picture data".getBytes() // content
//        );
//        return MockMvcRequestBuilders
//                .multipart(ADD_BLOG_URL, 1)
//                .file(mockMultipartFile)
//                .param("text", "asdfsdf")
//                .param("date", "2020-02-20")
//                .param("personName", "sdfs")
//                .param("headline", "sdsdvds")
//                .contentType(MediaType.MULTIPART_FORM_DATA_VALUE)
//                .accept(MediaType.APPLICATION_JSON_VALUE);
//    }
//
//    public RequestBuilder addNewBlog() {
//        MockMultipartFile mockMultipartFile = new MockMultipartFile(
//                "picture", // name of the file parameter
//                "smf.png", // original file name
//                MediaType.IMAGE_PNG_VALUE, // content type
//                "picture data".getBytes() // content
//        );
//        return MockMvcRequestBuilders
//                .multipart(ADD_BLOG_URL, 1)
//                .file(mockMultipartFile)
//                .param("text", "asdfserrerdf")
//                .param("date", "2020-04-20")
//                .param("personName", "sdfreres")
//                .param("headline", "sdsdverererds")
//                .contentType(MediaType.MULTIPART_FORM_DATA_VALUE)
//                .accept(MediaType.APPLICATION_JSON_VALUE);
//    }
//    public RequestBuilder addBlogWhichNotHasFullElement() {
//
//        return MockMvcRequestBuilders
//                .multipart(ADD_BLOG_URL, 1)
//                .param("text", "asdfsdf")
//                .param("date", "2020-02-20")
//                .param("personName", "sdfs")
//                .param("headline", "sdsdvds")
//                .contentType(MediaType.MULTIPART_FORM_DATA_VALUE)
//                .accept(MediaType.APPLICATION_JSON_VALUE);
//    }
//
//    public RequestBuilder updateBlog(int id) throws IOException {
//            MockMultipartFile mockMultipartFile = new MockMultipartFile(
//                    "picture", // name of the file parameter
//                    "aaaa.png", // original file name
//                    MediaType.IMAGE_PNG_VALUE, // content type
//                    "picture data".getBytes() // content
//            );
//        return MockMvcRequestBuilders
//                .put(UPDATE_BLOG_URL, id) // Use put() to send a PUT request
//                .content(mockMultipartFile.getBytes()) // Set the content of the request
//                .param("text", "bbbb")
//                .param("date", "2020-02-20")
//                .param("personName", "sdsdsd")
//                .param("headline", "yyyyyy")
//                .contentType(MediaType.MULTIPART_FORM_DATA_VALUE)
//                .accept(MediaType.APPLICATION_JSON_VALUE)
//                .characterEncoding("UTF-8");
//    }
//
//    public RequestBuilder getBlogsRelatedToOneCategory(int id) {
//        return MockMvcRequestBuilders
//                .get(GET_BLOGS_WITH_ONE_CATEGORY_URL, id)
//                .contentType(MediaType.MULTIPART_FORM_DATA_VALUE)
//                .accept(MediaType.APPLICATION_JSON_VALUE);
//    }
//
//    public RequestBuilder deleteBlog(int id) {
//        return MockMvcRequestBuilders
//                .delete(DELETE_BLOG_URL, id)
//                .contentType(MediaType.MULTIPART_FORM_DATA_VALUE)
//                .accept(MediaType.APPLICATION_JSON_VALUE);
//    }
//}
