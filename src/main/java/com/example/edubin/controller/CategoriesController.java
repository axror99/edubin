package com.example.edubin.controller;

import com.example.edubin.dto.request.CategoryRequest;
import com.example.edubin.dto.response.ApiResponse;
import com.example.edubin.enitity.CategoryEntity;
import com.example.edubin.enitity.CourseEntity;
import com.example.edubin.service.CategoryService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/category/")
@RequiredArgsConstructor
public class CategoriesController {

    //@Autowired
    private final CategoryService categoryService;

//    public CategoriesController(CategoryService categoryService) {
//        this.categoryService = categoryService;
//    }

    @PostMapping("/add")
    @ResponseStatus(HttpStatus.CREATED)
    @PreAuthorize("(hasRole('ADMIN') and hasAuthority('ADD')) or (hasRole('SUPER_ADMIN'))")
    private ApiResponse<Void> addCategory(@RequestBody CategoryRequest category) {
//        CategoryEntity categoryEntity = new CategoryEntity(category.getName());
//        categoryService.addCategory(categoryEntity);
        categoryService.addCategory(category);
        return new ApiResponse<>("category was saved successfully");
    }

    @DeleteMapping("/delete/{name}")
    @ResponseStatus(HttpStatus.OK)
    @PreAuthorize("(hasRole('ADMIN') and hasAuthority('DELETE')) or (hasRole('SUPER_ADMIN'))")
    private ApiResponse<Void> deleteCategory(@PathVariable("name") String name) {
        categoryService.deleteCategory(name);
        return new ApiResponse<>("category was deleted successfully");
    }

    @PutMapping("/update/{name}")
    @ResponseStatus(HttpStatus.OK)
    @PreAuthorize("(hasRole('ADMIN') and hasAuthority('UPDATE')) or (hasRole('SUPER_ADMIN'))")
    private ApiResponse<Void> updateCategory(@PathVariable("name") String name, @RequestBody CategoryRequest category) {
        categoryService.updateCategory(category, name);
        return new ApiResponse<>("category was updated successfully");
    }

    @GetMapping("/list")
    @ResponseStatus(HttpStatus.OK)
    @PreAuthorize("(hasRole('ADMIN') and hasAuthority('GET')) or (hasRole('SUPER_ADMIN'))")
    private ApiResponse<List<CategoryEntity>> getAllCategoryList() {
        List<CategoryEntity> allCategoryList = categoryService.getAllCategoryList();
        return new ApiResponse<>("here are your all categories list", allCategoryList);
    }

    @GetMapping("/get/{id}")
    private ApiResponse<List<CourseEntity>> getCategoryById(@PathVariable("id") int id) {
        return new ApiResponse<>("id=" + id + " category was taken successfully in database", categoryService.getCategoryById(id));
    }
}
