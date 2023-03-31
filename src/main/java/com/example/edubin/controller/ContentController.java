package com.example.edubin.controller;

import com.example.edubin.dto.request.ContentRequest;
import com.example.edubin.dto.response.ApiResponse;
import com.example.edubin.enitity.ContentEntity;
import com.example.edubin.service.ContentService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/content/")
@RequiredArgsConstructor
public class ContentController {
    private final ContentService contentService;

    @PostMapping("/add/{id}")
    @ResponseStatus(HttpStatus.OK)
    private ApiResponse<Void> addContentToCourse(@PathVariable("id") int id,@ModelAttribute ContentRequest content){
      contentService.saveContent(content,id);
      return new ApiResponse<>("content was saved successfully");
    }
    @PutMapping("/update/{id}")
    @ResponseStatus(HttpStatus.OK)
    private ApiResponse<Void> updateContent(@PathVariable("id") int id,@ModelAttribute ContentRequest contentRequest){
        contentService.updateContent(id,contentRequest);
        return new ApiResponse<>("content was updated successfully");
    }
    @DeleteMapping("/delete/{id}")
    @ResponseStatus(HttpStatus.OK)
    private ApiResponse<Void> deleteContent(@PathVariable("id") int id){
        contentService.deleteContent(id);
        return new ApiResponse<>("content was deleted successfully");
    }
    @GetMapping("/list")
    @ResponseStatus(HttpStatus.OK)
    private ApiResponse<List<ContentEntity>> getContentList(){
        return new ApiResponse<>("ContentList is here",contentService.getContentList());
    }
}
