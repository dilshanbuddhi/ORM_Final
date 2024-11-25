package org.example.contraller;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import org.example.bo.BoFactory;
import org.example.bo.custom.UserBo;
import org.example.dto.UserDto;

import java.io.IOException;
import java.util.List;

public class UserManagementController {
    public ComboBox roleCmb;
    UserBo userBo = (UserBo) BoFactory.getBoFactory().getBO(BoFactory.BOTypes.USER);

    @FXML
    private Button btnAddUser;

    @FXML
    private Button btnDeleteUser;

    @FXML
    private Button btnSearchUser;

    @FXML
    private Button btnUpdateUser;

    @FXML
    private TableColumn<?, ?> colEmail;

    @FXML
    private TableColumn<?, ?> colRole;

    @FXML
    private TableColumn<?, ?> colUserID;

    @FXML
    private TableColumn<?, ?> colUsername;

    @FXML
    private TableView<UserDto> tblUsers;

    @FXML
    private TextField txtEmail;

    @FXML
    private TextField txtPassword;

    @FXML
    private TextField txtUserID;

    @FXML
    private TextField txtUsername;

    public void initialize() {
        roleCmb.getItems().addAll("Admin", "User");
        setcellvalue();
        loadAllUsers();
    }

    private void loadAllUsers() {
        ObservableList<UserDto> oblist = FXCollections.observableArrayList();

        List<UserDto> dtos = userBo.getAllUsers();

        for (UserDto dto : dtos) {
            oblist.add(dto);
        }
        tblUsers.setItems(oblist);
    }

    private void setcellvalue() {
        colUserID.setCellValueFactory(new PropertyValueFactory<>("id"));
        colUsername.setCellValueFactory(new PropertyValueFactory<>("username"));
        colEmail.setCellValueFactory(new PropertyValueFactory<>("email"));
        colRole.setCellValueFactory(new PropertyValueFactory<>("role"));
    }


    @FXML
    void addUser(ActionEvent event) {
        String id = txtUserID.getText();
        String username = txtUsername.getText();
        String email = txtEmail.getText();
        String password = txtPassword.getText();
        String role = (String) roleCmb.getValue();

        if (id.isEmpty() || username.isEmpty() || email.isEmpty() || password.isEmpty() || role.isEmpty()) {
            new Alert(Alert.AlertType.ERROR, "Please fill all the fields").show();
        } else {
            UserDto userDto = new UserDto(username, email, password, role);
            boolean issaved = userBo.save(userDto);
            if (issaved) {
                new Alert(Alert.AlertType.CONFIRMATION, "User Saved").show();

            } else {
                new Alert(Alert.AlertType.ERROR, "User Not Saved").show();
            }
        }
    }

    @FXML
    void deleteUser(ActionEvent event) {
        String id = txtUserID.getText();
        if(id.isEmpty()){
            new Alert(Alert.AlertType.ERROR, "Please select a user to delete").show();
        }else{
            boolean isDeleted = userBo.deleteUser(id);
            if (isDeleted) {
                new Alert(Alert.AlertType.CONFIRMATION, "User Deleted").show();
                loadAllUsers();
            } else {
                new Alert(Alert.AlertType.ERROR, "User Not Deleted").show();
            }

        }
    }

    @FXML
    void searchUser(ActionEvent event) {

    }

    @FXML
    void updateUser(ActionEvent event) {
        int id = Integer.parseInt(txtUserID.getText());
        String username = txtUsername.getText();
        String email = txtEmail.getText();
        String password = txtPassword.getText();
        String role = (String) roleCmb.getValue();

        if ( id == 0 || username.isEmpty() || email.isEmpty() || password.isEmpty() || role.isEmpty()) {
            new Alert(Alert.AlertType.ERROR, "Please fill all the fields").show();
        } else {
            UserDto userDto = new UserDto(id ,username, email, password, role);
            boolean isUpdated = userBo.updateUser(userDto);
            if (isUpdated) {
                new Alert(Alert.AlertType.CONFIRMATION, "User Updated").show();
                loadAllUsers();
            } else {
                new Alert(Alert.AlertType.ERROR, "User Not Updated").show();
            }
        }
    }

    public void userTblClicked(MouseEvent mouseEvent) {

        UserDto userDto = tblUsers.getSelectionModel().getSelectedItem();
        if (userDto != null) {
            txtUserID.setText(String.valueOf(userDto.getId()));
            txtUsername.setText(userDto.getUsername());
            txtEmail.setText(userDto.getEmail());
            roleCmb.setValue(userDto.getRole());
        }
    }

    public void backOnAction(ActionEvent actionEvent) throws IOException {
        AnchorPane rootNode = FXMLLoader.load(getClass().getResource("/view/dashboard.fxml"));

        Scene scene = new Scene(rootNode);

        Stage stage = (Stage) txtUserID.getScene().getWindow();
        stage.setScene(scene);
        stage.centerOnScreen();
        stage.setTitle("Login Page");
    }

    public void passwordChangeOnAction(ActionEvent actionEvent) throws IOException {
        AnchorPane rootNode = FXMLLoader.load(getClass().getResource("/view/passwordChange.fxml"));

        Scene scene = new Scene(rootNode);

        Stage stage = (Stage) txtUserID.getScene().getWindow();
        stage.setScene(scene);
        stage.centerOnScreen();
        stage.setTitle("Registration Page");

    }
}
