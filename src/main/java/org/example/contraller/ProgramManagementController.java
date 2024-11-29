package org.example.contraller;

import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import org.example.bo.BoFactory;
import org.example.bo.custom.ProgramBo;
import org.example.dto.ProgramDto;
import org.example.dto.UserDto;
import org.example.entity.Programme;

import java.awt.event.MouseEvent;
import java.io.IOException;

public class ProgramManagementController {

    ProgramBo programBo = (ProgramBo) BoFactory.getBoFactory().getBO(BoFactory.BOTypes.PROGRAM);

    @FXML
    private Button btnAddProgram;

    @FXML
    private Button btnDeleteProgram;

    @FXML
    private Button btnSearchProgram;

    @FXML
    private Button btnUpdateProgram;

    @FXML
    private TableColumn<?, ?> colDuration;

    @FXML
    private TableColumn<?, ?> colFee;

    @FXML
    private TableColumn<?, ?> colProgramID;

    @FXML
    private TableColumn<?, ?> colProgramName;

    @FXML
    private TableView<ProgramDto> tblPrograms;

    @FXML
    private TextField txtDuration;

    @FXML
    private TextField txtFee;

    @FXML
    private TextField txtProgramID;

    @FXML
    private TextField txtProgramName;


    public void initialize() {
        setcellvaluefactory();
        getallProgram();
        UserDto userDto = LoginFormContraller.getLiveUserRole();
        checkRoll(userDto);
    }

    private void checkRoll(UserDto userDto) {
        if (userDto.getRole().equals("User")) {
            btnAddProgram.setVisible(false);
            btnDeleteProgram.setVisible(false);
            btnUpdateProgram.setVisible(false);
        }
    }

    private void setcellvaluefactory() {
        colProgramID.setCellValueFactory(new PropertyValueFactory<>("programId"));
        colProgramName.setCellValueFactory(new PropertyValueFactory<>("name"));
        colDuration.setCellValueFactory(new PropertyValueFactory<>("duration"));
        colFee.setCellValueFactory(new PropertyValueFactory<>("fees"));

    }

    private void getallProgram() {
        ObservableList<ProgramDto> programmes = programBo.getAllProgram();


        tblPrograms.setItems(programmes);
    }



    @FXML
    void addProgram(ActionEvent event) {
        String programId = txtProgramID.getText();
        String programName = txtProgramName.getText();
        String duration = txtDuration.getText();
        double fee = Double.parseDouble(txtFee.getText());

        if (isValid()) {
            new Alert(Alert.AlertType.ERROR, "Please fill all the fields").show();
        }else {
            Programme programme = new Programme(programId, programName, duration, fee);
            boolean result = programBo.addProgram(programme);
            if (result) {
                getallProgram();
                new Alert(Alert.AlertType.CONFIRMATION, "Add Successful").show();
            }
        }

    }


    @FXML
    void deleteProgram(ActionEvent event) {
        String programId = txtProgramID.getText();
        if (programId.isEmpty()) {
            new Alert(Alert.AlertType.ERROR, "Please fill all the fields").show();
        }else{
            boolean result = programBo.deleteProgram(programId);
            getallProgram();
            if (result) {
                getallProgram();
                new Alert(Alert.AlertType.CONFIRMATION, "Delete Successful").show();
            }
        }
    }

    @FXML
    void searchProgram(ActionEvent event) {
        String programId = txtProgramID.getText();
        if (programId.isEmpty()) {
            new Alert(Alert.AlertType.ERROR, "Please fill all the fields").show();
        }   else{
            ProgramDto programDto = programBo.searchProgram(programId);
            if (programDto != null) {
                txtProgramID.setText(programDto.getProgramId());
                txtProgramName.setText(programDto.getName());
                txtDuration.setText(programDto.getDuration());
                txtFee.setText(String.valueOf(programDto.getFees()));
            }else{
                new Alert(Alert.AlertType.ERROR, "Search Failed").show();
            }
        }
    }

