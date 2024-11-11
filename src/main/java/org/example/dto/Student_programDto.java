package org.example.dto;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.example.entity.Programme;
import org.example.entity.Student;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class Student_programDto {
    private String spid;
    private String registerDate;
    private StudentDto student;
    private ProgramDto program;
}
