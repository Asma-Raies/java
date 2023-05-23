package com.example.miniprojet;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.collections.transformation.SortedList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import javafx.scene.input.MouseEvent;

import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;

import java.util.Optional;
import java.util.ResourceBundle;


public class DashboardController implements Initializable {
    @FXML
    private ComboBox<ServiceData> idService;

    @FXML
    private ComboBox<SalleData> listSalle;
    @FXML
    private ComboBox<DoctorData> ServiceDoctor;
    @FXML
    private Button AddBtn;

    @FXML
    private Button AddNurse;

    @FXML
    private TextField Bloc;

    @FXML
    private TableColumn<ServiceData, String> BlocCol;

    @FXML
    private TableColumn<ServiceData, String> CodeServiceCol;

    @FXML
    private AnchorPane CrudForm;

    @FXML
    private AnchorPane CrudNurse;

    @FXML
    private AnchorPane CrudSalle;

    @FXML
    private AnchorPane CrudService;

    @FXML
    private TableColumn<DoctorData, String>DoctorAdresse;



    @FXML
    private TableColumn<DoctorData, String> DoctorMobile;

    @FXML
    private TableColumn<DoctorData, String> DoctorName;

    @FXML
    private TableColumn<DoctorData, String> DoctorSpectialite;
    @FXML
    private TableColumn<DoctorData, String> DoctorCode;
    @FXML
    private TableColumn<DoctorData, String> DoctorService;


    @FXML
    private Button HomeBtn;

    @FXML
    private AnchorPane HomeForm;

    @FXML
    private TextField IdSalle;
    @FXML
    private TextField salary;
    @FXML
    private TableColumn<SalleData, String> IdSalleCol;

    @FXML
    private Button LogoutBtn;

    @FXML
    private TextField NameNurse;

    @FXML
    private TableColumn<ServiceData, String> NameServiceCol;

    @FXML
    private TableColumn<NurseData, String> NurseAdresse;

    @FXML
    private TableColumn<NurseData, String > NurseEmail;
    @FXML
    private TableColumn<NurseData, String > numSalle;
    @FXML
    private TableColumn<NurseData, String> NurseNameCol;

    @FXML
    private TableView<NurseData> TableViewNurse;
    @FXML
    private TableView<DoctorData> tableViewDoctor;
    @FXML
    private Button addBtn;

    @FXML
    private Button addBtnN;

    @FXML
    private Button addBtnS;

    @FXML
    private Button addBtnS1;

    @FXML
    private Button addSalle;

    @FXML
    private Button addService;

    @FXML
    private TextField adresse;

    @FXML
    private TextField adresseNurse;

    @FXML
    private Button clearBedS;

    @FXML
    private Button clearBedS1;

    @FXML
    private Button clearBtn;

    @FXML
    private Button clearBtnN;

    @FXML
    private TextField codeeService;

    @FXML
    private Button deleteBtn;

    @FXML
    private Button deleteBtnN;

    @FXML
    private Button deleteBtnS;

    @FXML
    private Button deleteBtnS1;

    @FXML
    private TextField email;

    @FXML
    private TextField emailNurse;

    @FXML
    private TextField idNurse;

    @FXML
    private TextField mobile;

    @FXML
    private TextField mobileNurse;

    @FXML
    private TextField nameService;

    @FXML
    private TextField nbBed;

    @FXML
    private TableColumn<SalleData, String> nbBedCol;
    @FXML
    private TableColumn<NurseData, String >IdNurseCol;
    @FXML
    private TableColumn<NurseData, String >nurseMobile;

    @FXML
    private TableColumn<NurseData, String> salaryNurse;

    @FXML
    private TextField search;
    @FXML
    private Label TotalDoctor ;
    @FXML
    private Label totalNurse ;
    @FXML
    private Label totalSalle ;
    @FXML
    private TextField search1;

    @FXML
    private TextField searchSalle;

    @FXML
    private TextField searchService;

    @FXML
    private TextField service;

    @FXML
    private TableColumn<SalleData, String> serviceColl;

    @FXML
    private TableView<SalleData> tableViewSalle;

    @FXML
    private TableView<ServiceData> tableViewService;

    @FXML
    private Button updateBtn;

    @FXML
    private Button updateBtnN;

    @FXML
    private Button updateBtns;

    @FXML
    private Button updateBtns1;

