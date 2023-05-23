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
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.ResourceBundle;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class DoctorController implements Initializable {
    @FXML
    private TextField loginUsername;

    @FXML
    private Button LoginButton;

    @FXML
    private Hyperlink LoginRegister;

    @FXML
    private ComboBox<String> LoginSelect;

    @FXML
    private AnchorPane loginForm;

    @FXML
    private PasswordField loginPass;

    @FXML
    private AnchorPane mainForm;

    @FXML
    private Button registerButton;

    @FXML
    private TextField registerEmail;

    @FXML
    private AnchorPane registerForm;

    @FXML
    private TextField registerFullName;

    @FXML
    private Hyperlink registerLogin;

    @FXML
    private PasswordField registerPass;

    @FXML
    private TextField registerUserName;
    private PreparedStatement prepare ;
    private ResultSet result;
    private Connection connect ;
 private double x=0;
 private  double y=0;

    public void loginAccount() {
        if (loginPass.getText().isEmpty() || loginUsername.getText().isEmpty()) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Form Error!");
            alert.setHeaderText(null);
            alert.setContentText("Incorrecte UserName /Password ");
            alert.showAndWait();

        } else {
            String sql = "select * from Doctor where username= ? And password = ?";
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
                    Parent root = FXMLLoader.load(getClass().getResource("dashboardDoctor.fxml"));
                    Stage stage = new Stage();
                    Scene scene = new Scene(root);
                    stage.initStyle(StageStyle.TRANSPARENT);
                    stage.setTitle("hospital management system");
                    root.setOnMousePressed((MouseEvent event)->{
                        x=event.getScreenX();
                        y=event.getScreenY();

                    });

                    root.setOnMouseDragged((MouseEvent event )->{
                        stage.setX(event.getScreenX()-x);
                        stage.setY(event.getScreenY()-y);
                        stage.setOpacity(.8);
                    });

                    root.setOnMouseReleased((MouseEvent event )->{
                        stage.setOpacity(1);
                    });
                    stage.initStyle(StageStyle.TRANSPARENT);
                    stage.setScene(scene);
                    stage.show();

                }else {
                    Alert alert = new Alert(Alert.AlertType.ERROR);
                    alert.setTitle("Form Error!");
                    alert.setHeaderText(null);
                    alert.setContentText("Incorrect username / password");
                    alert.showAndWait();
                }
            }catch (Exception e){
                e.printStackTrace();
            }
        }
    }


    public static boolean isEmailAdress(String email){
        Pattern p = Pattern.compile("^[A-Z0-9._%+-]+@[A-Z0-9.-]+\\.[A-Z]{2,4}$" );
        Matcher m = p.matcher(email.toUpperCase());
        return m.matches();
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
        if (LoginSelect.getSelectionModel().getSelectedItem().equals("Admin")) {
            try {
                Parent root = FXMLLoader.load(getClass().getResource("hello-view.fxml"));
                Stage stage = new Stage();
                stage.setMinWidth(340);
                stage.setMaxWidth(560);
                stage.setTitle("hospital management system");
                stage.setScene(new Scene(root));
                stage.show();
            } catch (Exception e) {
                e.printStackTrace();
            }
        } else if
        (LoginSelect.getSelectionModel().getSelectedItem().equals("Doctor")) {
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
    public void initialize(URL url, ResourceBundle resourceBundle)  {

        userList();
    }
}
