package com.example.edubin.service;

import com.example.edubin.dto.request.BlogRequest;
import com.example.edubin.enitity.BlogEntity;
import com.example.edubin.enitity.CategoryEntity;
import com.example.edubin.exception.RecordNotFoundException;
import com.example.edubin.repository.BlogRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.nio.file.Paths;
import java.text.MessageFormat;
import java.util.List;
import java.util.Objects;

@Service
@RequiredArgsConstructor
public class BlogService {

    private final BlogRepository blogRepository;
    private final CategoryService categoryService;
    private final MediaService mediaService;
    private  String PATH_IMAGE="D:\\EduBin\\edubin\\src\\main\\resources\\static\\images\\";

    public void addBlog(int id, BlogRequest blogRequest) {
        CategoryEntity category = categoryService.findCategory(id);
        String newPictureName = mediaService.generateRandomName(Objects.requireNonNull(blogRequest.getPicture().getOriginalFilename()));

        BlogEntity blog =BlogEntity.builder()
                .text(blogRequest.getText())
                .date(blogRequest.getDate())
                .headline(blogRequest.getHeadline())
                .personName(blogRequest.getPersonName())
                .picture(newPictureName)
                .categoryEntity(category)
                .build();
        mediaService.internalWrite(blogRequest.getPicture(), Paths.get(PATH_IMAGE+newPictureName));
        blogRepository.save(blog);
    }

    public void updateBlog(int id, BlogRequest blogRequest) {
        BlogEntity blog = blogRepository.findById(id).orElseThrow(() -> new RecordNotFoundException(
                MessageFormat.format("id = {0}  was not found in database", id)
        ));
        if (blogRequest.getPicture()!=null) {
            mediaService.deleteExistImage(blog.getPicture());
            String newPictureName = mediaService.generateRandomName(Objects.requireNonNull(blogRequest.getPicture().getOriginalFilename()));
            mediaService.internalWrite(blogRequest.getPicture(),Paths.get(PATH_IMAGE+newPictureName));
            blog.setPicture(newPictureName);
        }
        if (blogRequest.getText()!=null && !blogRequest.getText().equals("")){
            blog.setText(blogRequest.getText());
        }
        if (blogRequest.getPersonName()!=null && !blogRequest.getPersonName().equals("")){
            blog.setPersonName(blogRequest.getPersonName());
        }
        if (blogRequest.getHeadline()!=null && !blogRequest.getHeadline().equals("")){
            blog.setHeadline(blogRequest.getHeadline());
        }
        if (blogRequest.getDate()!=null){
            blog.setDate(blogRequest.getDate());
        }
        blogRepository.save(blog);
    }

    public List<BlogEntity> getListByCategory(int id) {
        return blogRepository.findByCategoryEntity_Id(id).orElseThrow(()-> new RecordNotFoundException(
                MessageFormat.format("id = {0} blogs were not found in database",id)
        ));
    }

    public void deleteBlog(int id) {
        BlogEntity blog = blogRepository.findById(id).orElseThrow(() -> new RecordNotFoundException(
                MessageFormat.format("id = {0} blog was not found in database", id)
        ));
        mediaService.deleteExistImage(blog.getPicture());
        blogRepository.delete(blog);
    }

    public BlogEntity findBlogById(int id) {
       return blogRepository.findById(id).orElseThrow(() -> new RecordNotFoundException(
                MessageFormat.format("id = {0} blog was not found in database", id)
        ));
    }
}
