package com.javaguides.blogapp.service.impl;

import com.javaguides.blogapp.dto.CommentDto;
import com.javaguides.blogapp.exception.BlogAPIException;
import com.javaguides.blogapp.exception.ResourceNotFoundException;
import com.javaguides.blogapp.model.Comment;
import com.javaguides.blogapp.model.Post;
import com.javaguides.blogapp.repository.ICommentRepository;
import com.javaguides.blogapp.repository.IPostRepository;
import com.javaguides.blogapp.service.ICommentService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class CommentService implements ICommentService {

    private ICommentRepository commentRepository;
    private IPostRepository postRepository;
    private ModelMapper modelMapper;

    @Autowired
    public CommentService(ICommentRepository commentRepository, IPostRepository postRepository, ModelMapper modelMapper) {
        this.commentRepository = commentRepository;
        this.postRepository = postRepository;
        this.modelMapper = modelMapper;
    }

    @Override
    public CommentDto createComment(long postId, CommentDto commentDto) {
        //convert dto to entity
        Comment comment = mapToEntity(commentDto);

        //retrive post entity by id
        Post post = postRepository.findById(postId).orElseThrow(() -> new ResourceNotFoundException("Post" , "id", postId));

        //set post to comment entity
        comment.setPost(post);

        //save comment
        Comment newComment = commentRepository.save(comment);

        return mapToDto(newComment);
    }

    @Override
    public List<CommentDto> getCommentsByPostId(long postId) {
        //retrive comments by post id
        List<Comment> commentList = commentRepository.findCommentsByPostId(postId);

        //convert list of comment to list of dto
        return commentList.stream().map(comment -> mapToDto(comment)).collect(Collectors.toList());
    }

    @Override
    public CommentDto getCommentById(long postId, long commentId) {
        //retrive post by id
        Post post = postRepository.findById(postId).orElseThrow(()-> new ResourceNotFoundException("Post" , "id", postId));

        //retrive comment by id
        Comment comment = commentRepository.findById(commentId).orElseThrow(() -> new ResourceNotFoundException("Comment", "id" , commentId));

        //check that comment belongs to post
        if(!comment.getPost().getId().equals(post.getId())){
            throw new BlogAPIException(HttpStatus.BAD_REQUEST, "Comment Does not belongs to Post");
        }

        return mapToDto(comment);
    }

    @Override
    public CommentDto updateComment(long postId, long commentId, CommentDto commentDto) {
        //retrive post by id
        Post post = postRepository.findById(postId).orElseThrow(()-> new ResourceNotFoundException("Post" , "id", postId));

        //retrive comment by id
        Comment comment = commentRepository.findById(commentId).orElseThrow(() -> new ResourceNotFoundException("Comment", "id" , commentId));

        //check that comment belongs to post
        if(!comment.getPost().getId().equals(post.getId())){
            throw new BlogAPIException(HttpStatus.BAD_REQUEST, "Comment Does not belongs to Post");
        }

        comment.setName(commentDto.getName());
        comment.setEmail(commentDto.getEmail());
        comment.setBody(commentDto.getBody());

        Comment updatedComment = commentRepository.save(comment);

        return mapToDto(updatedComment);
    }

    @Override
    public void deleteComment(long postId, long commentId) {
        //retrive post by id
        Post post = postRepository.findById(postId).orElseThrow(()-> new ResourceNotFoundException("Post" , "id", postId));

        //retrive comment by id
        Comment comment = commentRepository.findById(commentId).orElseThrow(() -> new ResourceNotFoundException("Comment", "id" , commentId));

        //check that comment belongs to post
        if(!comment.getPost().getId().equals(post.getId())){
            throw new BlogAPIException(HttpStatus.BAD_REQUEST, "Comment Does not belongs to Post");
        }

        commentRepository.delete(comment);
    }

    private CommentDto mapToDto(Comment comment) {
        CommentDto commentDto = modelMapper.map(comment,CommentDto.class);
        return commentDto;
    }

    private Comment mapToEntity(CommentDto commentDto) {
        Comment comment = modelMapper.map(commentDto,Comment.class);
        return comment;
    }

}
