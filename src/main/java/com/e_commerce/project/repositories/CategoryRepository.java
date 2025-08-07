package com.e_commerce.project.repositories;

import com.e_commerce.project.model.Category;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CategoryRepository extends JpaRepository<Category, Long> {
    Category findByCategoryName(@NotBlank @Size(min =5, message = "Category name must be at least 5 characters") String categoryName);
    // JpaRepository đã cung cấp các phương thức cơ bản như save, findById, findAll, deleteById
    // Bạn có thể thêm các phương thức tùy chỉnh nếu cần thiết
}
