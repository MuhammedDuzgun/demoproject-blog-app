package com.javaguides.blogapp.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Schema(
        description = "Comment Dto Field"
)
@Data
public class CommentDto {

    @Schema(description = "Comment id")
    private long id;

    @Schema(description = "Comment name")
    @NotEmpty(message = "Name cannot be empty")
    private String name;

    @Schema(description = "Comment email")
    @NotEmpty(message = "Email cannot be empty")
    @Email
    private String email;

    @Schema(description = "Comment body")
    @NotEmpty(message = "Body cannot be empty")
    @Size(min = 10, message = "Comment body must be minimum 10 characters")
    private String body;

}
