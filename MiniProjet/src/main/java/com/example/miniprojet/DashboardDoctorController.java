package com.example.miniprojet;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.collections.transformation.SortedList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;

import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.Optional;
import java.util.ResourceBundle;

public class DashboardDoctorController implements Initializable {

    @FXML
    private Button AddBtn;

    @FXML
    private TextField AdressePatient;

    @FXML
    private AnchorPane CrudNurse;

    @FXML
    private DatePicker DateVisite;

    @FXML
    private Button HomeBtn;

    @FXML
    private AnchorPane HomeForm;

    @FXML
    private Button LogoutBtn;

    @FXML
    private TableColumn<?, ?> MobileCol;

    @FXML
    private Label TotalDoctor;

    @FXML
    private TableColumn<?, ?> adresseCol;

    @FXML
    private Button clear;

    @FXML
    private TextField codePatient;

    @FXML
    private TableColumn<PatientData, String >codePatientCol;

    @FXML
    private TableColumn<PatientData, String > dateCol;

    @FXML
    private Button delete;

    @FXML
    private TextArea description;

    @FXML
    private TableColumn<PatientData, String >descriptionCol;

    @FXML
    private TextField diagnostic;

    @FXML
    private TableColumn<PatientData, String > diagnosticCol;

    @FXML
    private TableColumn<PatientData, String > fullNameCol;

    @FXML
    private TextField fullNamePatient;

    @FXML
    private Button inserer;

    @FXML
    private TextField mobilePatient;

    @FXML
    private TextField passwordPatient;

    @FXML
    private TextField searchPatient;

    @FXML
    private TableView<PatientData> tableViewPatient;

    @FXML
    private Label totalDoctor;

    @FXML
    private Label totalNurse;

    @FXML
    private Label totalSalle;

    @FXML
    private Button update;
    private PreparedStatement prepare ;
    private ResultSet result;
    private Connection connect ;
    public  void addPatient(){
        String sql ="insert into patient (idPatient,password , fullname , mobile , adresse , description , diagnostic , date ) values (?,?,?,?,?,?,?,?)";

        try {
            if (codePatient.getText().isEmpty() || passwordPatient.getText().isEmpty() || fullNamePatient.getText().isEmpty()|| mobilePatient.getText().isEmpty() || AdressePatient.getText().isEmpty() || description.getText().isEmpty()) {
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Form Error!");
                alert.setHeaderText(null);
                alert.setContentText(" please fill all blank fields ! ");
                alert.showAndWait();
            }else {



                prepare = connect.prepareStatement(sql);
                prepare.setString(1, codePatient.getText());
                prepare.setString(2, passwordPatient.getText());
                prepare.setString(3, fullNamePatient.getText());
                prepare.setString(2, mobilePatient.getText());
                prepare.setString(3, AdressePatient.getText());
                prepare.setString(2, description.getText());
                prepare.setString(3, diagnostic.getText());
                // dateeeeeeeeeeee

                prepare.executeUpdate();
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("Information !");
                alert.setHeaderText(null);
                alert.setContentText(" Successfully Added ! ");
                alert.showAndWait();
                PatientShowListdData();
                PatientReset();

            }


        }catch(Exception e){
            e.printStackTrace();
        }

    }
    public  void UpdatePatient(){
        String sql ="update patient set password='"+passwordPatient.getText()+"' ,  fullname='"+fullNamePatient.getText() +"' ,  mobile='"+mobilePatient.getText()+"' ,  adresse='"+AdressePatient.getText()+"' ,  diagnostic='"+diagnostic.getText()+"' ,  description='"+description.getText()+" ' where code ='"+codePatient.getText()+"'";
        connect=DataBase.connectDB();

        try {
            if (codePatient.getText().isEmpty() || passwordPatient.getText().isEmpty() || fullNamePatient.getText().isEmpty()|| mobilePatient.getText().isEmpty() || AdressePatient.getText().isEmpty() || description.getText().isEmpty()){
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Form Error!");
                alert.setHeaderText(null);
                alert.setContentText(" please fill all blank fields ! ");
                alert.showAndWait();
            }else {

                Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
                alert.setTitle("Confirmation");
                alert.setHeaderText(null);
                alert.setContentText(" are you sure you want update patient  :'"+fullNamePatient.getText());

                Optional<ButtonType> option =alert.showAndWait();

                if(option.get().equals(ButtonType.OK)){
                    Statement statement = connect.createStatement();
                    statement.executeUpdate(sql);
                    alert = new Alert(Alert.AlertType.INFORMATION);
                    alert.setTitle("Information!");
                    alert.setHeaderText(null);
                    alert.setContentText(" Updated successfully ");
                    alert.showAndWait();
                    PatientShowListdData();
                    PatientReset();
                }

            }





        }catch (Exception e){
            e.printStackTrace();

        }

    }

