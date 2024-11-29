package org.example.entity;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.example.dto.StudentDto;

import java.util.List;

@AllArgsConstructor
@Getter
@Setter
@NoArgsConstructor

@Entity
public class Programme {
    @Id
    String programId;
    String name;
    String duration;
    double fees;

    public Programme(String programId, String name, String duration, double fees) {
        this.programId = programId;
        this.name = name;
        this.duration = duration;
        this.fees = fees;
    }

    @OneToMany(mappedBy = "program" , cascade = CascadeType.ALL)
    private List<Student_programDetail> studentProgramDetails;



}
