package com.javaguides.blogapp.service;

import com.javaguides.blogapp.dto.PostDto;

public interface IPostService {
    PostDto createPost(PostDto postDto);
}
