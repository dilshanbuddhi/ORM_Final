package org.example.contraller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import org.example.bo.BoFactory;
import org.example.bo.custom.UserBo;
import org.example.dao.DaoFactory;
import org.example.dao.custom.UserDao;
import org.example.dto.UserDto;
import org.springframework.security.crypto.bcrypt.BCrypt;

import java.io.IOException;

public class LoginFormContraller {

    UserDao userDao = (UserDao) DaoFactory.getdaoFactory().getDao(DaoFactory.DaoTypes.USER);
    UserBo userBo = (UserBo) BoFactory.getBoFactory().getBO(BoFactory.BOTypes.USER);

    static UserDto liveUserDto;
    @FXML
    private Button btnLogin;

    @FXML
    private Hyperlink registerLink;

    @FXML
    private PasswordField txtPassword;

    @FXML
    private TextField txtUsername;

    @FXML
    void loginOnAction(ActionEvent event) throws IOException {

        String username = txtUsername.getText();
        String password = txtPassword.getText();

        if (username.isEmpty() || password.isEmpty()) {
            new Alert(Alert.AlertType.ERROR, "Please fill all the fields").show();
        } else {
            UserDto userDto = userBo.getdata(username);

            if (userDto == null) {
                new Alert(Alert.AlertType.ERROR, "Invalid username").show();

            }else{
                if (BCrypt.checkpw(password, userDto.getPassword())){
                    if (userDto.getRole().equals("admin")) {
                        System.out.println("he is admin");
                        liveUserDto= userDto;
                    }else {
                        liveUserDto= userDto;
                        System.out.println("he is user");
                    }
                    getdashboard();
        }else {
                    new Alert(Alert.AlertType.ERROR,"Invalid password").show();
                }
    }}}

    void getdashboard() throws IOException {
        AnchorPane rootNode = FXMLLoader.load(getClass().getResource("/view/dashboard.fxml"));

        Scene scene = new Scene(rootNode);

        Stage stage = (Stage) registerLink.getScene().getWindow();
        stage.setScene(scene);
        stage.centerOnScreen();
        stage.setTitle("Registration Page");
    }

    @FXML
    void registrationOnAction(ActionEvent event) throws IOException {
        if (userDao.ifHaveAdmins()){
            new Alert(Alert.AlertType.ERROR, "already have admin").show();

        }else {
            AnchorPane rootNode = FXMLLoader.load(getClass().getResource("/view/RegistrationPage.fxml"));

            Scene scene = new Scene(rootNode);

            Stage stage = (Stage) registerLink.getScene().getWindow();
            stage.setScene(scene);
            stage.centerOnScreen();
            stage.setTitle("Registration Page");
        }
    }

    static UserDto getLiveUserRole() {
        return liveUserDto;
    }

}
