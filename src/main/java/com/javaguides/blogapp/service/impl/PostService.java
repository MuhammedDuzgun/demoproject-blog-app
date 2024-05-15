package com.javaguides.blogapp.service.impl;

import com.javaguides.blogapp.dto.PostDto;
import com.javaguides.blogapp.exception.ResourceNotFoundException;
import com.javaguides.blogapp.model.Post;
import com.javaguides.blogapp.repository.IPostRepository;
import com.javaguides.blogapp.service.IPostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

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
        Post post = mapToEntity(postDto);

        //Save Post
        Post newPost = postRepository.save(post);

        //convert entity to DTO
        PostDto postResponse = mapToDto(newPost);
        return postResponse;
    }

    @Override
    public List<PostDto> getAllPosts() {
        List<Post> postList = postRepository.findAll();
        return postList.stream().map(post -> mapToDto(post)).collect(Collectors.toList());
    }

    @Override
    public PostDto getPostById(long id) {
        Post post = postRepository.findById(id).orElseThrow(()-> new ResourceNotFoundException("Post" , "id" , id));
        return mapToDto(post);
    }

    @Override
    public PostDto updatePost(PostDto postDto, long id) {
        //get post by id
        Post post = postRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Post" , "id" , id));

        //update post
        post.setTitle(postDto.getTitle());
        post.setDescription(postDto.getDescription());
        post.setContent(postDto.getContent());

        //save changes
        Post updatedPost = postRepository.save(post);

        return mapToDto(updatedPost);
    }

    @Override
    public void deletePostById(long id) {
        //get post by id
        Post post = postRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Post" , "id" , id));
        postRepository.delete(post);
    }


    //Convert Entity to Dto
    private PostDto mapToDto(Post post) {
        PostDto postDto = new PostDto();
        postDto.setId(post.getId());
        postDto.setTitle(post.getTitle());
        postDto.setDescription(post.getDescription());
        postDto.setContent(post.getContent());
        return postDto;
    }

    private Post mapToEntity(PostDto postDto) {
        Post post = new Post();
        post.setTitle(postDto.getTitle());
        post.setDescription(postDto.getDescription());
        post.setContent(postDto.getContent());
        return post;
    }

}
