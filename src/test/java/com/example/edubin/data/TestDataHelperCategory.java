package com.example.edubin.data;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.RequestBuilder;
import org.testcontainers.shaded.com.fasterxml.jackson.core.JsonProcessingException;
import org.testcontainers.shaded.com.fasterxml.jackson.databind.ObjectMapper;

import java.util.HashMap;
import java.util.Map;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;

@Component
public class TestDataHelperCategory {

    private static final String ADD_CATEGORY_URL="/api/category/add";
    private static final String DELETE_CATEGORY_URL="/api/category/delete/{name}";
    private static final String UPDATE_CATEGORY_URL="/api/category/update/{name}";
    private static final String LIST_CATEGORY_URL="/api/category/list";
    private static final String GET_ONE_CATEGORY_URL="/api/category/get/{id}";


    public RequestBuilder addCategory(String category) throws JsonProcessingException {
        Map<String,Object> map =new HashMap<>();
        map.put("name",category);
        return post(ADD_CATEGORY_URL)
                .contentType(MediaType.APPLICATION_JSON)
                .content(new ObjectMapper().writeValueAsBytes(map));
    }

    public RequestBuilder deleteCategory(String name){
        return delete(DELETE_CATEGORY_URL,name)
                .contentType(MediaType.APPLICATION_JSON);
    }

    public RequestBuilder updateCategory(String oldName,String newName) throws JsonProcessingException {
        Map<String,Object> map = new HashMap<>();
        map.put("name",newName);
        return put(UPDATE_CATEGORY_URL+oldName)
                .contentType(MediaType.APPLICATION_JSON)
                .content(new ObjectMapper().writeValueAsString(map));
    }

    public RequestBuilder listOfCategory(){
        return get(LIST_CATEGORY_URL);
    }

    public RequestBuilder getOneCategory(int id){
        return get(GET_ONE_CATEGORY_URL,id);
    }

}

