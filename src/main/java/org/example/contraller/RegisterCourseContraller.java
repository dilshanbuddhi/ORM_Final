package org.example.contraller;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import org.example.bo.BoFactory;
import org.example.bo.custom.Course_Refistration;
import org.example.bo.custom.ProgramBo;
import org.example.bo.custom.StudentBo;
import org.example.dto.PaymentDto;
import org.example.dto.ProgramDto;
import org.example.dto.StudentDto;
import org.example.dto.Student_programDto;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

public class RegisterCourseContraller {
    StudentBo studentBo = (StudentBo) BoFactory.getBoFactory().getBO(BoFactory.BOTypes.STUDENT);
    ProgramBo programBo = (ProgramBo) BoFactory.getBoFactory().getBO(BoFactory.BOTypes.PROGRAM);
    Course_Refistration courseRefistration = ( Course_Refistration) BoFactory.getBoFactory().getBO(BoFactory.BOTypes.COURSE);

    @FXML
    private Button btnDelete;

    @FXML
    private Button btnRegister;

    @FXML
    private Button btnSearch;

    @FXML
    private ComboBox<String> cmbCourse;

    @FXML
    private ComboBox<String> cmbPaymentStatus;

    @FXML
    private ComboBox<String> cmbStudent;

    @FXML
    private TableColumn<?, ?> colCourseID;

    @FXML
    private TableColumn<?, ?> colCourseName;

    @FXML
    private TableColumn<?, ?> colDuration;

    @FXML
    private TableColumn<?, ?> colFee;

    @FXML
    private TableColumn<?, ?> colStudentID;

    @FXML
    private TableView<?> tblCourses;

    @FXML
    private TextField txtAmountPaid;

    @FXML
    private TextField txtCourseName;

    @FXML
    private TextField txtDuration;

    @FXML
    private TextField txtFee;

    @FXML
    private TextField txtStudentEmail;

    @FXML
    private TextField txtStudentName;

    @FXML
    private TextField txtStudentPhone;

    public void initialize() {
        cmbPaymentStatus.getItems().addAll("cash", "card");
        setStudentCmb();
        setCourseCmb();
    }

    private void setCourseCmb() {
        ObservableList<String> obList = FXCollections.observableArrayList();
        List<ProgramDto> dtos = programBo.getAllProgram();
        for (ProgramDto dto : dtos) {
            obList.add(dto.getName());
        }
        cmbCourse.getItems().addAll(obList);
    }

    private void setStudentCmb() {
        ObservableList<String> obList = FXCollections.observableArrayList();
        List<StudentDto> dtos = studentBo.getAllStudent();
        for (StudentDto dto : dtos) {
            obList.add(dto.getId());
        }
        cmbStudent.getItems().addAll(obList);
    }

    @FXML
    void DeleteOnAction(ActionEvent event) {

    }

    @FXML
    void SearchOnAction(ActionEvent event) {

    }

    @FXML
    void registerOnAction(ActionEvent event) {
        String st_id = cmbStudent.getSelectionModel().getSelectedItem();
        String cname = cmbCourse.getSelectionModel().getSelectedItem();
        ProgramDto pDto = programBo.searchProgramByname(cmbCourse.getSelectionModel().getSelectedItem());
        StudentDto sDto = studentBo.searchStudent(cmbStudent.getSelectionModel().getSelectedItem());

        String payment_status = cmbPaymentStatus.getSelectionModel().getSelectedItem();
        String amount_paid = txtAmountPaid.getText();
        String date = LocalDateTime.now().toString();

        if (st_id != null && pDto != null && payment_status != null && amount_paid != null) {

            courseRefistration.register("1",date,pDto.getProgramId(),st_id,payment_status,amount_paid);

           /* Student_programDto studentProgramDto = new Student_programDto(null, date, sDto, pDto);
            PaymentDto paymentDto = new PaymentDto(null, payment_status, date, amount_paid, studentProgramDto);

*/

        }
    }

    public void studentCmbOnAction(ActionEvent actionEvent) {
        String sid = cmbStudent.getSelectionModel().getSelectedItem();
        if (sid != null) {
            StudentDto dto = studentBo.searchStudent(sid);
            txtStudentName.setText(dto.getName());
            txtStudentEmail.setText(dto.getEmail());
            txtStudentPhone.setText(dto.getTel());
        }
    }

    public void courseCmbOnAction(ActionEvent actionEvent) {
        String cname = cmbCourse.getSelectionModel().getSelectedItem();
        if (cname != null) {
            ProgramDto dto = programBo.searchProgramByname(cname);
            txtCourseName.setText(dto.getName());
            txtFee.setText(String.valueOf(dto.getFees()));
            txtDuration.setText(String.valueOf(dto.getDuration()));
        }
    }
}
