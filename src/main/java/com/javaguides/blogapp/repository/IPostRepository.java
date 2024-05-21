package com.javaguides.blogapp.repository;

import com.javaguides.blogapp.model.Post;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface IPostRepository extends JpaRepository<Post, Long> {

    List<Post>  findByCategoryId(Long categoryId);

}
