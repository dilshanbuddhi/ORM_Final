package org.example.contraller;

import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import org.example.bo.BoFactory;
import org.example.bo.custom.StudentBo;
import org.example.bo.custom.UserBo;
import org.example.dto.StudentDto;
import org.example.dto.UserDto;

import java.time.LocalDate;

public class StudentManagementController {

    static UserDto liveUser;

    UserBo userBo = (UserBo) BoFactory.getBoFactory().getBO(BoFactory.BOTypes.USER);
    StudentBo studentBo = (StudentBo) BoFactory.getBoFactory().getBO(BoFactory.BOTypes.STUDENT);



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
    private TableView<StudentDto> tblStudents;

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

    public void initialize(){
        liveUser = LoginFormContraller.getLiveUserRole();
        setcellvaluefactory();
        getallStudent();
        checkrolll(liveUser);
    }

    private void checkrolll(UserDto liveUser) {
        if (liveUser.getRole().equals("user")){
            btnAddStudent.setVisible(true);
            btnDeleteStudent.setVisible(false);
            btnUpdateStudent.setVisible(true);
        }
    }


    public void setcellvaluefactory(){
        colID.setCellValueFactory(new PropertyValueFactory<>("id"));
        colName.setCellValueFactory(new PropertyValueFactory<>("name"));
        colEmail.setCellValueFactory(new PropertyValueFactory<>("email"));
        colTel.setCellValueFactory(new PropertyValueFactory<>("tel"));
        colAddress.setCellValueFactory(new PropertyValueFactory<>("address"));
        colDOB.setCellValueFactory(new PropertyValueFactory<>("dob"));
    }
    public void getallStudent() {
        ObservableList<StudentDto> studentDtos = studentBo.getAllStudent();

        tblStudents.setItems(studentDtos);
    }


    @FXML
    void addStudent(ActionEvent event) {

        String id = txtStudentID.getText();
        String name = txtStudentName.getText();
        String email = txtStudentEmail.getText();
        String tel = txtStudentTel.getText();
        String address = txtStudentAddress.getText();
        String dob = String.valueOf(txtStudentDOB.getValue());

        StudentDto studentDto = new StudentDto(id, name, email, tel, address, dob);
        boolean result = studentBo.addStudent(studentDto,liveUser);
        if (result){
            getallStudent();
            new Alert(Alert.AlertType.CONFIRMATION, "Add Successful").show();
        }

    }

    @FXML
    void deleteStudent(ActionEvent event) {
        String id = txtStudentID.getText();

        boolean isDelete = studentBo.deleteStudent(id);
        if (isDelete){
            getallStudent();
            new Alert(Alert.AlertType.CONFIRMATION, "Delete Successful").show();
        }
    }

    @FXML
    void searchStudent(ActionEvent event) {

        String id = txtStudentID.getText();
        StudentDto studentDto = studentBo.searchStudent(id);
        if (studentDto != null){
            txtStudentName.setText(studentDto.getName());
            txtStudentEmail.setText(studentDto.getEmail());
            txtStudentTel.setText(studentDto.getTel());
            txtStudentAddress.setText(studentDto.getAddress());
            txtStudentDOB.setValue(LocalDate.parse(studentDto.getDob()));
        }else {
            new Alert(Alert.AlertType.ERROR, "No Record Found").show();
        }
    }

    @FXML
    void updateStudent(ActionEvent event) {
        String name =  txtStudentName.getText();
        String email = txtStudentEmail.getText();
        String tel = txtStudentTel.getText();
        String address = txtStudentAddress.getText();
        String dob = String.valueOf(txtStudentDOB.getValue());
        String id = txtStudentID.getText();

        StudentDto studentDto = new StudentDto(id, name, email, tel, address, dob);

        boolean isUpdate = studentBo.updateStudent(studentDto);

        if (isUpdate){
            getallStudent();
            new Alert(Alert.AlertType.CONFIRMATION, "Update Successful").show();
        }
    }

}
