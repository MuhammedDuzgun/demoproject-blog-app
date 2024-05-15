package com.javaguides.blogapp.service.impl;

import com.javaguides.blogapp.dto.PostDto;
import com.javaguides.blogapp.model.Post;
import com.javaguides.blogapp.repository.IPostRepository;
import com.javaguides.blogapp.service.IPostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PostService implements IPostService {

    private IPostRepository postRepository;

    @Autowired
    public PostService(IPostRepository postRepository) {
        this.postRepository = postRepository;
    }

    @Override
    public PostDto createPost(PostDto postDto) {

        //convert DTO to entity
        Post post = new Post();
        post.setTitle(postDto.getTitle());
        post.setDescription(postDto.getDescription());
        post.setContent(postDto.getContent());

        Post newPost = postRepository.save(post);

        //convert entity to DTO
        PostDto postResponse = new PostDto();
        postResponse.setId(newPost.getId());
        postResponse.setTitle(newPost.getTitle());
        postResponse.setDescription(newPost.getDescription());
        postResponse.setContent(newPost.getContent());

        return postResponse;
    }
}
