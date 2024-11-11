package org.example.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter

@Entity
public class Student {
    @Id
    private String id;
    private String name;
    private String email;
    private String tel;
    private String address;
    private String dob;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    public Student(String id, String name, String email, String tel, String address, String dob) {
        this.id = id;
        this.name = name;
        this.email = email;
        this.tel = tel;
        this.address = address;
        this.dob = dob;
    }

   @OneToMany(mappedBy = "student")
    private List<Student_programDetail> studentProgramDetails;

}
