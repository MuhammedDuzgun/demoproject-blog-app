package com.javaguides.blogapp.service;

import com.javaguides.blogapp.dto.PostDto;

import java.util.List;

public interface IPostService {
    PostDto createPost(PostDto postDto);

    List<PostDto> getAllPosts();

    PostDto getPostById(long id);

    PostDto updatePost(PostDto postDto , long id);

    void deletePostById(long id);

}
