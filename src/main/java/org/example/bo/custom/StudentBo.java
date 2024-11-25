package org.example.bo.custom;

import javafx.collections.ObservableList;
import org.example.bo.SuperBo;
import org.example.dto.StudentDto;
import org.example.dto.UserDto;
import org.example.entity.Student;
import org.example.entity.User;

import java.util.List;

public interface StudentBo extends SuperBo {
    boolean addStudent(StudentDto studentDto, UserDto userDto);

    ObservableList<StudentDto> getAllStudent();

    boolean updateStudent(StudentDto studentDto);

    boolean deleteStudent(String id);

    StudentDto searchStudent(String id);

    StudentDto getStudent(String cid);

    List<String> getAllProgrambyId(String id);
}
