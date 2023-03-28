package com.example.edubin.controller;

import com.example.edubin.data.CommonIntegrationTest;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;
import org.testcontainers.lifecycle.Startables;

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
    @WithMockUser(roles = "SUPER_ADMIN")
    public void shouldAddBlog() throws Exception {
        mockMvc.perform(testDataHelperBlog.addBlog(1)).andExpect(status().isOk());
    }
    @Test
    @WithMockUser(roles = "SUPER_ADMIN")
    public void shouldNotAddBlog() throws Exception {
        mockMvc.perform(testDataHelperBlog.addBlog(1)).andExpect(status().isNotFound());
    }

    @Test
    @WithMockUser(roles = "SUPER_ADMIN")
    public void shouldUpdateBlog() throws Exception {
        mockMvc.perform(testDataHelperBlog.addBlog(1));
        mockMvc.perform(testDataHelperBlog.updateLog(1)).andExpect(status().isOk());
    }
    @Test
    @WithMockUser(roles = "SUPER_ADMIN")
    public void shouldNotUpdateBlog() throws Exception {
        mockMvc.perform(testDataHelperBlog.addBlog(1));
        mockMvc.perform(testDataHelperBlog.updateLog(1)).andExpect(status().isNotFound());
    }
    @Test
    @WithMockUser(roles = "SUPER_ADMIN")
    public void shouldDeleteBlog() throws Exception {
        mockMvc.perform(testDataHelperBlog.deleteBlog(1)).andExpect(status().isOk());
    }
    @Test
    @WithMockUser(roles = "SUPER_ADMIN")
    public void shouldNotDeleteBlog() throws Exception {
        mockMvc.perform(testDataHelperBlog.deleteBlog(1)).andExpect(status().isNotFound());
    }
    @Test
    @WithMockUser(roles = "SUPER_ADMIN")
    public void shouldGetOneBlogList() throws Exception {
        mockMvc.perform(testDataHelperBlog.listOfOneBlog(1)).andExpect(status().isOk());
    }
    @Test
    @WithMockUser(roles = "SUPER_ADMIN")
    public void shouldNotGetOneBlogList() throws Exception {
        mockMvc.perform(testDataHelperBlog.listOfOneBlog(2)).andExpect(status().isNotFound());
    }
}