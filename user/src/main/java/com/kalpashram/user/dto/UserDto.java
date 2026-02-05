package com.kalpashram.user.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import lombok.Data;


@Data
@Schema(
        name = "User",
        description = "Schema to hold user information"
)
public class UserDto {

    private Long userId;
    @Schema(description = "Full name of the user", example = "Varsha Kumari")
    @NotBlank(message = "Name is required")
    private String name;
    @Schema(description = "Email of the user", example = "varsha.kumari@kalpashram.com")
    @NotBlank(message = "email is required")
    @Email(message = "Invalid email")
    private String email;
    @Schema(description = "Mobile number of the user", example = "1234567890")
    @Pattern(regexp = "^[0-9]{10}$", message = "Mobile number must be 10 digits")
    private String mobileNumber;
    @Schema(description = "Role of the user", example = "ADMIN")
    @NotBlank
    private String userRole;

}