    public void clear() {
        txtProgramID.clear();
        txtProgramName.clear();
        txtDuration.clear();
        txtFee.clear();

    }


    @FXML
    void updateProgram(ActionEvent event) {
        String id = txtProgramID.getText();
        if (id.isEmpty()) {
            new Alert(Alert.AlertType.ERROR, "Please fill all the fields").show();
        }else{
            String programName = txtProgramName.getText();
            String duration = txtDuration.getText();
            double fee = Double.parseDouble(txtFee.getText());
            ProgramDto programDto = new ProgramDto(id, programName, duration, fee);
            boolean result = programBo.updateProgram(programDto);
            getallProgram();
            if (result) {
                getallProgram();
                new Alert(Alert.AlertType.CONFIRMATION, "Update Successful").show();
            }
        }

    }

    public void onClickedAction(javafx.scene.input.MouseEvent mouseEvent) {
        TableView<ProgramDto> tableView = (TableView<ProgramDto>) mouseEvent.getSource();
        ProgramDto selectedData = tableView.getSelectionModel().getSelectedItem();

        if (selectedData != null) {
            String programId = selectedData.getProgramId();
            String programName = selectedData.getName();
            String duration = selectedData.getDuration();
            double fee = selectedData.getFees();

            txtProgramID.setText(programId);
            txtProgramName.setText(programName);
            txtDuration.setText(duration);
            txtFee.setText(String.valueOf(fee));
        }

    }

    public void backOnAction(ActionEvent actionEvent) throws IOException {
        AnchorPane rootNode = FXMLLoader.load(getClass().getResource("/view/dashboard.fxml"));

        Scene scene = new Scene(rootNode);

        Stage stage = (Stage) txtProgramID.getScene().getWindow();
        stage.setScene(scene);
        stage.centerOnScreen();
        stage.setTitle("Login Page");
    }

    public boolean isValid() {
        String id = txtProgramID.getText();
        String name = txtProgramName.getText();
        String duration = txtDuration.getText();
        double fee = Double.parseDouble(txtFee.getText());

        if (id.matches("^[a-z]{3,}[0-9]{3,}$") && name.matches("[a-zA-Z ]{3,}") && duration.matches("^\\d+\\s+(year|month)$") && fee > 0) {
            return true;
        } else {
            return false;
        }
    }


    public void idOnKeyReleased(KeyEvent keyEvent) {
        if (txtProgramID.getText().matches("^[a-z]{3,}[0-9]{3,}$")) {
            txtProgramID.setStyle("-fx-border-color: green; -fx-border-width: 2px; -fx-border-radius: 5px;");
        }else {
            txtProgramID.setStyle("-fx-border-color: red; -fx-border-width: 2px; -fx-border-radius: 5px;");
        }
    }

    public void nameOnKeyReleased(KeyEvent keyEvent) {
        if (txtProgramName.getText().matches("[a-zA-Z ]{4,}")) {
            txtProgramName.setStyle("-fx-border-color: green; -fx-border-width: 2px; -fx-border-radius: 5px;");
        }else {
            txtProgramName.setStyle("-fx-border-color: red; -fx-border-width: 2px; -fx-border-radius: 5px;");
        }
    }

    public void durationOnKeyReleased(KeyEvent keyEvent) {
        if (txtDuration.getText().matches("^\\d+\\s+(year|month)$")) {
            txtDuration.setStyle("-fx-border-color: green; -fx-border-width: 2px; -fx-border-radius: 5px;");
        }else {
            txtDuration.setStyle("-fx-border-color: red; -fx-border-width: 2px; -fx-border-radius: 5px;");
        }
    }


    public void feeOnKeyReleased(KeyEvent keyEvent) {
        if (txtFee.getText().matches("\\d+")) {
            txtFee.setStyle("-fx-border-color: green; -fx-border-width: 2px; -fx-border-radius: 5px;");
        }else {
            txtFee.setStyle("-fx-border-color: red; -fx-border-width: 2px; -fx-border-radius: 5px;");
        }
    }
}