    @FXML
    private TextField fullNameDoctor;
    @FXML
    private TextField adresseDoctor ;
    @FXML
    private TextField mobileDoctor;
    @FXML
    private TextField codeDoctor;
    @FXML
    private TextField specialiteDoctor;
    @FXML
    private PasswordField passwordDoctor;


    private PreparedStatement prepare ;
    private ResultSet result;
    private Connection connect ;



    public  void addServiceAdd(){
        String sql ="insert into service (code , NomService , Bloc ) values (?,?,?)";

        try {
            if (codeeService.getText().isEmpty() || nameService.getText().isEmpty() || Bloc.getText().isEmpty()) {
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Form Error!");
                alert.setHeaderText(null);
                alert.setContentText(" please fill all blank fields ! ");
                alert.showAndWait();
            }else {



                prepare = connect.prepareStatement(sql);
                prepare.setString(1, codeeService.getText());
                prepare.setString(2, nameService.getText());
                prepare.setString(3, Bloc.getText());
                prepare.executeUpdate();
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("Information !");
                alert.setHeaderText(null);
                alert.setContentText(" Successfully Added ! ");
                alert.showAndWait();
                addServiceShowListdData();
                AddServiceReset();

                }


        }catch(Exception e){
                e.printStackTrace();
            }

    }
    public  void AddServiceUpdate(){
        String sql ="update service set NomService='"+nameService.getText()+"' ,  Bloc='"+Bloc.getText() +" ' where code ='"+codeeService.getText()+"'";
        connect=DataBase.connectDB();

        try {
            if (codeeService.getText().isEmpty() || nameService.getText().isEmpty() || Bloc.getText().isEmpty()) {
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Form Error!");
                alert.setHeaderText(null);
                alert.setContentText(" please fill all blank fields ! ");
                alert.showAndWait();
            }else {

               Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
                alert.setTitle("Confirmation");
                alert.setHeaderText(null);
                alert.setContentText(" are you sure you want update service :'"+nameService.getText());

                Optional<ButtonType> option =alert.showAndWait();

                   if(option.get().equals(ButtonType.OK)){
                       Statement statement = connect.createStatement();
                       statement.executeUpdate(sql);
                       alert = new Alert(Alert.AlertType.INFORMATION);
                       alert.setTitle("Information!");
                       alert.setHeaderText(null);
                       alert.setContentText(" Updated successfully ");
                       alert.showAndWait();
                       addServiceShowListdData();
                       AddServiceReset();
                   }

                 }





        }catch (Exception e){
            e.printStackTrace();

        }

    }

    public  void AddServiceDelete(){
        String sql ="delete from service where code ='"+codeeService.getText()+"'";
        connect =DataBase.connectDB();
        try{


            if (codeeService.getText().isEmpty() || nameService.getText().isEmpty() || Bloc.getText().isEmpty()) {
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Form Error!");
                alert.setHeaderText(null);
                alert.setContentText(" please fill all blank fields ! ");
                alert.showAndWait();
            }else {

                Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
                alert.setTitle("Confirmation");
                alert.setHeaderText(null);
                alert.setContentText(" are you sure you want delete service :' "+nameService.getText());

                Optional<ButtonType> option =alert.showAndWait();

                if(option.get().equals(ButtonType.OK)){
                    Statement statement = connect.createStatement();
                    statement.executeUpdate(sql);
                    alert = new Alert(Alert.AlertType.INFORMATION);
                    alert.setTitle("Information!");
                    alert.setHeaderText(null);
                    alert.setContentText(" delete successfully ");
                    alert.showAndWait();
                    addServiceShowListdData();
                    AddServiceReset();
                }

            }


        }catch (Exception e){
            e.printStackTrace();
        }
    }


