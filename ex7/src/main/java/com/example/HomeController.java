package com.example;
 
import java.io.File;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.Scanner;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
 
 
public class HomeController implements Initializable{
    ObservableList<User> mylist = FXCollections.observableArrayList();
 
    @FXML
    Label usernamelabel;
    @FXML
    private TableView<User> Atable;
 
    @FXML
    private TableColumn<User, String> acccrecolumn;
 
    @FXML
    private ChoiceBox<User> choyschoicebox;
 
    @FXML
    private Button createbutton;
 
    @FXML
    private Button deletebutton;
 
    @FXML
    private PasswordField passwpasswordfield;
 
    @FXML
    private TableColumn<User, String> psscolumn;
 
    @FXML
    private TableColumn<User, String> statscolumn;
 
    @FXML
    private Button updatebutton;
 
    @FXML
    private TextField userntextfield;
 
    @FXML
    private TextField passtextfield;
 
    @FXML
    private TableColumn<User, String> usrcolumn;
 
 
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        initializeCol();
        loadData();
        // TODO Auto-generated method stub
        String username = LoginController.user.getUsername();
        usernamelabel.setText(username);
    }

        private void initializeCol(){

        usrcolumn.setCellValueFactory(new PropertyValueFactory<>("username"));
        psscolumn.setCellValueFactory(new PropertyValueFactory<>("password"));
        acccrecolumn.setCellValueFactory(new PropertyValueFactory<>("accountcreated"));
        statscolumn.setCellValueFactory(new PropertyValueFactory<>("accountstatus"));
        }
        private void loadData(){

             mylist.clear(); 

    
        try {
            // Create object from File class
            File myFile = new File("accounts.txt");

            // .exists() method checks if a file exists in the pathname
            if (myFile.exists()) {

                Scanner filescanner = new Scanner(myFile);

                while (filescanner.hasNextLine()) {

                    String data = filescanner.nextLine();
        
                    String username = data.split(",")[0];
                    String password = data.split(",")[1];
                    String dcreated = data.split(",")[2];
                    String status = data.split(",")[3];

                    mylist.add(new User(username, password, dcreated, status));
                } 
                Atable.setItems(mylist);

                filescanner.close();
            }
            else {
                System.out.println(myFile.getName() + " does not exist!");
            }
        } catch (Exception e) {
            System.out.println("There is an error");
        } 
        }
    }
