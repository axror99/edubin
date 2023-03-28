package com.example.edubin.controller;

import com.example.edubin.data.CommonIntegrationTest;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;
import org.testcontainers.lifecycle.Startables;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


class CategoriesControllerTest extends CommonIntegrationTest {

    @BeforeAll
    static void beforeAll(){
        Startables.deepStart(postgres).join();
    }

    @AfterEach
    public void afterEach(){
        categoryRepository.deleteAll();
    }

    @Autowired
    private MockMvc mockMvc;

    @Test
    @WithMockUser(roles = "SUPER_ADMIN")
    public void shouldAddCategory() throws Exception {
        mockMvc.perform(testDataHelperCategory.addCategory("JAVA")).andExpect(status().isCreated());
    }
}