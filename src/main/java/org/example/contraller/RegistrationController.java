package org.example.contraller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import org.example.bo.BoFactory;
import org.example.bo.custom.UserBo;
import org.example.dto.UserDto;
import org.springframework.security.crypto.bcrypt.BCrypt;


import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.List;

public class RegistrationController {
    UserBo userBo = (UserBo) BoFactory.getBoFactory().getBO(BoFactory.BOTypes.USER);
    @FXML
    private Button backbtn;

    @FXML
    private Button btnRegister;

    @FXML
    private TextField txtEmail;

    @FXML
    private PasswordField txtPassword;

    @FXML
    private TextField txtUsername;

    @FXML
    void backOnAction(ActionEvent event) throws IOException {
        AnchorPane rootNode = FXMLLoader.load(getClass().getResource("/view/LoginForm.fxml"));

        Scene scene = new Scene(rootNode);

        Stage stage = (Stage) btnRegister.getScene().getWindow();
        stage.setScene(scene);
        stage.centerOnScreen();
        stage.setTitle("Login Page");
    }

    @FXML
    void registerOnAction(ActionEvent event) throws IOException {
        String username = txtUsername.getText();
        String email = txtEmail.getText();
        String password = txtPassword.getText();

        if (username.isEmpty() || email.isEmpty() || password.isEmpty() ) {
            new Alert(Alert.AlertType.ERROR, "Please fill all the fields").show();
        } else {


            String encryptedPassword = null;

            try {
                encryptedPassword = BCrypt.hashpw(password, BCrypt.gensalt());
            } catch (Exception e) {
                e.printStackTrace();
                new Alert(Alert.AlertType.ERROR, "Error while encrypting password").show();
                return;
            }

            UserDto admin = new UserDto(username, email, encryptedPassword, "admin");

            boolean result = userBo.registerAdmin(admin);

            if (result) {
                new Alert(Alert.AlertType.CONFIRMATION, "Registration successful").show();
                backOnAction(event);
            } else {
                new Alert(Alert.AlertType.ERROR, "Registration failed").show();
            }
        }
    }

/*    public boolean isDuplicate(String username) {
        List<UserDto> users = userBo.getAllUsers();
        for (UserDto user : users) {
            if (user.getUsername().equals(username)) {

                return true;
            }
        }
        return false;
    }*/

    private String hashPassword(String password) throws NoSuchAlgorithmException {
        MessageDigest digest = MessageDigest.getInstance("SHA-256");
        byte[] encodedHash = digest.digest(password.getBytes(StandardCharsets.UTF_8));
        StringBuilder hexString = new StringBuilder();
        for (byte b : encodedHash) {
            String hex = Integer.toHexString(0xff & b);
            if (hex.length() == 1) {
                hexString.append('0');
            }
            hexString.append(hex);
        }
        return hexString.toString();
    }


}