    public  void AddServiceSearch(){

        FilteredList<ServiceData> filter = new FilteredList<>(addServiceList, e-> true);

        searchService.textProperty().addListener((Observable , oldValue , newValue)->{
            filter.setPredicate(predicateServiceData -> {
                if (newValue== null ||newValue.isEmpty()){
                    return true;
                }
                String searchKey = newValue.toLowerCase();
                if (predicateServiceData.getCode().toLowerCase().contains(searchKey)){
                return true ;

                }else if (predicateServiceData.getName().toLowerCase().contains(searchKey)){
                    return true;

                }else if(predicateServiceData.getBloc().toLowerCase().contains(searchKey)){
                    return true;
                }else return false;
            });

        });
        SortedList<ServiceData> sortList = new SortedList<>(filter);
        sortList.comparatorProperty().bind(tableViewService.comparatorProperty());

        tableViewService.setItems(sortList);
    }
    public void AddServiceReset(){
        codeeService.setText("");
        nameService.setText("");
        Bloc.setText("");
    }
    public ObservableList<ServiceData> addServiceListData(){
        String sql="select * from service";
      ObservableList<ServiceData> listService = FXCollections.observableArrayList();
      connect=DataBase.connectDB();
      try {
          prepare =connect.prepareStatement(sql);
          result=prepare.executeQuery();


          ServiceData service ;

          while (result.next()){
              service = new ServiceData(result.getString("code"),result.getString("NomService"),result.getString("bloc") );
              listService.add(service);

          }
      }catch (Exception e){
          e.printStackTrace();
      }
      return listService ;
    }
private ObservableList<ServiceData> addServiceList ;
  public  void addServiceShowListdData(){
      addServiceList = addServiceListData();
      CodeServiceCol.setCellValueFactory(new PropertyValueFactory<>("code"));
      NameServiceCol.setCellValueFactory(new PropertyValueFactory<>("name"));
      BlocCol.setCellValueFactory(new PropertyValueFactory<>("Bloc"));
     tableViewService.setItems(addServiceList);
  }

