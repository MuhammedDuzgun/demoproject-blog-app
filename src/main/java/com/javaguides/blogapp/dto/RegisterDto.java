package com.javaguides.blogapp.dto;


import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Schema(
        description = "Register Dto Field"
)
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class RegisterDto {

    @Schema(description = "name for register")
    private String name;

    @Schema(description = "username for register")
    private String username;

    @Schema(description = "email for register")
    private String email;

    @Schema(description = "password for register")
    private String password;

}
