package com.example.edubin.controller;

import com.example.edubin.dto.request.CategoryRequest;
import com.example.edubin.dto.response.ApiResponse;
import com.example.edubin.enitity.CategoryEntity1;
import com.example.edubin.enitity.CourseEntity1;
import com.example.edubin.service.CategoryService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/category/")
@RequiredArgsConstructor
public class CategoriesController {

    private final CategoryService categoryService;

    @PostMapping("/add")
    @ResponseStatus(HttpStatus.OK)
    private ApiResponse<Void> addCategory(@RequestBody CategoryRequest category) {
        CategoryEntity1 categoryEntity1 = new CategoryEntity1(category.getName1());
        categoryService.addCategory(categoryEntity1);
        return new ApiResponse<>("category was saved successfully");
    }

    @DeleteMapping("/delete/{name}")
    @ResponseStatus(HttpStatus.OK)
    private ApiResponse<Void> deleteCategory(@PathVariable("name") String name) {
        categoryService.deleteCategory(name);
        return new ApiResponse<>("category was deleted successfully");
    }

    @PutMapping("/update/{name}")
    @ResponseStatus(HttpStatus.OK)
    private ApiResponse<Void> updateCategory(@PathVariable("name") String name, @RequestBody CategoryRequest category) {
        categoryService.updateCategory(category, name);
        return new ApiResponse<>("category was updated successfully");
    }

    @GetMapping("/list")
    @ResponseStatus(HttpStatus.OK)
    private ApiResponse<List<CategoryEntity1>> getAllCategoryList() {
        List<CategoryEntity1> allCategoryList = categoryService.getAllCategoryList();
        return new ApiResponse<>("here are your all categories list", allCategoryList);
    }

    @GetMapping("/get/{id}")
    private ApiResponse<List<CourseEntity1>> getCategoryById(@PathVariable("id") int id) {
        return new ApiResponse<>("id="+id+" category was taken successfully in database",categoryService.getCategoryById(id));
    }
}
