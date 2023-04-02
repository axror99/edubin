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

class CommentControllerTest extends CommonIntegrationTest {

    @BeforeAll
    static void beforeAll(){
        Startables.deepStart(postgres).join();
    }

    @AfterEach
    void deleteAll(){
        commentRepository.deleteAll();
    }

    @Autowired
    MockMvc mockMvc;

    @Test
    @WithMockUser(roles = "SUPER_ADMIN")
    public void shouldAddCommentToTeacher() throws Exception {
        mockMvc.perform(testDataHelperComment.addCommentToTeacher()).andExpect(status().isOk());
    }
}