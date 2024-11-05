package org.example.contraller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;

public class StudentManagementController {

    @FXML
    private Button btnAddStudent;

    @FXML
    private Button btnDeleteStudent;

    @FXML
    private Button btnSearchStudent;

    @FXML
    private Button btnUpdateStudent;

    @FXML
    private TableColumn<?, ?> colAddress;

    @FXML
    private TableColumn<?, ?> colDOB;

    @FXML
    private TableColumn<?, ?> colEmail;

    @FXML
    private TableColumn<?, ?> colID;

    @FXML
    private TableColumn<?, ?> colName;

    @FXML
    private TableColumn<?, ?> colTel;

    @FXML
    private TableView<?> tblStudents;

    @FXML
    private TextField txtStudentAddress;

    @FXML
    private DatePicker txtStudentDOB;

    @FXML
    private TextField txtStudentEmail;

    @FXML
    private TextField txtStudentID;

    @FXML
    private TextField txtStudentName;

    @FXML
    private TextField txtStudentTel;

    @FXML
    void addStudent(ActionEvent event) {
        String role = LoginFormContraller.getLiveUserRole();

        String id = txtStudentID.getText();
        String name = txtStudentName.getText();
        String email = txtStudentEmail.getText();
        String tel = txtStudentTel.getText();
        String address = txtStudentAddress.getText();
        String dob = txtStudentDOB.getValue().toString();



    }

    @FXML
    void deleteStudent(ActionEvent event) {

    }

    @FXML
    void searchStudent(ActionEvent event) {

    }

    @FXML
    void updateStudent(ActionEvent event) {

    }

}
