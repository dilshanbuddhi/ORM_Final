package org.example.contraller;

import javafx.beans.Observable;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import org.example.bo.BoFactory;
import org.example.bo.custom.Course_Refistration;
import org.example.bo.custom.StudentBo;
import org.example.dto.StudentDto;

import java.util.List;

public class PaymentController {
    public TextField txtPaymentID;
    public TextField txtAmount;
    public TextField txtDate;
    public TextField txtMethod;
    public Button btnPay;
    public Button btnRefill;
    public Button btnClear;
    public TextField txtPaymentHistory;
    public Button backbtn;
    public ComboBox<String> cmbStudent;
    public ComboBox<String> cmbProgram;
    public TextField refillAmountTxt;

    StudentBo studentBo = (StudentBo) BoFactory.getBoFactory().getBO(BoFactory.BOTypes.STUDENT);
    Course_Refistration courseRefistration = ( Course_Refistration) BoFactory.getBoFactory().getBO(BoFactory.BOTypes.COURSE);
    public void initialize() {
        setStudentCmb();
    }

    private void setStudentCmb() {
        ObservableList<String> obList = FXCollections.observableArrayList();
        List<StudentDto> dtos = studentBo.getAllStudent();
        for (StudentDto dto : dtos) {
            obList.add(dto.getId());
        }
        cmbStudent.getItems().addAll(obList);
    }

    public void payAction(ActionEvent actionEvent) {
    }

    public void refillAction(ActionEvent actionEvent) {
    }

    public void clearAction(ActionEvent actionEvent) {
    }

    public void backOnAction(ActionEvent actionEvent) {
    }

    public void studentCmbOnAction(ActionEvent actionEvent) {
        String id = cmbStudent.getSelectionModel().getSelectedItem();
        setProgramCmb(id);
    }

    private void setProgramCmb(String id) {
        ObservableList<String> obList = FXCollections.observableArrayList();
        List<String> dtos = courseRefistration.getAllProgrambyId(id);
        for (String dto : dtos) {
            obList.add(dto);
        }
        cmbProgram.getItems().addAll(obList);
    }

    public void programCmbOnAction(ActionEvent actionEvent) {
    }
}
