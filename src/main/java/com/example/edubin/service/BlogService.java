package com.example.edubin.service;

import com.example.edubin.dto.request.BlogRequest;
import com.example.edubin.dto.response.BlogResponse;
import com.example.edubin.enitity.BlogEntity;
import com.example.edubin.enitity.CategoryEntity;
import com.example.edubin.exception.RecordNotFoundException;
import com.example.edubin.repository.BlogRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.nio.file.Paths;
import java.text.MessageFormat;
import java.time.LocalDate;
import java.util.List;
import java.util.Objects;

@Service
@RequiredArgsConstructor
public class BlogService {

    private final BlogRepository blogRepository;
    private final CategoryService categoryService;
    private final MediaService mediaService;
    private  String PATH_IMAGE="D:/EduBin/edubin/src/main/resources/static/assets/images/";

    public void addBlog(int id, BlogRequest blogRequest) {
        CategoryEntity category = categoryService.findCategory(id);
        String newPictureName = mediaService.generateRandomName(Objects.requireNonNull(blogRequest.getPicture().getOriginalFilename()));
        BlogEntity blog =BlogEntity.builder()
                .text(blogRequest.getText())
                .date(LocalDate.now())
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
        if (blogRequest.getCategory_di()!=null){
            CategoryEntity category = categoryService.findCategory(blogRequest.getCategory_di());
            blog.setCategoryEntity(category);
        }
        blog.setDate(LocalDate.now());
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
    public BlogResponse findResponseBlogById(int id) {
        BlogEntity blog = findBlogById(id);
        BlogResponse blogResponse =new BlogResponse();
        blogResponse.setId(blog.getId());
        blogResponse.setText(blog.getText());
        blogResponse.setHeadline(blog.getHeadline());
        blogResponse.setComments(blog.getComments());
        blogResponse.setCategoryName(blog.getCategoryEntity().getName());
        blogResponse.setDate(blog.getDate());
        blogResponse.setPersonName(blog.getPersonName());
        blogResponse.setPicture(blog.getPicture());
        return blogResponse;
    }

    public List<BlogEntity> getPageableListByCategory(int id,int page1, int size) {
        Pageable page = PageRequest.of(page1-1,size, Sort.by("id"));
        return blogRepository.findByCategoryEntity_Id(id,page).getContent();
//         return blogRepository.findAll(page).getContent();
    }

    public List<BlogEntity> findPopularBlogs() {
        return blogRepository.findAll();
    }
}
