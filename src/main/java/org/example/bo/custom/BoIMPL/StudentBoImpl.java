package org.example.bo.custom.BoIMPL;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import org.example.bo.BoFactory;
import org.example.bo.custom.StudentBo;
import org.example.dao.DaoFactory;
import org.example.dao.custom.StudentDao;
import org.example.dto.StudentDto;
import org.example.dto.UserDto;
import org.example.entity.Student;
import org.example.entity.User;

import java.util.ArrayList;
import java.util.List;

public class StudentBoImpl implements StudentBo {
    StudentDao studentDao = (StudentDao) DaoFactory.getdaoFactory().getDao(DaoFactory.DaoTypes.STUDENT);
    @Override
    public boolean addStudent(StudentDto se, UserDto ue) {
        return studentDao.addStudent(new Student(se.getId(), se.getName(), se.getEmail(), se.getTel(), se.getAddress(), se.getDob()), new User(ue.getId(), ue.getUsername(), ue.getEmail(), ue.getPassword(), ue.getRole())); //To change body of generated methods, choose Tools | Templates.ser);
    }

    @Override
    public ObservableList<StudentDto> getAllStudent() {
        List<StudentDto> studentDtos = new ArrayList<>();
        List<Student> students = studentDao.getAllStudents();
        for (Student student : students) {
            studentDtos.add(new StudentDto(student.getId(), student.getName(), student.getEmail(), student.getTel(), student.getAddress(), student.getDob()));
        }

        return FXCollections.observableArrayList(studentDtos);
    }

    @Override
    public boolean updateStudent(StudentDto studentDto) {
        return studentDao.update(new Student(studentDto.getId(), studentDto.getName(), studentDto.getEmail(), studentDto.getTel(), studentDto.getAddress(), studentDto.getDob()));
    }

    @Override
    public boolean deleteStudent(String id) {
        return studentDao.delete(id);
    }

    @Override
    public StudentDto searchStudent(String id) {
        Student student = studentDao.search(id);
        if (student == null) {
            return null;
        } else {
            return new StudentDto(student.getId(), student.getName(), student.getEmail(), student.getTel(), student.getAddress(), student.getDob());
        }
    }

    @Override
    public StudentDto getStudent(String cid) {
        Student student = studentDao.search(cid);
        if (student != null) {
            return new StudentDto(student.getId(), student.getName(), student.getEmail(), student.getTel(), student.getAddress(), student.getDob());
        }
        return null;
    }
}
