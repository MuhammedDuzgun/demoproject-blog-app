package com.javaguides.blogapp.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Schema(
        description = "Category Dto Field"
)
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class CategoryDto {

    @Schema(description = "category id")
    private Long id;

    @Schema(description = "category name")
    private String name;

    @Schema(description = "category description")
    private String description;

}
