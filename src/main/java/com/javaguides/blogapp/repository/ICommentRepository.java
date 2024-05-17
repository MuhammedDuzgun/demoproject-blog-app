package com.javaguides.blogapp.repository;

import com.javaguides.blogapp.model.Comment;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ICommentRepository extends JpaRepository<Comment,Long> {
    List<Comment> findCommentsByPostId(long postId);
}
