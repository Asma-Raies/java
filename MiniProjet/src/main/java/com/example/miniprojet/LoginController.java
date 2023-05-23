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

public class LoginController implements Initializable {
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
    private CheckBox loginShowPass;

    @FXML
    private TextField loginUserName;

    @FXML
    private Button registerButton;

    @FXML
    private TextField registerEmail;

    @FXML
    private AnchorPane registerForm;

    @FXML
    private Hyperlink registerLogin;

    @FXML
    private PasswordField registerPass;

    @FXML
    private CheckBox registerSelect;

    @FXML
    private TextField registerUserName;

    private PreparedStatement prepare ;
    private  ResultSet result;
    private Connection connect ;

private double x=0 ;
private  double y=0 ;


    public void loginAccount() {
        if (loginPass.getText().isEmpty() || loginUserName.getText().isEmpty()) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Form Error!");
            alert.setHeaderText(null);
            alert.setContentText("Incorrecte UserName /Password ");
            alert.showAndWait();

        } else {
            String sql = "select * from admin where username= ? And password = ?";
            Connection connect = DataBase.connectDB();
            try {
                prepare=connect.prepareStatement(sql);

                prepare.setString(1, loginUserName.getText());
                prepare.setString(2, loginPass.getText());
                result = prepare.executeQuery();

                if(result.next()){
                    Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
                    alert.setTitle("Form Error!");
                    alert.setHeaderText(null);
                    alert.setContentText("login successfully");
                    alert.showAndWait();

                    Parent root = FXMLLoader.load(getClass().getResource("dashboard.fxml"));

                    Stage stage = new Stage();
                    stage.setTitle("Student Main Form");
                    stage.setScene(new Scene(root));

                    stage.show();

                    // TO HIDE YOUR STUDENT LOGIN FORM
                    LoginButton.getScene().getWindow().hide();

                }else
                {
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
    public void registerAccount() {
        if (registerEmail.getText().isEmpty() || registerPass.getText().isEmpty() || registerUserName.getText().isEmpty()|| !isEmailAdress(registerEmail.getText())) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Form Error!");
            alert.setHeaderText(null);
            alert.setContentText("Please fill all blank fields");
            alert.showAndWait();

        }else {


            String checkUsername = "select * from admin where username= '"+registerUserName.getText()+"'";
            Connection connect = DataBase.connectDB();
            try {
                prepare=connect.prepareStatement(checkUsername);
                result = prepare.executeQuery();

                if (result.next()){
                    Alert alert = new Alert(Alert.AlertType.ERROR);
                    alert.setTitle("Form Error!");
                    alert.setHeaderText(null);
                    alert.setContentText(registerUserName.getText()+" is already exist");
                    alert.showAndWait();
                }else
                {
                    String insertData = "insert into admin (email , username , password , date) values (? , ? , ?,?)";
                    Date date = new Date();
                    java.sql.Date sqlDate = new java.sql.Date(date.getTime());
                    prepare = connect.prepareStatement(insertData);
                    prepare.setString(1, registerEmail.getText());
                    prepare.setString(2, registerUserName.getText());
                    prepare.setString(3, registerPass.getText());
                    prepare.setString(4, String.valueOf(sqlDate));
                    prepare.executeUpdate();
                    Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
                    alert.setTitle("confirmation !");
                    alert.setHeaderText(null);
                    alert.setContentText("registered successfully ! ");
                    alert.showAndWait();
                   // registerClear();

                    loginForm.setVisible(true);
                    registerForm.setVisible(false);
                }
            }catch (Exception e){
                e.printStackTrace();
            }
        }


    }
    public void registerClear(){
        registerEmail.clear();
        registerUserName.clear();
        registerUserName.clear();

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
    public void switchForm(ActionEvent event){
        if (event.getSource()== LoginRegister){
             loginForm.setVisible(false);
             registerForm.setVisible(true);

        }else if (event.getSource()==registerLogin){
            loginForm.setVisible(true);
            registerForm.setVisible(false);

        }
    }
    public void switchPage(){
        if (LoginSelect.getSelectionModel().getSelectedItem()=="Admin") {
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
                (LoginSelect.getSelectionModel().getSelectedItem()=="Doctor") {
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
                }

        else if (LoginSelect.getSelectionModel().getSelectedItem()=="Patient"){
                try {
                    Parent root = FXMLLoader.load(getClass().getResource("Patient.fxml"));
                    Stage stage = new Stage();
                    stage.setMinWidth(340);
                    stage.setMaxWidth(560);
                    stage.setTitle("hospiatl management system");
                    stage.setScene(new Scene(root));
                    stage.show();
                }catch (Exception e){
                    e.printStackTrace();
                }}
    }
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        userList();
    }
}
