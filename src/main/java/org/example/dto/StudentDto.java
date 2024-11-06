package org.example.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter

public class StudentDto {
    private String id;
    private String name;
    private String email;
    private String tel;
    private String address;
    private String dob;
}
