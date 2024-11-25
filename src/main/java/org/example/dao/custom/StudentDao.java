package org.example.dao.custom;

import org.example.dao.CrudDao;
import org.example.entity.Student;
import org.example.entity.User;

import java.util.List;

public interface StudentDao extends CrudDao<Student> {
    boolean addStudent(Student student, User user);

    List<Student> getAllStudents();

    boolean update(Student student);

    boolean delete(String id);

    Student search(String id);

    List<String> getAllProgrambyId(String id);
}
