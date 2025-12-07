package at.dto.request;

import lombok.Data;

@Data
public class UserRegisterRequest {
    private String gender;
    private String firstName;
    private String lastName;
    private String email;
    private String password;
    private String confirmPassword;
}
