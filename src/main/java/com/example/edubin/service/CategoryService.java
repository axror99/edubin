package com.example.edubin.service;

import com.example.edubin.dto.request.CategoryRequest;
import com.example.edubin.enitity.CategoryEntity1;
import com.example.edubin.enitity.CourseEntity1;
import com.example.edubin.exception.PSQLException;
import com.example.edubin.exception.RecordNotFoundException;
import com.example.edubin.repository.CategoryRepository;
import com.example.edubin.repository.CourseRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.text.MessageFormat;
import java.util.List;

@Service
@RequiredArgsConstructor
public class CategoryService {

    private final CategoryRepository categoryRepository;
    private final CourseRepository courseRepository;
    private final MediaService mediaService;
    public void addCategory(CategoryEntity1 categoryEntity1) {
        try {
            categoryRepository.save(categoryEntity1);
        } catch (Exception e){
            throw new PSQLException(MessageFormat.format("ERROR: duplicate <= {0} =>  key value violates unique constraint", categoryEntity1.getName()));
        }
    }

    public void deleteCategory(String name) {
        CategoryEntity1 categoryEntity1 = categoryRepository.findByName(name).orElseThrow(() -> new RecordNotFoundException(
                MessageFormat.format("name={0} category is not found in  database", name)
        ));
        categoryRepository.delete(categoryEntity1);
    }

    public void updateCategory(CategoryRequest category, String name) {
        CategoryEntity1 categoryEntity1 = categoryRepository.findByName(name).orElseThrow(() -> new RecordNotFoundException(
                MessageFormat.format("name={0} category is not found in  database", name)
        ));
        categoryEntity1.setName(category.getName1());
        categoryRepository.save(categoryEntity1);
    }

    public List<CategoryEntity1> getAllCategoryList() {
        List<CategoryEntity1> all = categoryRepository.findAll();
        if (all==null){
            throw new RecordNotFoundException("There are not category in database");
        }else {
            return all;
        }
    }

    public CategoryEntity1 findCategory(int category_di) {
        return categoryRepository.findById(category_di).orElseThrow(() -> new RecordNotFoundException(
                MessageFormat.format("id={0} category was not in database", category_di)
        ));
    }

    public List<CourseEntity1> getCategoryById(int id) {
        CategoryEntity1 categoryEntity1 = categoryRepository.findById(id).orElseThrow(() -> new RecordNotFoundException(
                MessageFormat.format("id = {0} category was not found in database", id)
        ));
    return courseRepository.findByCategory(categoryEntity1).orElseThrow(()->new RecordNotFoundException(
             MessageFormat.format("category with {0} was Not found in course database", categoryEntity1.getName())
     ));
    }
}
