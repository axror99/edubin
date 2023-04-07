package com.example.edubin.data;

import com.example.edubin.service.EmployeeService;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.RequestBuilder;
import org.testcontainers.shaded.com.fasterxml.jackson.core.JsonProcessingException;
import org.testcontainers.shaded.com.fasterxml.jackson.databind.ObjectMapper;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;

@Component
public class TestDataHelperEmployee {

    private static final String ADD_EMPLOYEE_URL="/api/employee/add";
    private static final String DELETE_EMPLOYEE_URL="/api/employee/delete/{id}";
    private static final String UPDATE_EMPLOYEE_URL="/api/employee/update/{id}";
    private static final String UPDATE_HIMSELF__URL="/api/employee/update/himself";
    private static final String LIST_ALL_EMPLOYEE_URL="/api/employee/list";
    private static final String LIST_ADMINS_URL="/api/employee/listAdmin";
    private static final String LIST_TEACHER_URL="/api/employee/listTeacher";
    private static final String GET_ONE_TEACHER_URL="/api/employee/teacher";

    @Autowired
    private MockMvc mockMvc;



    public RequestBuilder shouldAddEmployee() throws JsonProcessingException {
        Map<String,Object> map =new HashMap<>();
        map.put("name","Odil");
        map.put("username","odil");
        map.put("password","12AA");
        map.put("email","odil@gmail.com");
        map.put("birthday","1999-04-24");
        map.put("roles", List.of("TEACHER"));
        map.put("permissionList",List.of("READ","ADD"));

        return post(ADD_EMPLOYEE_URL)
                .contentType(MediaType.APPLICATION_JSON)
                .content(new ObjectMapper().writeValueAsString(map));
    }
    public RequestBuilder shouldDeleteEmployee(int id){
        return delete(DELETE_EMPLOYEE_URL,id)
                .contentType(MediaType.APPLICATION_JSON);
    }

    public RequestBuilder shouldUpdateEmployee(int id) throws JsonProcessingException {
        Map<String,Object> map =new HashMap<>();
        map.put("name","Bekzod");
        map.put("username","bek");
        map.put("password","AAA999");
        map.put("email","bek@gmail.com");
        map.put("birthday","1998-01-29");
        map.put("roles", List.of("TEACHER"));
        map.put("permissionList",List.of("DELETE","ADD"));

        return put(UPDATE_EMPLOYEE_URL,id)
                .contentType(MediaType.APPLICATION_JSON)
                .content(new ObjectMapper().writeValueAsString(map));
    }

}
