package com.javaguides.blogapp.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Schema(
        description = "Login Dto Field"
)
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class LoginDto {

    @Schema(description = "Username or Email for Login")
    private String usernameOrEmail;

    @Schema(description = "Password for Login")
    private String password;

}
