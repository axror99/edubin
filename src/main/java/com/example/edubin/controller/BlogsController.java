package com.example.edubin.controller;

import com.example.edubin.dto.request.BlogRequest;
import com.example.edubin.dto.response.ApiResponse;
import com.example.edubin.dto.response.BlogResponse;
import com.example.edubin.enitity.BlogEntity;
import com.example.edubin.service.BlogService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/blog/")
@RequiredArgsConstructor
public class BlogsController {

    private final BlogService blogService;
    @PostMapping("/add/{id}")// category id
    @ResponseStatus(HttpStatus.OK)
    private ApiResponse<Void> addBlog(@PathVariable("id") int id, @ModelAttribute BlogRequest blogRequest){
        blogService.addBlog(id,blogRequest);
        return new ApiResponse<>("blog was saved successfully in database");
    }

    @PutMapping("/update/{id}")// blog id
    @ResponseStatus(HttpStatus.OK)
    private ApiResponse<Void> updateBlog(@PathVariable("id") int id, @ModelAttribute BlogRequest blogRequest){
        blogService.updateBlog(id, blogRequest);
        return new ApiResponse<>("blog was updated successfully in database");
    }
    @GetMapping("/list/{id}")// category id
    @ResponseStatus(HttpStatus.OK)
    private ApiResponse<List<BlogEntity>> getListByCategory(@PathVariable("id") int id){
        return new ApiResponse<>("blogs List was token successfully in database",blogService.getListByCategory(id));
    }

    @GetMapping("/list/page/{id}/{page}")// category id
    @ResponseStatus(HttpStatus.OK)
    private ApiResponse<List<BlogEntity>> getPageableListByCategory(@PathVariable("id") int id,@PathVariable("page") int page){
        return new ApiResponse<>("blogs List was token successfully in database",blogService.getPageableListByCategory(id,page,4));
    }

    @DeleteMapping("/delete/{id}")// blog id
    @ResponseStatus(HttpStatus.OK)
    private ApiResponse<Void> deleteBlog(@PathVariable("id") int id){
        blogService.deleteBlog(id);
        return new ApiResponse<>("blog was deleted successfully in database");
    }

    @GetMapping("/one/{id}")// blog id
    private ApiResponse<BlogResponse> getBlogById(@PathVariable("id")int id){
        return new ApiResponse<>("blog was token successfully in database",blogService.findResponseBlogById(id) );
    }

    @GetMapping("/popular")// blog id
    private ApiResponse<List<BlogEntity>> getPopularBlogs(){
        List<BlogEntity> popularBlogs = blogService.findPopularBlogs();
        return new ApiResponse<>(" popular blogs was token successfully in database", popularBlogs);
    }

}
