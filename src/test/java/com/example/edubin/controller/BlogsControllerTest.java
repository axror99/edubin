//package com.example.edubin.controller;
//
//import com.example.edubin.CommonIntegrationTest;
//import org.junit.jupiter.api.AfterEach;
//import org.junit.jupiter.api.BeforeAll;
//import org.junit.jupiter.api.Test;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.test.web.servlet.MockMvc;
//import org.testcontainers.lifecycle.Startables;
//import org.testcontainers.shaded.com.fasterxml.jackson.core.JsonProcessingException;
//
//import static org.junit.jupiter.api.Assertions.*;
//import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
//
//class BlogsControllerTest extends CommonIntegrationTest {
//
//    @BeforeAll
//    static void beforeAll() {
//        Startables.deepStart(postgres).join();
//    }
//
//    @AfterEach
//    public void after() {
//        userRepository.deleteAll();
//    }
//
//    @Autowired
//    private MockMvc mockMvc;
//
//    @Test
//    public void shouldAddBlog() throws Exception {
//        mockMvc.perform(testDataHelperBlog.addBlog()).andExpect(status().isOk());
//    }
//}