package com.example.appwarehouse.controller;

import com.example.appwarehouse.entity.Category;
import com.example.appwarehouse.payload.CategoryDto;
import com.example.appwarehouse.payload.Result;
import com.example.appwarehouse.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/category")
public class CategoryController {

    @Autowired
    CategoryService categoryService;

    @PostMapping
    public Result addCategory(@RequestBody CategoryDto categoryDto) {

        Result result = categoryService.addCategory(categoryDto);
        return result;
    }

    @GetMapping
    public Result getCategories() {

        Result result = categoryService.getCategories();
        return result;
    }

    @PutMapping("/{id}")
    public Result updateCategory(@PathVariable Integer id, @RequestBody CategoryDto categoryDto) {

        Result result = categoryService.updateCategory(id, categoryDto);
        return result;
    }

    @DeleteMapping("/{id}")
    public Result deleteCategory(@PathVariable Integer id) {

        Result result = categoryService.deleteCategory(id);
        return result;
    }
}