    public void addServiceSelect(){
      ServiceData serDate = tableViewService.getSelectionModel().getSelectedItem();
      int num = tableViewService.getSelectionModel().getSelectedIndex();
      if ((num-1)< -1)
        {return;}
       codeeService.setText(String.valueOf(serDate.getCode()));
        nameService.setText(String.valueOf(serDate.getName()));
        Bloc.setText(String.valueOf(serDate.getBloc()));
    }
//****************************************** crud  salle****************

public  void ListeService (){
      String sql = "select code from service ";
      connect = DataBase.connectDB();

      try{
          ObservableList listD = FXCollections.observableArrayList();

          prepare=connect.prepareStatement(sql);
          result=prepare.executeQuery();

          while (result.next()){
              listD.add(result.getString("code"));

          }
          idService.setItems(listD);
          ServiceDoctor.setItems(listD);




      }catch(Exception e){
          e.printStackTrace();
      }
}
public  void addSalleAdd(){
    String sql ="insert into salle ( idSalle , nbBed , idService ) values (? ,?,?)";

    try {
        if (nbBed.getText().isEmpty()|| idService.getSelectionModel().getSelectedItem()==null ) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Form Error!");
            alert.setHeaderText(null);
            alert.setContentText(" please fill all blank fields ! ");
            alert.showAndWait();
        }else {



            prepare = connect.prepareStatement(sql);
            prepare.setInt(1, Integer.parseInt(IdSalle.getText()));
            prepare.setInt(2, Integer.parseInt(nbBed.getText()));
            prepare.setString(3, String.valueOf(idService.getSelectionModel().getSelectedItem()));

            prepare.executeUpdate();
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Information !");
            alert.setHeaderText(null);
            alert.setContentText(" Successfully Added ! ");
            alert.showAndWait();
            addSalleShowListdData();
            AddSalleReset();

        }


    }catch(Exception e){
        e.printStackTrace();
    }

}
        public  void AddSalleUpdate(){
        String sql ="update salle set nbBed='"+nbBed.getText()+"' ,  idService='"+idService.getSelectionModel().getSelectedItem() +"' where idSalle='"+IdSalle.getText()+"'";
        connect=DataBase.connectDB();

        try {
            if (IdSalle.getText().isEmpty()  ||nbBed.getText().isEmpty() || idService.getSelectionModel().getSelectedItem()==null) {
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Form Error!");
                alert.setHeaderText(null);
                alert.setContentText(" please fill all blank fields ! ");
                alert.showAndWait();
            }else {

                Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
                alert.setTitle("Confirmation");
                alert.setHeaderText(null);
                alert.setContentText(" are you sure you want update Salle ");

                Optional<ButtonType> option =alert.showAndWait();

                if(option.get().equals(ButtonType.OK)){
                    Statement statement = connect.createStatement();
                    statement.executeUpdate(sql);
                    alert = new Alert(Alert.AlertType.INFORMATION);
                    alert.setTitle("Information!");
                    alert.setHeaderText(null);
                    alert.setContentText(" Updated successfully ");
                    alert.showAndWait();
                    addSalleShowListdData();
                    AddSalleReset();
                }

            }





        }catch (Exception e){
            e.printStackTrace();

        }

    }

    public  void AddSalleDelete(){
          String sql ="delete from salle where idSalle ='"+IdSalle.getText()+"'";
          connect =DataBase.connectDB();
          try{


              if (IdSalle.getText().isEmpty() || nbBed.getText().isEmpty()) {
                  Alert alert = new Alert(Alert.AlertType.ERROR);
                  alert.setTitle("Form Error!");
                  alert.setHeaderText(null);
                  alert.setContentText(" please fill all blank fields ! ");
                  alert.showAndWait();
              }else {

                  Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
                  alert.setTitle("Confirmation");
                  alert.setHeaderText(null);
                  alert.setContentText(" are you sure you want delete service :' "+IdSalle.getText());

                  Optional<ButtonType> option =alert.showAndWait();

                  if(option.get().equals(ButtonType.OK)){
                      Statement statement = connect.createStatement();
                      statement.executeUpdate(sql);
                      alert = new Alert(Alert.AlertType.INFORMATION);
                      alert.setTitle("Information!");
                      alert.setHeaderText(null);
                      alert.setContentText(" delete successfully ");
                      alert.showAndWait();
                      addSalleShowListdData();
                      AddSalleReset();
                  }

              }


          }catch (Exception e){
              e.printStackTrace();
          }
      }



      public void AddSalleReset(){
          nbBed.setText("");
          IdSalle.setText("");
          idService.getSelectionModel().clearSelection();


      }
    public ObservableList<SalleData> addSalleListData(){
        String sql="select * from salle";
        ObservableList<SalleData> listSalle = FXCollections.observableArrayList();
        connect=DataBase.connectDB();
        try {
            prepare =connect.prepareStatement(sql);
            result=prepare.executeQuery();


            SalleData salle ;

            while (result.next()){
                salle = new SalleData(result.getInt("idSalle"),result.getInt("nbBed"),result.getString("idService") );
                listSalle.add(salle);

            }
        }catch (Exception e){
            e.printStackTrace();
        }
        return listSalle ;
    }
    private ObservableList<SalleData> addSalleList ;
    public  void addSalleShowListdData(){
        addSalleList = addSalleListData();
        IdSalleCol.setCellValueFactory(new PropertyValueFactory<>("idSalle"));
        nbBedCol.setCellValueFactory(new PropertyValueFactory<>("nbBed"));
        serviceColl.setCellValueFactory(new PropertyValueFactory<>("idService"));
        tableViewSalle.setItems(addSalleList);
    }

    public void addSalleSelect(){
        SalleData serDate = tableViewSalle.getSelectionModel().getSelectedItem();
        int num = tableViewSalle.getSelectionModel().getSelectedIndex();
        if ((num-1)< -1)
        {return;}
        IdSalle.setText(String.valueOf(serDate.getIdSalle()));
        nbBed.setText(String.valueOf(serDate.getNbBed()));

    }
//*******************switch***********************