    public  void DeletePatient(){
        String sql ="delete from patient where code ='"+codePatient.getText()+"'";
        connect =DataBase.connectDB();
        try{


            if (codePatient.getText().isEmpty() || passwordPatient.getText().isEmpty() || fullNamePatient.getText().isEmpty()|| mobilePatient.getText().isEmpty() || AdressePatient.getText().isEmpty() || description.getText().isEmpty()){
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Form Error!");
                alert.setHeaderText(null);
                alert.setContentText(" please fill all blank fields ! ");
                alert.showAndWait();
            }else {

                Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
                alert.setTitle("Confirmation");
                alert.setHeaderText(null);
                alert.setContentText(" are you sure you want delete patient :' "+fullNamePatient.getText());

                Optional<ButtonType> option =alert.showAndWait();

                if(option.get().equals(ButtonType.OK)){
                    Statement statement = connect.createStatement();
                    statement.executeUpdate(sql);
                    alert = new Alert(Alert.AlertType.INFORMATION);
                    alert.setTitle("Information!");
                    alert.setHeaderText(null);
                    alert.setContentText(" delete successfully ");
                    alert.showAndWait();
                    PatientShowListdData();
                    PatientReset();
                }

            }


        }catch (Exception e){
            e.printStackTrace();
        }
    }


    public  void SearchPatient(){

        FilteredList<PatientData> filter = new FilteredList<>(addPatientList, e-> true);

        Search.textProperty().addListener((Observable , oldValue , newValue)->{
            filter.setPredicate(predicatePatientData -> {
                if (newValue== null ||newValue.isEmpty()){
                    return true;
                }
                String searchKey = newValue.toLowerCase();
                if (predicatePatientData.getFullName().toLowerCase().contains(searchKey)){
                    return true ;

                }else if (predicatePatientData.getMobile().toLowerCase().contains(searchKey)){
                    return true;

                }else if(predicatePatientData.getAdresse().toLowerCase().contains(searchKey)){
                    return true;
                }else return false;
            });

        });
        SortedList<PatientData> sortList = new SortedList<>(filter);
        sortList.comparatorProperty().bind(tableViewPatient.comparatorProperty());

        tableViewPatient.setItems(sortList);
    }
    public void PatientReset(){
        codePatient.setText("");
        fullNamePatient.setText("");
        mobilePatient.setText("");
        diagnostic.setText("");
        description.setText("");
        AdressePatient.setText("");
        passwordPatient.setText("");



    }
    public ObservableList<PatientData> PatientListData(){
        String sql="select * from patient";
        ObservableList<PatientData> listService = FXCollections.observableArrayList();
        connect=DataBase.connectDB();
        try {
            prepare =connect.prepareStatement(sql);
            result=prepare.executeQuery();


            PatientData service ;

            while (result.next()){
                service = new PatientData(result.getInt("IdPatient"),result.getString("password"),result.getString("fullname") ,result.getString("description"),result.getString("diagnostic") ,result.getString("adresse"),result.getString("mobile") ,result.getString("date") );
                listService.add(service);

            }
        }catch (Exception e){
            e.printStackTrace();
        }
        return listService ;
    }
    private ObservableList<PatientData> PatientList ;
    public  void PatientShowListdData(){
        PatientList = PatientListData();
        codePatientCol.setCellValueFactory(new PropertyValueFactory<>("code"));
        fullNameCol.setCellValueFactory(new PropertyValueFactory<>("name"));
        adresseCol.setCellValueFactory(new PropertyValueFactory<>("Bloc"));
        MobileCol.setCellValueFactory(new PropertyValueFactory<>("name"));
        diagnosticCol.setCellValueFactory(new PropertyValueFactory<>("Bloc"));
        //description.setCellValueFactory(new PropertyValueFactory<>("name"));
        dateCol.setCellValueFactory(new PropertyValueFactory<>("Bloc"));

        tableViewPatient.setItems(PatientList);
    }

    public void addServiceSelect(){
        PatientData serDate = tableViewPatient.getSelectionModel().getSelectedItem();
        int num = tableViewPatient.getSelectionModel().getSelectedIndex();
        if ((num-1)< -1)
        {return;}
        codePatient.setText(String.valueOf(serDate.getPatientId()));
        fullNamePatient.setText(String.valueOf(serDate.getFullName()));
        AdressePatient.setText(String.valueOf(serDate.getAdresse()));
        mobilePatient.setText(String.valueOf(serDate.getMobile()));
        description.setText(String.valueOf(serDate.getDescription()));
        diagnostic.setText(String.valueOf(serDate.getDiagnostic()));
        //AdressePatient.setText(String.valueOf(serDate.getAdresse()));
    }
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        PatientShowListdData();

    }
}
