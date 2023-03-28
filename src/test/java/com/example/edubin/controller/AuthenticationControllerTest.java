package com.example.edubin.controller;

import com.example.edubin.data.CommonIntegrationTest;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.web.servlet.MockMvc;
import org.testcontainers.lifecycle.Startables;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

public class AuthenticationControllerTest extends CommonIntegrationTest {

    @BeforeAll
    static void beforeAll() {
        Startables.deepStart(postgres).join();
    }

    @AfterEach
    public void after() {
        userRepository.deleteAll();
    }

    @Autowired
    private MockMvc mockMvc;


    @Test
    public void shouldPassRegister() throws Exception {
        mockMvc.perform(testDataHelperUser.registerUser()).andExpect(status().isOk());
    }

    @Test
    public void shouldNotPassRegisterWhenAlreadyBeRegistered() throws Exception {
        mockMvc.perform(testDataHelperUser.registerUser());
        mockMvc.perform(testDataHelperUser.registerUser()).andExpect(status().isNotAcceptable());
    }

    @Test
    public void shouldPassLogin() throws Exception {
        mockMvc.perform(testDataHelperUser.registerUser());
        mockMvc.perform(testDataHelperUser.login()).andExpect(status().isOk());
    }

    @Test
    public void shouldNotPassLogin() throws Exception {
        mockMvc.perform(testDataHelperUser.login()).andExpect(status().isUnauthorized());
    }

//    @Test
//    public void shouldGetToken() throws Exception {
//        mockMvc.perform(testDataHelperUser.token()).andExpect(status().isOk());
//    }

}