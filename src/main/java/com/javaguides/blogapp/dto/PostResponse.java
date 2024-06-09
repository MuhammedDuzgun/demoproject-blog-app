package com.javaguides.blogapp.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Schema(
        description = "Post Response Field"
)
@Data
@AllArgsConstructor
@NoArgsConstructor
public class PostResponse {

    @Schema(description = "List of PostDto")
    private List<PostDto> content;

    @Schema(description = "Page no")
    private int pageNo;

    @Schema(description = "Page size")
    private int pageSize;

    @Schema(description = "Total elements")
    private long totalElements;

    @Schema(description = "Total Pages")
    private int totalPages;

    @Schema(description = "is Last Page of Content")
    private boolean last;

}
