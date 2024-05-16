package com.javaguides.blogapp.service;

import com.javaguides.blogapp.dto.PostDto;
import com.javaguides.blogapp.dto.PostResponse;

import java.util.List;

public interface IPostService {
    PostDto createPost(PostDto postDto);

    PostResponse getAllPosts(int pageNo, int pageSize, String sortBy, String sortDir);

    PostDto getPostById(long id);

    PostDto updatePost(PostDto postDto , long id);

    void deletePostById(long id);

}
