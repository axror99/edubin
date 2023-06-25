//package com.example.edubin.controller;
//
//import com.example.edubin.CommonIntegrationTest;
//import org.junit.jupiter.api.AfterEach;
//import org.junit.jupiter.api.BeforeAll;
//import org.junit.jupiter.api.DisplayName;
//import org.junit.jupiter.api.Test;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.test.web.servlet.MockMvc;
//import org.testcontainers.lifecycle.Startables;
//
//import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
//import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
//
//public class AuthenticationControllerTest extends CommonIntegrationTest {
//
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
//
//    @Test
//    @DisplayName(value = "successfully registered")
//    public void shouldPassRegister() throws Exception {
//        mockMvc.perform(testDataHelperUser.registerUser()).andExpect(status().isOk());
//    }
//
//    @Test
//    @DisplayName(value = "not pass  register When already registered successfully")
//    public void shouldNotPassRegisterWhenAlreadyBeRegistered() throws Exception {
//        mockMvc.perform(testDataHelperUser.registerUser());
//        mockMvc.perform(testDataHelperUser.registerUser()).andExpect(status().isNotAcceptable());
//    }
//
//    @Test
//    @DisplayName(value = "successfully logged")
//    public void shouldPassLogin() throws Exception {
//        mockMvc.perform(testDataHelperUser.registerUser());
//        mockMvc.perform(testDataHelperUser.login()).andExpect(status().isOk());
//    }
//
//    @Test
//    @DisplayName(value = "not passed login successfully")
//    public void shouldNotPassLogin() throws Exception {
//        mockMvc.perform(testDataHelperUser.login()).andExpect(status().isUnauthorized());
//    }
//}