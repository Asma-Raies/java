package com.example.miniprojet;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

public class PatienController implements Initializable {
    @FXML
    private Button LoginButton;

    @FXML
    private Hyperlink LoginRegister;

    @FXML
    private ComboBox<?> LoginSelect;

    @FXML
    private AnchorPane loginForm;

    @FXML
    private PasswordField loginPass;

    @FXML
    private TextField loginUsername;

    @FXML
    private AnchorPane mainForm;
    private PreparedStatement prepare ;
    private ResultSet result;
    private Connection connect ;
    @FXML
    public void loginAccount() {
        if (loginPass.getText().isEmpty() || loginUsername.getText().isEmpty()) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Form Error!");
            alert.setHeaderText(null);
            alert.setContentText("Incorrecte UserName /Password ");
            alert.showAndWait();

        } else {
            String sql = "select * from patient where patientId= ? And password = ? ";
            Connection connect = DataBase.connectDB();
            try {



                    prepare=connect.prepareStatement(sql);

                    prepare.setString(1, loginUsername.getText());
                    prepare.setString(2, loginPass.getText());
                    result = prepare.executeQuery();

                    if(result.next()){
                        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
                        alert.setTitle("Form Error!");
                        alert.setHeaderText(null);
                        alert.setContentText("login successfully");
                        alert.showAndWait();
                    }else {
                        Alert alert = new Alert(Alert.AlertType.ERROR);
                        alert.setTitle("Form Error!");
                        alert.setHeaderText(null);
                        alert.setContentText("Incorrect Patient_id / password");
                        alert.showAndWait();
                    }

            }catch (Exception e){
                e.printStackTrace();
            }
        }
    }






    public  void userList(){
        List<String> listeU = new ArrayList<>();

        for (String  data   : Users.user){
            listeU.add(data);

        }
        ObservableList listData = FXCollections.observableList(listeU);
        LoginSelect.setItems(listData);
    }

    public void switchPage() {
        if (LoginSelect.getSelectionModel().getSelectedItem() == "Admin") {
            try {
                Parent root = FXMLLoader.load(getClass().getResource("hello-view.fxml"));
                Stage stage = new Stage();
                stage.setMinWidth(340);
                stage.setMaxWidth(560);
                stage.setTitle("hospiatl management system");
                stage.setScene(new Scene(root));
                stage.show();
            } catch (Exception e) {
                e.printStackTrace();
            }
        } else if
        (LoginSelect.getSelectionModel().getSelectedItem() == "Doctor") {
            try {
                Parent root = FXMLLoader.load(getClass().getResource("Doctor.fxml"));
                Stage stage = new Stage();
                stage.setMinWidth(340);
                stage.setMaxWidth(560);
                stage.setTitle("hospiatl management system");
                stage.setScene(new Scene(root));
                stage.show();
            } catch (Exception e) {
                e.printStackTrace();
            }
        } else if (LoginSelect.getSelectionModel().getSelectedItem() == "Patient") {
            try {
                Parent root = FXMLLoader.load(getClass().getResource("Patient.fxml"));
                Stage stage = new Stage();
                stage.setMinWidth(340);
                stage.setMaxWidth(560);
                stage.setTitle("hospiatl management system");
                stage.setScene(new Scene(root));
                stage.show();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        LoginSelect.getScene().getWindow().hide();
    }


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        userList();

    }
}