//********************crud nurse ******************
public  void addNurseAdd(){
    String sql ="insert into nurse (idNurse , fullName , adresse , mobile , salary  , idSalle ) values (?,?,?,?,?,?)";

    try {
        if (idNurse.getText().isEmpty() ||  NameNurse.getText().isEmpty() || adresseNurse.getText().isEmpty() || mobileNurse.getText().isEmpty()|| salary.getText().isEmpty() || listSalle.getSelectionModel().getSelectedItem()==null) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Form Error!");
            alert.setHeaderText(null);
            alert.setContentText(" please fill all blank fields ! ");
            alert.showAndWait();
        }else {



            prepare = connect.prepareStatement(sql);
            prepare.setInt(1, Integer.parseInt(idNurse.getText()));
            prepare.setString(2, NameNurse.getText());
            prepare.setString(3, adresseNurse.getText());

            prepare.setString(4, mobileNurse.getText());
            prepare.setDouble(5, Double.parseDouble(salary.getText()));
            prepare.setInt(6, Integer.parseInt(String.valueOf(listSalle.getSelectionModel().getSelectedItem())));


            prepare.executeUpdate();
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Information !");
            alert.setHeaderText(null);
            alert.setContentText(" Successfully Added ! ");
            alert.showAndWait();
            addNurseShowListdData();
            AddNurseReset();

        }


    }catch(Exception e){
        e.printStackTrace();
    }

}
    public  void AddNurseUpdate(){
        String sql ="update nurse set  fullname='"+NameNurse.getText() +" ' ,adresse='"+adresseNurse.getText()+" ' , mobile='"+mobileNurse.getText() +"' ,salary ='"+salary.getText()+"' ,idSalle ='"+listSalle.getSelectionModel().getSelectedItem()+" '   where idNurse ='"+idNurse.getText()+"'";
        connect=DataBase.connectDB();

        try {
            if (idNurse.getText().isEmpty() || NameNurse.getText().isEmpty() || adresseNurse.getText().isEmpty() || mobileNurse.getText().isEmpty() || salary.getText().isEmpty() || listSalle.getSelectionModel().getSelectedItem()==null) {
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Form Error!");
                alert.setHeaderText(null);
                alert.setContentText(" please fill all blank fields ! ");
                alert.showAndWait();
            }else {

                Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
                alert.setTitle("Confirmation");
                alert.setHeaderText(null);
                alert.setContentText(" are you sure you want update service :'"+ NameNurse.getText());

                Optional<ButtonType> option =alert.showAndWait();

                if(option.get().equals(ButtonType.OK)){
                    Statement statement = connect.createStatement();
                    statement.executeUpdate(sql);
                    alert = new Alert(Alert.AlertType.INFORMATION);
                    alert.setTitle("Information!");
                    alert.setHeaderText(null);
                    alert.setContentText(" Updated successfully ");
                    alert.showAndWait();
                    addNurseShowListdData();
                    AddNurseReset();
                }

            }





        }catch (Exception e){
            e.printStackTrace();

        }

    }

    public  void AddNurseDelete(){
        String sql ="delete from nurse where idNurse ='"+idNurse.getText()+"'";
        connect =DataBase.connectDB();
        try{


            if (idNurse.getText().isEmpty() || NameNurse.getText().isEmpty() || adresseNurse.getText().isEmpty() || mobileNurse.getText().isEmpty() || salary.getText().isEmpty() || listSalle.getSelectionModel().getSelectedItem()==null) {
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Form Error!");
                alert.setHeaderText(null);
                alert.setContentText(" please fill all blank fields ! ");
                alert.showAndWait();
            }else {

                Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
                alert.setTitle("Confirmation");
                alert.setHeaderText(null);
                alert.setContentText(" are you sure you want delete service :' "+NameNurse.getText());

                Optional<ButtonType> option =alert.showAndWait();

                if(option.get().equals(ButtonType.OK)){
                    Statement statement = connect.createStatement();
                    statement.executeUpdate(sql);
                    alert = new Alert(Alert.AlertType.INFORMATION);
                    alert.setTitle("Information!");
                    alert.setHeaderText(null);
                    alert.setContentText(" delete successfully ");
                    alert.showAndWait();
                    addNurseShowListdData();
                    AddNurseReset();
                }

            }


        }catch (Exception e){
            e.printStackTrace();
        }
    }



    public void AddNurseReset(){
        idNurse.setText("");
        NameNurse.setText("");
        nameService.setText("");
        adresseNurse.setText("");
        mobileNurse.setText("");
        salary.setText("");
        listSalle.getSelectionModel().clearSelection();
    }
    public ObservableList<NurseData> addNurseListData(){
        String sql="select * from nurse";
        ObservableList<NurseData> listNurse = FXCollections.observableArrayList();
        connect=DataBase.connectDB();
        try {
            prepare =connect.prepareStatement(sql);
            result=prepare.executeQuery();


            NurseData nurse ;

            while (result.next()){
                nurse = new NurseData(result.getInt("idNurse"),result.getString("fullName"),result.getString("adresse"),result.getString("mobile") ,result.getDouble("salary") , result.getInt("idSalle"));
                listNurse.add(nurse);

            }
        }catch (Exception e){
            e.printStackTrace();
        }
        return listNurse ;
    }
    private ObservableList<NurseData> addNurseList ;
    public  void addNurseShowListdData(){
        addNurseList = addNurseListData();
        IdNurseCol.setCellValueFactory(new PropertyValueFactory<>("IdNurse"));
        NurseNameCol.setCellValueFactory(new PropertyValueFactory<>("fullName"));
        NurseAdresse.setCellValueFactory(new PropertyValueFactory<>("adresse"));
        nurseMobile.setCellValueFactory(new PropertyValueFactory<>("mobile"));
        salaryNurse.setCellValueFactory(new PropertyValueFactory<>("salary"));
        numSalle.setCellValueFactory(new PropertyValueFactory<>("idSalle"));
        TableViewNurse.setItems(addNurseList);
    }

    public void addNurseSelect(){
        NurseData serDate = TableViewNurse.getSelectionModel().getSelectedItem();
        int num = TableViewNurse.getSelectionModel().getSelectedIndex();
        if ((num-1)< -1)
        {return;}
        idNurse.setText(String.valueOf(serDate.getIdNurse()));
        NameNurse.setText(String.valueOf(serDate.getFullName()));
        adresseNurse.setText(String.valueOf(serDate.getAdresse()));
        mobileNurse.setText(String.valueOf(serDate.getMobile()));
        salary.setText(String.valueOf(serDate.getSalary()));
    }
    public  void ListeSalle (){
        String sql = "select idSalle from salle ";
        connect = DataBase.connectDB();

        try{
            ObservableList listD = FXCollections.observableArrayList();

            prepare=connect.prepareStatement(sql);
            result=prepare.executeQuery();

            while (result.next()){
                listD.add(result.getString("idSalle"));

            }
            listSalle.setItems(listD);




        }catch(Exception e){
            e.printStackTrace();
        }
    }
