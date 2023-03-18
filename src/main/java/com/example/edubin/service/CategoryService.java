package com.example.edubin.service;

import com.example.edubin.dto.request.CategoryRequest;
import com.example.edubin.enitity.CategoryEntity;
import com.example.edubin.exception.PSQLException;
import com.example.edubin.exception.RecordNotFoundException;
import com.example.edubin.repository.CategoryRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.text.MessageFormat;
import java.util.List;

@Service
@RequiredArgsConstructor
public class CategoryService {

    private final CategoryRepository categoryRepository;
    public void addCategory(CategoryEntity categoryEntity) {
        try {
            categoryRepository.save(categoryEntity);
        } catch (Exception e){
            throw new PSQLException(MessageFormat.format("ERROR: duplicate <= {0} =>  key value violates unique constraint",categoryEntity.getName()));
        }
    }

    public void deleteCategory(String name) {
        CategoryEntity categoryEntity = categoryRepository.findByName(name).orElseThrow(() -> new RecordNotFoundException(
                MessageFormat.format("name={0} category is not found in  database", name)
        ));
        categoryRepository.delete(categoryEntity);
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
}
