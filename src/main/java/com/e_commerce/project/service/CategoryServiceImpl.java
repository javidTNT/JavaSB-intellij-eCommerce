package com.e_commerce.project.service;

import com.e_commerce.project.exceptions.APIExceptions;
import com.e_commerce.project.exceptions.ResourceNotFoundException;
import com.e_commerce.project.model.Category;
import com.e_commerce.project.payload.CategoryDTO;
import com.e_commerce.project.payload.CategoryResponse;
import com.e_commerce.project.repositories.CategoryRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CategoryServiceImpl implements CategoryService{

    @Autowired
    private CategoryRepository categoryRepository;
    @Autowired
    private ModelMapper modelMapper;

    @Override
    public CategoryResponse getAllCategories(Integer pageNumber, Integer pageSize, String sortBy, String sortOder) {
        Sort sortByAndOder = sortOder.equalsIgnoreCase("asc")
                ? Sort.by(sortBy).ascending()
                : Sort.by(sortBy).descending(); // kiểm tra hướng sắp xếp và tạo Sort tương ứng
        Pageable pageableDetails = PageRequest.of(pageNumber, pageSize, sortByAndOder); // tạo Pageable với số trang và kích thước trang
        Page<Category> categoryPage = categoryRepository.findAll(pageableDetails); // lấy danh sách các danh mục theo Pageable
        List<Category> categories = categoryPage.getContent(); // lấy nội dung của trang hiện tại
        if(categories.isEmpty()){
            throw new APIExceptions("No category created till now"); // nếu danh sách rỗng thì ném ra lỗi 404
        }
        List<CategoryDTO> categoryDTOS = categories.stream()
                .map(category -> modelMapper.map(category, CategoryDTO.class)) // chuyển đổi từ Category sang CategoryDTO
                .toList(); // chuyển đổi thành danh sách
        CategoryResponse categoryResponse = new CategoryResponse();
        categoryResponse.setContent(categoryDTOS); // đặt danh sách CategoryDTO vào CategoryResponse
        categoryResponse.setPageNumber(categoryPage.getNumber()); // đặt số trang
        categoryResponse.setPageSize(categoryPage.getSize()); // đặt kích thước trang
        categoryResponse.setTotalElements(categoryPage.getTotalElements()); // đặt tổng số phần tử
        categoryResponse.setTotalPages(categoryPage.getTotalPages()); // đặt tổng số trang
        categoryResponse.setLastPage(categoryPage.isLast()); // đặt xem có phải là trang cuối cùng hay không
        return categoryResponse;
    }// lấy danh sách tất cả các danh mục

    @Override
    public CategoryDTO createCategory(CategoryDTO categoryDTO) {
        Category category = modelMapper.map(categoryDTO, Category.class);
        Category categoryFromDB = categoryRepository.findByCategoryName(category.getCategoryName());
        if(categoryFromDB !=null){
            throw new APIExceptions("Category with name " + category.getCategoryName() + " already exists !");
        }
        Category saveCategory = categoryRepository.save(category);
        return modelMapper.map(saveCategory, CategoryDTO.class); // trả về danh mục đã tạo mới
    }// tạo mới danh mục, tự động tăng ID

    @Override
    public CategoryDTO deleteCategory(Long categoryId) {
        Category category = categoryRepository.findById(categoryId)
                .orElseThrow(() -> new ResourceNotFoundException("Category","CategoryId",categoryId));// tìm kiếm danh mục theo ID, nếu không tìm thấy thì ném ra lỗi 404
        categoryRepository.delete(category); // xóa danh mục theo ID
        return modelMapper.map(category, CategoryDTO.class); // trả về thông báo thành công
    }

    @Override
    public CategoryDTO updateCategory(CategoryDTO categoryDTO, Long categoryId) {
        // tìm kiếm danh mục theo ID, nếu không tìm thấy thì ném ra lỗi 404
        Category savedCategory = categoryRepository.findById(categoryId)
                .orElseThrow(() -> new ResourceNotFoundException("Category","CategoryId",categoryId));
        Category category = modelMapper.map(categoryDTO, Category.class); // chuyển đổi từ CategoryDTO sang Category
        category.setCategoryId(categoryId);
        savedCategory = categoryRepository.save(category);
        return modelMapper.map(savedCategory, CategoryDTO.class); // trả về danh mục đã cập nhật
    }
}