//**********************************Crud Doctor *********************************

    public  void addDoctorAdd(){
        String sql ="insert into doctor ( doctor_id , username , password  , mobile , adresse , specialized , service ) values (? ,?,?,?,?,?,?)";

        try {
            if (codeDoctor.getText().isEmpty()|| fullNameDoctor.getText().isEmpty() || adresseDoctor.getText().isEmpty() || mobileDoctor.getText().isEmpty() || specialiteDoctor.getText().isEmpty() || passwordDoctor.getText().isEmpty() || ServiceDoctor.getSelectionModel().getSelectedItem()==null ) {
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Form Error!");
                alert.setHeaderText(null);
                alert.setContentText(" please fill all blank fields ! ");
                alert.showAndWait();
            }else {



                prepare = connect.prepareStatement(sql);
                prepare.setInt(1, Integer.parseInt(codeDoctor.getText()));
                prepare.setString(2, fullNameDoctor.getText());
                prepare.setString(3, passwordDoctor.getText());
                prepare.setString(4, mobileDoctor.getText());
                prepare.setString(5,  adresseDoctor.getText());
                prepare.setString(6, specialiteDoctor.getText());

                prepare.setString(7, String.valueOf(ServiceDoctor.getSelectionModel().getSelectedItem()));

                prepare.executeUpdate();
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("Information !");
                alert.setHeaderText(null);
                alert.setContentText(" Successfully Added ! ");
                alert.showAndWait();
                addDoctorShowListdData();
                AddDoctorReset();

            }


        }catch(Exception e){
            e.printStackTrace();
        }

    }
    public  void AddDoctorUpdate(){
        String sql ="update doctor set username='"+fullNameDoctor.getText()+"' , password='"+passwordDoctor.getText()+"' , mobile='"+mobileDoctor.getText()+"' ,  adresse='"+adresseDoctor.getText()+"', specialized='"+specialiteDoctor.getText()+"' , service='"+ServiceDoctor.getSelectionModel().getSelectedItem() +"' where doctor_id='"+codeDoctor.getText()+"'";
        connect=DataBase.connectDB();

        try {
            if (codeDoctor.getText().isEmpty()|| fullNameDoctor.getText().isEmpty() || adresseDoctor.getText().isEmpty() || mobileDoctor.getText().isEmpty() || specialiteDoctor.getText().isEmpty() || passwordDoctor.getText().isEmpty() || ServiceDoctor.getSelectionModel().getSelectedItem()==null ) {
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Form Error!");
                alert.setHeaderText(null);
                alert.setContentText(" please fill all blank fields ! ");
                alert.showAndWait();
            }else {

                Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
                alert.setTitle("Confirmation");
                alert.setHeaderText(null);
                alert.setContentText(" are you sure you want update doctor ");

                Optional<ButtonType> option =alert.showAndWait();

                if(option.get().equals(ButtonType.OK)){
                    Statement statement = connect.createStatement();
                    statement.executeUpdate(sql);
                    alert = new Alert(Alert.AlertType.INFORMATION);
                    alert.setTitle("Information!");
                    alert.setHeaderText(null);
                    alert.setContentText(" Updated successfully ");
                    alert.showAndWait();
                    addDoctorShowListdData();
                    AddDoctorReset();
                }

            }





        }catch (Exception e){
            e.printStackTrace();

        }

    }

    public  void AddDoctorDelete(){
        String sql ="delete from doctor where doctor_id ='"+codeDoctor.getText()+"'";
        connect =DataBase.connectDB();
        try{


            if (codeDoctor.getText().isEmpty()|| fullNameDoctor.getText().isEmpty() || adresseDoctor.getText().isEmpty() || mobileDoctor.getText().isEmpty() || specialiteDoctor.getText().isEmpty() || passwordDoctor.getText().isEmpty() || ServiceDoctor.getSelectionModel().getSelectedItem()==null ) {
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Form Error!");
                alert.setHeaderText(null);
                alert.setContentText(" please fill all blank fields ! ");
                alert.showAndWait();
            }else {

                Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
                alert.setTitle("Confirmation");
                alert.setHeaderText(null);
                alert.setContentText(" are you sure you want delete service :' "+codeDoctor.getText());

                Optional<ButtonType> option =alert.showAndWait();

                if(option.get().equals(ButtonType.OK)){
                    Statement statement = connect.createStatement();
                    statement.executeUpdate(sql);
                    alert = new Alert(Alert.AlertType.INFORMATION);
                    alert.setTitle("Information!");
                    alert.setHeaderText(null);
                    alert.setContentText(" delete successfully ");
                    alert.showAndWait();
                    addDoctorShowListdData();
                    AddDoctorReset();
                }

            }


        }catch (Exception e){
            e.printStackTrace();
        }
    }



    public void AddDoctorReset(){
        codeDoctor.setText("");
        fullNameDoctor.setText("");
        passwordDoctor.setText("");
        specialiteDoctor.setText("");
        adresseDoctor.setText("");
        mobileDoctor.setText("");


        ServiceDoctor.getSelectionModel().clearSelection();


    }
    public ObservableList<DoctorData> addDoctorListData(){
        String sql="select * from doctor";
        ObservableList<DoctorData> listDoc = FXCollections.observableArrayList();
        connect=DataBase.connectDB();
        try {
            prepare =connect.prepareStatement(sql);
            result=prepare.executeQuery();


            DoctorData doctor ;

            while (result.next()){
                doctor = new DoctorData(result.getInt("doctor_id"),result.getString("username"),result.getString("adresse"),result.getString("password"),result.getString("specialized") , result.getString("service"),result.getString("mobile") );
                listDoc.add(doctor);

            }
        }catch (Exception e){
            e.printStackTrace();
        }
        return listDoc ;
    }
    private ObservableList<DoctorData> addDoctorList ;
    public  void addDoctorShowListdData(){
        addDoctorList = addDoctorListData();
        DoctorCode.setCellValueFactory(new PropertyValueFactory<>("code"));
        DoctorName.setCellValueFactory(new PropertyValueFactory<>("fullName"));
        DoctorAdresse.setCellValueFactory(new PropertyValueFactory<>("adresse"));
        DoctorMobile.setCellValueFactory(new PropertyValueFactory<>("mobile"));
        DoctorSpectialite.setCellValueFactory(new PropertyValueFactory<>("specialite"));
      //  DoctorPassword.setCellValueFactory(new PropertyValueFactory<>("adresse"));
        DoctorService.setCellValueFactory(new PropertyValueFactory<>("service"));


        tableViewDoctor.setItems(addDoctorList);
    }

    public void addDoctorSelect(){
        DoctorData serDate = tableViewDoctor.getSelectionModel().getSelectedItem();
        int num = tableViewDoctor.getSelectionModel().getSelectedIndex();
        if ((num-1)< -1)
        {return;}
        codeDoctor.setText(String.valueOf(serDate.getCode()));
        fullNameDoctor.setText(String.valueOf(serDate.getFullName()));
        adresseDoctor.setText(String.valueOf(serDate.getAdresse()));
        mobileDoctor.setText(String.valueOf(serDate.getMobile()));
        specialiteDoctor.setText(String.valueOf(serDate.getSpecialite()));
        //ServiceDoctor.setText(String.valueOf(serDate.getFullName()));
        codeDoctor.setText(String.valueOf(serDate.getCode()));
        passwordDoctor.setText(String.valueOf(serDate.getPassword()));

    }
    //*************************************************
    private double y=0 ;
    private double x=0 ;
    public void switchForm(ActionEvent event) {
        if (event.getSource() == AddBtn) {
            CrudForm.setVisible(true);
            HomeForm.setVisible(false);
            CrudNurse.setVisible(false);
            CrudSalle.setVisible(false);
            CrudService.setVisible(false);
            ListeService();
            addDoctorShowListdData();

        } else if (event.getSource() == HomeBtn) {
            CrudForm.setVisible(false);
            HomeForm.setVisible(true);
            CrudNurse.setVisible(false);
            CrudSalle.setVisible(false);
            CrudService.setVisible(false);

        } else if (event.getSource() == AddNurse) {
            CrudForm.setVisible(false);
            HomeForm.setVisible(false);
            CrudNurse.setVisible(true);
            CrudSalle.setVisible(false);
            CrudService.setVisible(false);
            ListeSalle ();
            addNurseShowListdData();

        }
        else if (event.getSource() == addSalle) {
            CrudForm.setVisible(false);
            HomeForm.setVisible(false);
            CrudNurse.setVisible(false);
            CrudSalle.setVisible(true);
            CrudService.setVisible(false);
            ListeService();
            addSalleShowListdData();
        }
        else if (event.getSource() == addService) {
            CrudForm.setVisible(false);
            HomeForm.setVisible(false);
            CrudNurse.setVisible(false);
            CrudSalle.setVisible(false);
            CrudService.setVisible(true);
            addServiceShowListdData();
            AddServiceSearch();

        }
    }
    public  void logout (){
        try {
            Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
            alert.setTitle("Form Error!");
            alert.setHeaderText(null);
            alert.setContentText("Are you sure you want to logout ?");
            Optional<ButtonType> option = alert.showAndWait();
                LogoutBtn.getScene().getWindow().hide();
            if (option.get().equals(ButtonType.OK)) {
                Parent root = FXMLLoader.load(getClass().getResource("hello-view.fxml"));
                Stage stage = new Stage();
                Scene scene = new Scene(root);
               //stage.initStyle(StageStyle.TRANSPARENT);
               stage.setTitle("hospiatl management system");

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
            }
        }catch (Exception e){
            e.printStackTrace();
        }
    }



    public void dashboardTC() {

        String sql = "SELECT COUNT(doctor_id) FROM doctor ";

        connect = DataBase.connectDB();

        int tc = 0;

        try {
            prepare = connect.prepareStatement(sql);
            result = prepare.executeQuery();

            if (result.next()) {
                tc = result.getInt("COUNT(doctor_id)");
            }
            TotalDoctor.setText(String.valueOf(tc));

        } catch (Exception e) {
            e.printStackTrace();
        }

    }
    public void dashboardNurse() {

        String sql = "SELECT COUNT(idNurse) FROM nurse ";

        connect = DataBase.connectDB();

        int tc = 0;

        try {
            prepare = connect.prepareStatement(sql);
            result = prepare.executeQuery();

            if (result.next()) {
                tc = result.getInt("COUNT(idNurse)");
            }
            totalNurse.setText(String.valueOf(tc));

        } catch (Exception e) {
            e.printStackTrace();
        }

    }
    public void dashboardSalle() {

        String sql = "SELECT COUNT(idSalle) FROM salle ";

        connect = DataBase.connectDB();

        int tc = 0;

        try {
            prepare = connect.prepareStatement(sql);
            result = prepare.executeQuery();

            if (result.next()) {
                tc = result.getInt("COUNT(idSalle)");
            }
            totalSalle.setText(String.valueOf(tc));

        } catch (Exception e) {
            e.printStackTrace();
        }

    }





    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        dashboardSalle();
        dashboardNurse();
        dashboardTC();
        addServiceShowListdData();
        ListeService();
        addSalleShowListdData();
        ListeSalle ();
        addNurseShowListdData();
        ListeService();
        addDoctorShowListdData();
}}
