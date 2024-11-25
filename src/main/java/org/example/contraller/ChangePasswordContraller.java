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

public class ChangePasswordContraller {

    UserBo userBo = (UserBo) BoFactory.getBoFactory().getBO(BoFactory.BOTypes.USER);

    @FXML
    private Button backbtn;

    @FXML
    private Button btnClear;

    @FXML
    private Button btnSubmit;

    @FXML
    private PasswordField txtConfirmPassword;

    @FXML
    private PasswordField txtNewPassword;

    @FXML
    private TextField txtUsername;

    @FXML
    void backOnAction(ActionEvent event) throws IOException {
        AnchorPane rootNode = FXMLLoader.load(getClass().getResource("/view/dashboard.fxml"));

        Scene scene = new Scene(rootNode);

        Stage stage = (Stage) txtNewPassword.getScene().getWindow();
        stage.setScene(scene);
        stage.centerOnScreen();
        stage.setTitle("Registration Page");

    }

    @FXML
    void clearFields(ActionEvent event) {
        txtNewPassword.clear();
        txtConfirmPassword.clear();

    }

    @FXML
    void submitPasswordChange(ActionEvent event) {
        String username = txtUsername.getText();
        if (!username.isEmpty()) {
            UserDto userDto = userBo.getdata(username);
            if (userDto != null) {
                if (txtNewPassword.getText().equals(txtConfirmPassword.getText())) {
                    String newPassword = txtNewPassword.getText();
                    String encryptedPassword = null;
                    try {
                        encryptedPassword = BCrypt.hashpw(newPassword, BCrypt.gensalt());
                    } catch (Exception e) {
                        e.printStackTrace();
                        new Alert(Alert.AlertType.ERROR, "Error while encrypting password").show();
                        return;
                    }
                    userDto.setPassword(encryptedPassword);
                    boolean isUpdated = userBo.updateUser(userDto);
                    if (isUpdated) {
                        new Alert(Alert.AlertType.CONFIRMATION, "Password Changed").show();
                        clearFields(event);
                    } else {
                        new Alert(Alert.AlertType.ERROR, "Password Not Changed").show();
                    }
                }else {
                    new Alert(Alert.AlertType.ERROR, "Passwords do not match").show();
                    txtNewPassword.setStyle("-fx-border-color: red; -fx-border-width: 2px;");
                }
            }else {
                new Alert(Alert.AlertType.ERROR, "User Not Found").show();
                txtUsername.setStyle("-fx-border-color: red; -fx-border-width: 2px;");
            }

        }

    }
}
