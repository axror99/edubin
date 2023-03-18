package com.example.edubin.controller;

import com.example.edubin.dto.request.ContentRequest;
import com.example.edubin.dto.response.ApiResponse;
import com.example.edubin.enitity.ContentEntity;
import com.example.edubin.service.ContentService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/content/")
@RequiredArgsConstructor
public final class ContentController {
    private final ContentService contentService;

    @PostMapping("/add/{id}")
    @ResponseStatus(HttpStatus.OK)
    private ApiResponse<Void> addContentToCourse(@PathVariable("id") int id,@ModelAttribute ContentRequest content){
      contentService.saveContent(content,id);
      return new ApiResponse<>("content was saved successfully");
    }
}
