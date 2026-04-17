package com.bhavitha.taskmanager.controller;

import com.bhavitha.taskmanager.entity.Category;
import com.bhavitha.taskmanager.service.CategoryService;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/categories")
public class CategoryController {

    @Autowired
    private CategoryService categoryService;

    @PostMapping
    public Category createCategory(@RequestParam String name,
                                   HttpServletRequest request) {

        Long userId = (Long) request.getAttribute("userId");
        return categoryService.createCategory(name, userId);
    }

    @GetMapping
    public List<Category> getAllCategories(HttpServletRequest request) {

        Long userId = (Long) request.getAttribute("userId");
        return categoryService.getAllCategories(userId);
    }

    @PutMapping("/{id}")
    public Category updateCategory(@PathVariable Long id,
                                   @RequestParam String name,
                                   HttpServletRequest request) {

        Long userId = (Long) request.getAttribute("userId");
        return categoryService.updateCategory(id, name, userId);
    }

    @DeleteMapping("/{id}")
    public String deleteCategory(@PathVariable Long id,
                                 HttpServletRequest request) {

        Long userId = (Long) request.getAttribute("userId");
        categoryService.deleteCategory(id, userId);
        return "Category deleted";
    }
}