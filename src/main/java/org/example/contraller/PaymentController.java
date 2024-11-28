package org.example.contraller;

import javafx.beans.Observable;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import org.example.bo.BoFactory;
import org.example.bo.custom.Course_Refistration;
import org.example.bo.custom.PaymentBo;
import org.example.bo.custom.ProgramBo;
import org.example.bo.custom.StudentBo;
import org.example.dto.PaymentDto;
import org.example.dto.ProgramDto;
import org.example.dto.StudentDto;
import org.example.dto.Student_programDto;
import org.example.entity.Payment;

import java.io.IOException;
import java.util.EventListener;
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
    ProgramBo programBo = (ProgramBo) BoFactory.getBoFactory().getBO(BoFactory.BOTypes.PROGRAM);
    PaymentBo paymentBo = ( PaymentBo) BoFactory.getBoFactory().getBO(BoFactory.BOTypes.PAYMENT);
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
        String pid = txtPaymentID.getText();
        double refillAmount = Double.parseDouble(refillAmountTxt.getText());
        boolean isrefill = paymentBo.refill(pid, refillAmount);
        if (isrefill) {
            new Alert(Alert.AlertType.CONFIRMATION, "Refill Successful").show();
        }else {
            new Alert(Alert.AlertType.ERROR, "Refill Failed").show();
        }

    }

    public void clearAction(ActionEvent actionEvent) {
        txtPaymentID.clear();
        txtAmount.clear();
        txtDate.clear();
        txtMethod.clear();
    }

    public void backOnAction(ActionEvent actionEvent) throws IOException {
        AnchorPane rootNode = FXMLLoader.load(getClass().getResource("/view/register_course.fxml"));

        Scene scene = new Scene(rootNode);

        Stage stage = (Stage) txtPaymentID.getScene().getWindow();
        stage.setScene(scene);
        stage.centerOnScreen();
        stage.setTitle("Login Page");
    }

    public void studentCmbOnAction(ActionEvent actionEvent) {
        String id = cmbStudent.getSelectionModel().getSelectedItem();
        setProgramCmb(id);
    }

    private void setProgramCmb(String id) {
        ObservableList<String> obList = FXCollections.observableArrayList();
        List<String> dtos = courseRefistration.getAllProgrambyId(id);
        System.out.println(dtos);
        for (String dto : dtos) {
            obList.add(dto);
        }
        cmbProgram.setItems(obList);

    }

    public void programCmbOnAction(ActionEvent actionEvent) {
        String sid = cmbStudent.getSelectionModel().getSelectedItem();
        String pname = cmbProgram.getSelectionModel().getSelectedItem();

        String pid = programBo.getID(pname);

        String spid = courseRefistration.getspid(sid, pid);
        System.out.println(spid);

        setTextfield(spid);
    }

    private void setTextfield(String spid) {
        PaymentDto dto = paymentBo.getpaymentdetail(spid);
        txtPaymentID.setText(String.valueOf(dto.getPid()));
        txtAmount.setText(String.valueOf(dto.getRemainPayment()));
        txtDate.setText(dto.getPaymentDate());
        txtMethod.setText(dto.getPaymentMethod());
    }


}
