package com.bhavitha.taskmanager.service;

import com.bhavitha.taskmanager.entity.Category;
import com.bhavitha.taskmanager.exception.CustomException;
import com.bhavitha.taskmanager.repository.CategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CategoryService {

    @Autowired
    private CategoryRepository categoryRepo;

    public Category createCategory(String name, Long userId) {
        Category category = new Category();
        category.setName(name);
        category.setUserId(userId);

        return categoryRepo.save(category);
    }

    public List<Category> getAllCategories(Long userId) {
        return categoryRepo.findByUserId(userId);
    }

    public Category updateCategory(Long id, String name, Long userId) {
        Category category = categoryRepo.findById(id)
                .orElseThrow(() -> new CustomException("Category not found"));

        if (!category.getUserId().equals(userId)) {
            throw new CustomException("Forbidden");
        }

        category.setName(name);
        return categoryRepo.save(category);
    }

    public void deleteCategory(Long id, Long userId) {
        Category category = categoryRepo.findById(id)
                .orElseThrow(() -> new CustomException("Category not found"));

        if (!category.getUserId().equals(userId)) {
            throw new CustomException("Forbidden");
        }

        categoryRepo.delete(category);
    }
}