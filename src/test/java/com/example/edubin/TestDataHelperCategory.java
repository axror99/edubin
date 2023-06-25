//package com.example.edubin;
//
//
//import org.springframework.http.HttpHeaders;
//import org.springframework.http.MediaType;
//import org.springframework.stereotype.Component;
//import org.springframework.test.web.servlet.RequestBuilder;
//import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
//import org.testcontainers.shaded.com.fasterxml.jackson.databind.ObjectMapper;
//
//import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
//import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
//
//@Component
//public class TestDataHelperCategory {
//
//    private final static String  ADD_CATEGORY_URL="/api/category/add";
//    private final static String  DELETE_CATEGORY_URL="/api/category/delete/{name}";
//    private final static String  UPDATE_CATEGORY_URL="/api/category/update/{name}";
//    private final static String  GET_ONE_CATEGORY_URL="/api/category/get/{id}";
//    private final static String  GET_ALL_CATEGORY_URL="/api/category/list";
//
//    public RequestBuilder addCategory(){
//        String jsonPayload = "{\"name1\":\"backend\"}";
//        return MockMvcRequestBuilders
//                .multipart(ADD_CATEGORY_URL)
//                .contentType(MediaType.APPLICATION_JSON_VALUE)
//                .accept(MediaType.APPLICATION_JSON_VALUE)
//                .content(jsonPayload);
//    }
//    public RequestBuilder addSameCategory(){
//        String jsonPayload = "{\"name1\":\"backend same\"}";
//        return MockMvcRequestBuilders
//                .multipart(ADD_CATEGORY_URL)
//                .contentType(MediaType.APPLICATION_JSON_VALUE)
//                .accept(MediaType.APPLICATION_JSON_VALUE)
//                .content(jsonPayload);
//    }
//    public RequestBuilder addNullCategory(){
//        String jsonPayload = "{\"name1\":null}";
//        return MockMvcRequestBuilders
//                .multipart(ADD_CATEGORY_URL)
//                .contentType(MediaType.APPLICATION_JSON_VALUE)
//                .accept(MediaType.APPLICATION_JSON_VALUE)
//                .content(jsonPayload);
//    }
//
//    public RequestBuilder addCategoryWithoutToken(){
//        String jsonPayload = "{\"name1\":\"backend\"}";
//        return MockMvcRequestBuilders
//                .multipart(ADD_CATEGORY_URL)
//                .contentType(MediaType.APPLICATION_JSON_VALUE)
//                .accept(MediaType.APPLICATION_JSON_VALUE)
//                .content(jsonPayload);
//    }
//
//    public RequestBuilder deleteExistCategory() {
//        return delete(DELETE_CATEGORY_URL,"backend")
//                .contentType(MediaType.APPLICATION_JSON);
//    }
//
//    public RequestBuilder deleteNotExistCategory() {
//        return delete(DELETE_CATEGORY_URL,"backend1")
//                .contentType(MediaType.APPLICATION_JSON);
//    }
//
//    public RequestBuilder updateCategory() {
//        String jsonPayload = "{\"name1\":\"frontend\"}";
//        return MockMvcRequestBuilders
//                .put(UPDATE_CATEGORY_URL,"backend")
//                .contentType(MediaType.APPLICATION_JSON_VALUE)
//                .accept(MediaType.APPLICATION_JSON_VALUE)
//                .content(jsonPayload);
//    }
//
//    public RequestBuilder getAllCategory() {
//        return MockMvcRequestBuilders
//                .get(GET_ALL_CATEGORY_URL);
//    }
//
//    public RequestBuilder getOneCategory() {
//        return MockMvcRequestBuilders
//                .get(GET_ONE_CATEGORY_URL,1);
//    }
//}
