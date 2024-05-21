package com.javaguides.blogapp.repository;

import com.javaguides.blogapp.model.Category;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ICategoryRepository extends JpaRepository<Category, Long> {

}
