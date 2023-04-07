package com.example.edubin.controller;

import com.example.edubin.data.CommonIntegrationTest;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.testcontainers.lifecycle.Startables;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

//@WebMvcTest(EmployeeController.class)

public class EmployeeControllerTest extends CommonIntegrationTest {

    @BeforeAll
    static void beforeAll(){
        Startables.deepStart(postgres).join();
    }

    @AfterEach
    public void after(){
        userRepository.deleteAll();
    }
    @Autowired
    private MockMvc mockMvc;

    @Test
    @WithMockUser(roles = "SUPER_ADMIN")
    public void shouldAddEmployee() throws Exception {
        mockMvc.perform(testDataHelperEmployee.shouldAddEmployee()).andExpect(status().isOk());
    }
    @Test
    @WithMockUser(roles = "SUPER_ADMIN")
    public void shouldDeleteEmployee() throws Exception {
        mockMvc.perform(testDataHelperEmployee.shouldAddEmployee()).andExpect(status().isOk());
        mockMvc.perform(testDataHelperEmployee.shouldDeleteEmployee(1)).andExpect(status().isOk());
    }
    @Test
    @WithMockUser(roles = "SUPER_ADMIN")
    public void shouldNotDeleteEmployee() throws Exception {
        mockMvc.perform(testDataHelperEmployee.shouldAddEmployee()).andExpect(status().isOk());
        mockMvc.perform(testDataHelperEmployee.shouldDeleteEmployee(9)).andExpect(status().isNotFound());
    }

    @Test
    @WithMockUser(roles = "SUPER_ADMIN")
    public void shouldUpdateEmployee() throws Exception {
        mockMvc.perform(testDataHelperEmployee.shouldAddEmployee()).andExpect(status().isOk());
        mockMvc.perform(testDataHelperEmployee.shouldUpdateEmployee(1)).andExpect(status().isOk());
    }

}