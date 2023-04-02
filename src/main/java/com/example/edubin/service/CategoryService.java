package com.example.edubin.service;

import com.example.edubin.dto.request.CategoryRequest;
import com.example.edubin.enitity.CategoryEntity;
import com.example.edubin.enitity.CourseEntity;
import com.example.edubin.exception.PSQLException;
import com.example.edubin.exception.RecordNotFoundException;
import com.example.edubin.repository.CategoryRepository;
import com.example.edubin.repository.CourseRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.text.MessageFormat;
import java.util.List;

@Service
@RequiredArgsConstructor
public class CategoryService {

    private final CategoryRepository categoryRepository;
    private final CourseRepository courseRepository;
    private final MediaService mediaService;
    public void addCategory(CategoryEntity categoryEntity) {
        try {
            categoryRepository.save(categoryEntity);
        } catch (Exception e){
            throw new PSQLException(MessageFormat.format("ERROR: duplicate <= {0} =>  key value violates unique constraint",categoryEntity.getName()));
        }
        mediaService.createFolder(categoryEntity.getName());
    }
    public void addCategory(CategoryRequest category) {
        CategoryEntity categoryEntity = new CategoryEntity(category.getName());
        try {
            categoryRepository.save(categoryEntity);
        } catch (Exception e){
            throw new PSQLException(MessageFormat.format("ERROR: duplicate <= {0} =>  key value violates unique constraint",categoryEntity.getName()));
        }
        mediaService.createFolder(categoryEntity.getName());
    }

    public void deleteCategory(String name) {
        CategoryEntity categoryEntity = categoryRepository.findByName(name).orElseThrow(() -> new RecordNotFoundException(
                MessageFormat.format("name={0} category is not found in  database", name)
        ));
        categoryRepository.delete(categoryEntity);
        mediaService.deleteFolder(categoryEntity.getName());
    }

    public void updateCategory(CategoryRequest category, String name) {
        CategoryEntity categoryEntity = categoryRepository.findByName(name).orElseThrow(() -> new RecordNotFoundException(
                MessageFormat.format("name={0} category is not found in  database", name)
        ));
        categoryEntity.setName(category.getName());
        categoryRepository.save(categoryEntity);
    }

    public List<CategoryEntity> getAllCategoryList() {
        List<CategoryEntity> all = categoryRepository.findAll();
        if (all==null){
            throw new RecordNotFoundException("There are not category in database");
        }else {
            return all;
        }
    }

    public CategoryEntity findCategory(int category_di) {
        return categoryRepository.findById(category_di).orElseThrow(() -> new RecordNotFoundException(
                MessageFormat.format("id={0} category was not in database", category_di)
        ));
    }

    public List<CourseEntity> getCategoryById(int id) {
        CategoryEntity categoryEntity = categoryRepository.findById(id).orElseThrow(() -> new RecordNotFoundException(
                MessageFormat.format("id = {0} category was not found in database", id)
        ));
    return courseRepository.findByCategory(categoryEntity).orElseThrow(()->new RecordNotFoundException(
             MessageFormat.format("category with {0} was Not found in course database",categoryEntity.getName())
     ));
    }
}
