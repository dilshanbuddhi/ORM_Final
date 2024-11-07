package org.example.dto;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@Getter
@Setter
@NoArgsConstructor
public class UserDto {

    private long id;
    private String username;
    private String email;
    private String password;
    private String role;

    public UserDto(String username, String email, String password, String admin) {
        this.username = username;
        this.email = email;
        this.password = password;
        this.role = admin;
    }

    public UserDto(long id, String username, String email, String role) {
        this.id = id;
        this.username = username;
        this.email = email;
        this.role = role;
    }
}
