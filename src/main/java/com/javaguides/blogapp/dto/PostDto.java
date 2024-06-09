package com.javaguides.blogapp.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;
import lombok.Data;

import java.util.Set;

@Schema(
        description = "Post Dto Field"
)
@Data
public class PostDto {

    @Schema(description = "Post id")
    private long id;

    @Schema(description = "Post title")
    @NotEmpty
    @Size(min = 2, message = "Post title should have at least 2 characters")
    private String title;

    @Schema(description = "Post Description")
    @NotEmpty
    @Size(min = 10, message = "Post Description should have at least 10 characters")
    private String description;

    @Schema(description = "Post Content")
    @NotEmpty
    private String content;

    @Schema(description = "Post's Comment List")
    private Set<CommentDto> comments;

    @Schema(description = "Category id")
    private Long categoryId;

}
