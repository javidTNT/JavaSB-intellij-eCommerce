package com.e_commerce.project.controller;

import com.e_commerce.project.config.AppConstants;
import com.e_commerce.project.payload.CategoryDTO;
import com.e_commerce.project.payload.CategoryResponse;
import com.e_commerce.project.service.CategoryService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api") // định nghĩa đường dẫn chung cho tất cả các API
public class CategoryController {
    @Autowired
    private CategoryService categoryService;


    //@GetMapping("/api/public/categories")// lấy danh sách tất cả các danh mục
    @RequestMapping(value = "/public/categories", method = RequestMethod.GET)
    public ResponseEntity<CategoryResponse> getAllCategories(
            @RequestParam(name = "pageNumber", defaultValue = AppConstants.DEFAULT_PAGE_NUMBER, required = false) Integer pageNumber,
            @RequestParam(name = "pageSize", defaultValue = AppConstants.DEFAULT_PAGE_SIZE, required = false) Integer pageSize,
            @RequestParam(name = "sortBy", defaultValue = AppConstants.SORT_CATEGORY_BY, required = false) String sortBy,
            @RequestParam(name = "sortOder", defaultValue = AppConstants.SORT_DIR, required = false) String sortOder
    ) {
        CategoryResponse categoryResponse = categoryService.getAllCategories(pageNumber, pageSize, sortBy, sortOder);
        return new ResponseEntity<>(categoryResponse, HttpStatus.OK);
    }

    //@PostMapping("/api/public/categories")// tạo mới danh mục
    @RequestMapping(value = "/public/categories", method = RequestMethod.POST)
    public ResponseEntity<CategoryDTO> createCategory(@Valid @RequestBody CategoryDTO categoryDTO) {
        CategoryDTO saveCategoryDTO = categoryService.createCategory(categoryDTO);
        return new ResponseEntity<>(saveCategoryDTO, HttpStatus.CREATED);
    }

    //@DeleteMapping("/api/admin/categories/{categoryId}")// xóa danh mục theo ID
    @RequestMapping(value = "/admin/categories/{categoryId}", method = RequestMethod.DELETE)
    public ResponseEntity<CategoryDTO> deleteCategory(@PathVariable Long categoryId) {
            CategoryDTO deleteCategory = categoryService.deleteCategory(categoryId);// gọi service để xóa danh mục
            return new ResponseEntity<>(deleteCategory, HttpStatus.OK);// trả về thông báo thành công
    }

    //@PutMapping("/api/public/categories/{categoryId}")// cập nhật danh mục
    @RequestMapping(value = "/public/categories/{categoryId}", method = RequestMethod.PUT)
    public ResponseEntity<CategoryDTO> updateCategory(@Valid@RequestBody CategoryDTO categoryDTO, @PathVariable Long categoryId) {
            CategoryDTO savedCategoryDTO = categoryService.updateCategory(categoryDTO,categoryId);// gọi service để xóa danh mục
            return new ResponseEntity<>(savedCategoryDTO, HttpStatus.OK);// trả về thông báo thành công
    }
}



