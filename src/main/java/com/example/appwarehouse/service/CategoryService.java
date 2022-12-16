package com.example.appwarehouse.service;

import com.example.appwarehouse.entity.Category;
import com.example.appwarehouse.payload.CategoryDto;
import com.example.appwarehouse.payload.Result;
import com.example.appwarehouse.repository.CategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CategoryService {

    @Autowired
    CategoryRepository categoryRepository;

    public Result addCategory(CategoryDto categoryDto) {

        Category category = new Category();
        category.setName(categoryDto.getName());
        if(categoryDto.getParentCategoryId() != null) {
            Optional<Category> optionalParentCategory = categoryRepository.findById(categoryDto.getParentCategoryId());
            if (optionalParentCategory.isEmpty()) {
                return Result.fail("Category not found");
            }
                category.setParentCategory(optionalParentCategory.get());
        }
        categoryRepository.save(category);
        return Result.success("Success", category);
    }

    public Result getCategories() {

        List<Category> categoryList = categoryRepository.getCategoriesByActive(true);
        return Result.success("Success", categoryList);
    }

    public Result updateCategory(Integer id, CategoryDto categoryDto) {
        Optional<Category> optionalCategory = categoryRepository.findById(id);
        if (optionalCategory.isEmpty()) {
            return Result.fail("Category not found");
        }
        Category editingcategory = optionalCategory.get();
        editingcategory.setName(categoryDto.getName());

        if(categoryDto.getParentCategoryId() != null) {
            Optional<Category> optionalParentCategory = categoryRepository.findById(categoryDto.getParentCategoryId());
            if (optionalParentCategory.isEmpty()) {
                return Result.fail("Category not found");
            }
            editingcategory.setParentCategory(optionalParentCategory.get());
        }
        categoryRepository.save(editingcategory);
        return Result.success("Category edited", editingcategory);
    }

    public Result deleteCategory(Integer id) {

        Optional<Category> optionalCategory = categoryRepository.findById(id);
        if (optionalCategory.isEmpty()) {
            return Result.fail("Category not found");
        }
        categoryRepository.deleteById(id);
        return Result.success("Category deleted");
    }
}
