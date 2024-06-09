package com.javaguides.blogapp.controller;

import com.javaguides.blogapp.dto.PostDto;
import com.javaguides.blogapp.dto.PostResponse;
import com.javaguides.blogapp.service.IPostService;
import com.javaguides.blogapp.utils.AppConstants;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;
@Tag(
        name = "Post Api Controller"
)
@RestController
@RequestMapping("/api/posts")
public class PostController {

    private IPostService postService;

    @Autowired
    public PostController(IPostService postService) {
        this.postService = postService;
    }

    //create post rest api
    @Operation(
            summary = "Create Post REST Api",
            description = "Save Post in Database"
    )
    @ApiResponse(
            responseCode = "201",
            description = "Http Status 201 Created"
    )
    @SecurityRequirement(
            name = "Bearer Authentication"
    )
    @PreAuthorize("hasRole('ADMIN')")
    @PostMapping
    public ResponseEntity<PostDto> createPost(@Valid @RequestBody PostDto postDto) {
        return new ResponseEntity<>(postService.createPost(postDto) , HttpStatus.CREATED);
    }

    // get all posts rest api
    @Operation(
            summary = "Get All Post REST Api",
            description = "All Posts in database viewed"
    )
    @ApiResponse(
            responseCode = "200",
            description = "Http Status 200 OK"
    )
    @GetMapping
    public PostResponse getAllPosts(
            @RequestParam(value = "pageNo" , defaultValue = AppConstants.DEFAULT_PAGE_NUMBER, required = false) int pageNo,
            @RequestParam(value = "pageSize", defaultValue = AppConstants.DEFAULT_PAGE_SIZE, required = false) int pageSize,
            @RequestParam(value = "sortBy" , defaultValue = AppConstants.DEFAULT_SORT_BY , required = false) String sortBy,
            @RequestParam(value = "sortDir", defaultValue = AppConstants.DEFAULT_SORT_DIRECTION, required = false) String sortDir
    ) {
        return postService.getAllPosts(pageNo, pageSize, sortBy, sortDir);
    }

    //get post by id rest api
    @Operation(
            summary = "Get Post by Id REST Api",
            description = "Getting post from database"
    )
    @ApiResponse(
            responseCode = "200",
            description = "Http Status 200 OK"
    )
    @GetMapping("/{id}")
    public ResponseEntity<PostDto> getPostById(@PathVariable(name = "id") long id) {
        return ResponseEntity.ok(postService.getPostById(id));
    }

    //update post by id rest api
    @Operation(
            summary = "Update Post by Id REST Api",
            description = "Updating post in database"
    )
    @ApiResponse(
            responseCode = "200",
            description = "Http Status 200 OK "
    )
    @SecurityRequirement(
            name = "Bearer Authentication"
    )
    @PreAuthorize("hasRole('ADMIN')")
    @PutMapping("/{id}")
    public ResponseEntity<PostDto> updatePost(@PathVariable(name = "id") long id ,
                                              @Valid
                                              @RequestBody PostDto postDto) {
        PostDto responsePost = postService.updatePost(postDto , id);
        return new ResponseEntity<>(responsePost , HttpStatus.OK);
    }

    //delete post by id rest api
    @Operation(
            summary = "Delete Post by Id REST Api",
            description = "Deleting post from database"
    )
    @ApiResponse(
            responseCode = "200",
            description = "Http Status 200 OK"
    )
    @SecurityRequirement(
            name = "Bearer Authentication"
    )
    @PreAuthorize("hasRole('ADMIN')")
    @DeleteMapping("/{id}")
    public ResponseEntity<String> deletePost(@PathVariable(name = "id") long id) {
        postService.deletePostById(id);
        return new ResponseEntity<>("Post Deleted Successfuly" , HttpStatus.OK);
    }

    //get posts by category id
    @Operation(
            summary = "Get Post by Category REST Api",
            description = "Getting posts from database by filtering category"
    )
    @ApiResponse(
            responseCode = "200",
            description = "Http Status 200 OK"
    )
    @GetMapping("/category/{id}")
    public ResponseEntity<List<PostDto>> getPostsByCategory(@PathVariable(name = "id") Long categoryId) {
        List<PostDto> postDtos = postService.getPostsByCategory(categoryId);
        return ResponseEntity.ok(postDtos);
    }

}
