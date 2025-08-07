package com.e_commerce.project.service;

import com.e_commerce.project.model.Category;
import com.e_commerce.project.payload.CategoryDTO;
import com.e_commerce.project.payload.CategoryResponse;


public interface CategoryService {
    CategoryResponse getAllCategories(Integer pageNumber, Integer pageSize, String sortBy, String sortOder);// lấy danh sách tất cả các danh mục
    CategoryDTO createCategory(CategoryDTO categoryDTO);// tạo mới danh mục

    CategoryDTO deleteCategory(Long categoryId);// xóa danh mục theo ID

    CategoryDTO updateCategory(CategoryDTO categoryDTO, Long categoryId);
}
