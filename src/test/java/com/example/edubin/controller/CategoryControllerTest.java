package com.example.edubin.controller;

import com.example.edubin.CommonIntegrationTest;
import com.example.edubin.WithAuthentication;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.test.web.servlet.MockMvc;
import org.testcontainers.lifecycle.Startables;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

public class CategoryControllerTest extends CommonIntegrationTest {


    @BeforeAll
    static void beforeAll() {
        Startables.deepStart(postgres).join();
    }

    @AfterEach
    public void after() {
        categoryRepository.deleteAll();
    }
    @Autowired
    private MockMvc mockMvc;

    @Test
    @DisplayName(value = "added category successfully")
    @WithAuthentication(username = "owner")
    public void shouldAddCategory() throws Exception {
        mockMvc.perform(testDataHelperCategory.addCategory()).andExpect(status().isOk());
    }
    @Test
    @DisplayName(value = "not added same category successfully")
    @WithAuthentication(username = "owner")
    public void shouldNotAddSameCategory() throws Exception {
        mockMvc.perform(testDataHelperCategory.addSameCategory()).andExpect(status().isOk());
        mockMvc.perform(testDataHelperCategory.addSameCategory()).andExpect(status().isConflict());
    }

    @Test
    @DisplayName(value = "not added Null category successfully")
    @WithAuthentication(username = "owner")
    public void shouldAddNullCategory() throws Exception {
        mockMvc.perform(testDataHelperCategory.addNullCategory()).andExpect(status().isConflict());
    }

    @Test
    @DisplayName(value = "error happen when not Authentication to be added")
    public void shouldNotAddCategory() throws Exception {
        mockMvc.perform(testDataHelperCategory.addCategoryWithoutToken()).andExpect(status().isForbidden());
    }

    @Test
    @DisplayName(value = "delete category successfully")
    @WithAuthentication(username = "me")
    public void shouldDeleteCategory() throws Exception {
        mockMvc.perform(testDataHelperCategory.addCategory());
        mockMvc.perform(testDataHelperCategory.deleteExistCategory()).andExpect(status().isOk());
    }

    @Test
    @DisplayName(value = "not delete category which is not exist")
    @WithAuthentication(username = "me")
    public void shouldNotDeleteCategory() throws Exception {
        mockMvc.perform(testDataHelperCategory.deleteNotExistCategory()).andExpect(status().isNotFound());
    }

    @Test
    @DisplayName(value = "update category successfully")
    @WithAuthentication(username = "me")
    public void shouldUpdateCategory() throws Exception {
        mockMvc.perform(testDataHelperCategory.addCategory());
        mockMvc.perform(testDataHelperCategory.updateCategory()).andExpect(status().isOk());
    }

    @Test
    @DisplayName(value = "does not update not exist category")
    @WithAuthentication(username = "me")
    public void shouldNotUpdateNotExistCategory() throws Exception {
        mockMvc.perform(testDataHelperCategory.updateCategory()).andExpect(status().isNotFound());
    }

    @Test
    @DisplayName(value = "get all category list")
    public void shouldGetAllCategory() throws Exception {
        mockMvc.perform(testDataHelperCategory.addCategory());
        mockMvc.perform(testDataHelperCategory.addSameCategory());
        mockMvc.perform(testDataHelperCategory.getAllCategory()).andExpect(status().isOk());
    }


    @Test
    @DisplayName(value = "get one category")
    @WithAuthentication(username = "me")
    public void shouldGetOneCategory() throws Exception {
        mockMvc.perform(testDataHelperCategory.addCategory());
        mockMvc.perform(testDataHelperCategory.getOneCategory()).andExpect(status().isOk());
    }

    @Test
    @DisplayName(value = "should not get one category")
    @WithAuthentication(username = "me")
    public void shouldNotGetOneCategory() throws Exception {
        mockMvc.perform(testDataHelperCategory.getOneCategory()).andExpect(status().isNotFound());
    }
}
