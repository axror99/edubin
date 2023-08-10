package com.example.edubin;//package com.example.edubin;
//
//import com.example.edubin.dto.request.UserLogin;
//import com.example.edubin.dto.request.UserRegister;
//import com.example.edubin.service.UserService;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.http.MediaType;
//import org.springframework.stereotype.Component;
//import org.springframework.test.web.servlet.MockMvc;
//import org.springframework.test.web.servlet.MvcResult;
//import org.springframework.test.web.servlet.RequestBuilder;
//import org.testcontainers.shaded.com.fasterxml.jackson.core.JsonProcessingException;
//import org.testcontainers.shaded.com.fasterxml.jackson.databind.ObjectMapper;
//
//import java.util.HashMap;
//import java.util.Map;
//
//import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
//import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
//
//@Component
//public class TestDataHelperUser {
//
//    private static final String REGISTER_URL="/api/auth/register";
//    private static final String LOGIN_URL="/api/auth/login";
//    private static final String TOKEN_URL="/api/auth/token";
//
//    @Autowired
//    private MockMvc mockMvc;
//
//    @Autowired
//    private UserService userService;
//
//    public String loginAndGetToken() throws Exception {
//        UserLogin userLogin = new UserLogin();
//        userLogin.setPassword("superAdmin");
//        userLogin.setUsername("superAdmin");
//        String token = userService.login(userLogin);
//        return token;
//    }
//    public RequestBuilder registerUser() throws JsonProcessingException {
//        Map<String,Object> map =new HashMap<>();
//        map.put("username","axror");
//        map.put("name","aa");
//        map.put("email","aa@gmail.com");
//        map.put("password","123");
//
//        return post(REGISTER_URL)
//                .contentType(MediaType.APPLICATION_JSON)
//                .content(new ObjectMapper().writeValueAsString(map));
//    }
//
//    public RequestBuilder login() throws JsonProcessingException {
//        Map<String,Object> map = new HashMap<>();
//        map.put("password","123");
//        map.put("username","axror");
//
//        return post(LOGIN_URL)
//                .contentType(MediaType.APPLICATION_JSON)
//                .content(new ObjectMapper().writeValueAsString(map));
//    }
//
////    public RequestBuilder token() throws JsonProcessingException {
////        Map<String,Object> map=new HashMap<>();
////        map.put("accessToken",);
////        map.put("refreshToken",);
////        return post(TOKEN_URL)
////                .contentType(MediaType.APPLICATION_JSON)
////                .content(new ObjectMapper().writeValueAsString(map));
////    }
//}
