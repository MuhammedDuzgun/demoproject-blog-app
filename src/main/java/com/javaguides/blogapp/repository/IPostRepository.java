package com.javaguides.blogapp.repository;

import com.javaguides.blogapp.model.Post;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IPostRepository extends JpaRepository<Post, Long> {

}
