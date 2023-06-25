package com.example.edubin.controller;

import com.example.edubin.CommonIntegrationTest;
import com.example.edubin.WithAuthentication;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.web.servlet.MockMvc;
import org.testcontainers.lifecycle.Startables;

import java.io.IOException;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

class BlogsControllerTest extends CommonIntegrationTest {


    @BeforeAll
    static void beforeAll() {
        Startables.deepStart(postgres).join();
    }

    @AfterEach
    public void after() {
        blogRepository.deleteAll();
    }

    @Autowired
    private MockMvc mockMvc;

    @Test
    @DisplayName(value = "add blog successfully")
    @WithAuthentication(username = "me")
    public void shouldAddBlog() throws Exception {
        mockMvc.perform(testDataHelperCategory.addCategory());
        mockMvc.perform(testDataHelperBlog.addBlog()).andExpect(status().isOk());
    }

//    @Test
//    @DisplayName(value = "not add blog successfully")
//    @WithAuthentication(username = "me")
//    public void shouldNotAddBlog() throws Exception {
//        mockMvc.perform(testDataHelperBlog.addBlog()).andExpect(status().isNotFound());
//    }

    @Test
    @DisplayName(value = "add blog which has not full elements")
    @WithAuthentication(username = "me")
    public void shouldNotAddBlog2() throws Exception {
        mockMvc.perform(testDataHelperCategory.addCategory());
        mockMvc.perform(testDataHelperBlog.addBlogWhichNotHasFullElement()).andExpect(status().isUnsupportedMediaType());
    }

    @Test
    @DisplayName(value = "update blog successfully")
    @WithAuthentication(username = "me")
    public void shouldUpdateBlog() throws Exception {
        mockMvc.perform(testDataHelperCategory.addCategory());
        mockMvc.perform(testDataHelperBlog.addBlog()).andExpect(status().isOk());
        mockMvc.perform(testDataHelperBlog.updateBlog(1)).andExpect(status().isOk());
    }

    @Test
    @DisplayName(value = "not update not exist blog successfuly")
    @WithAuthentication(username = "me")
    public void shouldNotUpdateBlog() throws Exception {
        mockMvc.perform(testDataHelperBlog.updateBlog(0)).andExpect(status().isNotFound());
    }

    @Test
    @DisplayName(value = "get blogs with one category")
    @WithAuthentication(username = "me")
    public void shouldGetBlogsRelatedToOneCategory() throws Exception {
        mockMvc.perform(testDataHelperCategory.addCategory());
        mockMvc.perform(testDataHelperBlog.getBlogsRelatedToOneCategory(1)).andExpect(status().isOk());
    }

//    @Test
//    @DisplayName(value = "error happen when get Blogs with one category")
//    public void shouldNotGetBlogsInCategory() throws Exception {
//        mockMvc.perform(testDataHelperBlog.getBlogsRelatedToOneCategory(300)).andExpect(status().isNotFound());
//    }

//    @Test
//    @DisplayName(value = "delete one blog successfully")
//    @WithAuthentication(username = "me")
//    public void shouldDeleteBlog() throws Exception {
//        mockMvc.perform(testDataHelperCategory.addSameCategory());
//        mockMvc.perform(testDataHelperBlog.addNewBlog());
//        mockMvc.perform(testDataHelperBlog.deleteBlog(1)).andExpect(status().isOk());
//    }

    @Test
    @DisplayName(value = "error happen when one blog was deleted")
    @WithAuthentication(username = "me")
    public void shouldNotDeleteBlog() throws Exception {
        mockMvc.perform(testDataHelperBlog.deleteBlog(1)).andExpect(status().isNotFound());
    }
}