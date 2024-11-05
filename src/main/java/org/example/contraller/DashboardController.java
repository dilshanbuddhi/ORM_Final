package org.example.contraller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

import java.io.IOException;

public class DashboardController {

    @FXML
    private Button btnCourseManagement;

    @FXML
    private Button btnRegistration;

    @FXML
    private Button btnStudentManagement;

    @FXML
    private Button btnUserManagement;

    @FXML
    void openCourseManagement(ActionEvent event) {

    }

    @FXML
    void openRegistration(ActionEvent event) {

    }

    @FXML
    void openStudentManagement(ActionEvent event) throws IOException {
        AnchorPane rootNode = FXMLLoader.load(getClass().getResource("/view/StudentManagement.fxml"));

        Scene scene = new Scene(rootNode);

        Stage stage = (Stage) btnStudentManagement.getScene().getWindow();
        stage.setScene(scene);
        stage.centerOnScreen();
        stage.setTitle("Registration Page");
    }

    @FXML
    void openUserManagement(ActionEvent event) {

    }

}
