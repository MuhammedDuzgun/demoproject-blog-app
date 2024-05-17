package com.javaguides.blogapp.service;

import com.javaguides.blogapp.dto.CommentDto;

import java.util.List;

public interface ICommentService {
    CommentDto createComment(long postId,CommentDto commentDto);
    List<CommentDto> getCommentsByPostId(long postId);
    CommentDto getCommentById(long postId, long commentId);
    CommentDto updateComment(long postId, long commentId, CommentDto commentDto);
    void deleteComment(long postId, long commentId);

}
