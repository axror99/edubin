package com.example.edubin.controller;

import com.example.edubin.data.CommonIntegrationTest;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;
import org.testcontainers.lifecycle.Startables;

import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

class ContentControllerTest extends CommonIntegrationTest {

    @BeforeAll
    static void beforeAll(){
        Startables.deepStart(postgres).join();
    }

    @AfterEach
    void afterEach(){
        contentRepository.deleteAll();
    }
    @Autowired
    MockMvc mockMvc;

    @Test
    @WithMockUser(roles = "SUPER_ADMIN")
    public void shouldAddContent() throws Exception {
        mockMvc.perform(testDataHelperContent.addContent()).andExpect(status().isOk());
    }
}