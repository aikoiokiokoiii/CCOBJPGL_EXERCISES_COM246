package com.example;
 
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.net.URL;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import java.util.Scanner;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
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
    private ChoiceBox<String> choyschoicebox;
 
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
    private TextField usertextfield;
 
    @FXML
    private TextField passtextfield;
 
    @FXML
    private TableColumn<User, String> usrcolumn;
 
 
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        initializeCol();
        loadData();

        choyschoicebox.getItems().addAll("Active", "Inactive");

        // TODO Auto-generated method stub
        String username = User.getUsername(LoginController.user);
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
    

       @FXML
    private boolean createuser(ActionEvent event) {

        String username = usertextfield.getText();

        String password = passwpasswordfield.getText();

        String status = choyschoicebox.getValue();

        System.out.println(status);
        username = username.trim();
        password = password.trim();
        status = status.trim();

        if(username.length() == 0)
        {
            Alert alert = new Alert(AlertType.ERROR);
            alert.setContentText("no username provided");
        }

        if(password.length() == 0)
        {
            Alert alert = new Alert(AlertType.ERROR);
            alert.setContentText("no password provided");
        }

        // Get current date
        LocalDate today = LocalDate.now();

        // Format as MM-dd-yyyy
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MM-dd-yyyy");
        String formattedDate = today.format(formatter);

        // Create user object
        User user = new User(username, password, formattedDate, status);

        try {

            BufferedWriter myWriter = new BufferedWriter(new FileWriter("accounts.txt", true));
      
            // .write() methods adds content to the file
            myWriter.newLine(); // adds a new line
            myWriter.write(User.getUsername(user) + "," + user.getPassword() + "," + user.getAccountcreated() + "," + user.getAccountstatus());

            // Close FileWriter
            myWriter.close();

            Alert alert = new Alert(AlertType.INFORMATION);
            alert.setTitle("Information Dialog");
            alert.setHeaderText("Account Created");
            alert.setContentText("You have created a new account!");
            alert.showAndWait();
            
            // Refresh data in table
            loadData();

        } catch (IOException e) {
            System.out.println("An error occurred.");
        }

        return true;
    }
    @FXML
    public boolean updateuser(ActionEvent event) {

        User user = Atable.getSelectionModel().getSelectedItem();

        String username = usertextfield.getText();

        String password = passwpasswordfield.getText();

        String status = choyschoicebox.getValue();


        username = username.trim();
        password = password.trim();
        status = status.trim();

        if(username.length() == 0)
        {
            System.out.println("No username!");
            return false;
        }

        if(password.length() == 0)
        {
            System.out.println("No password!");
            return false;
        }

        String targetUsername = User.getUsername(user);
        String newUsername = username;
        String newPassword = password;
        String newStatus = status;

        List<String> updatedLines = new ArrayList<>();

        // Step 1: Read and update
        try (BufferedReader reader = new BufferedReader(new FileReader("accounts.txt"))) {
            String line;
            while ((line = reader.readLine()) != null) {
                if (!line.trim().isEmpty()) {
                    String[] parts = line.split(",");

                    if (parts.length == 4 && parts[0].equalsIgnoreCase(targetUsername)) {
                        updatedLines.add(newUsername + "," + newPassword + "," + user.getAccountcreated() + "," + newStatus);
                    } else {
                        updatedLines.add(line);
                    }

                    // if (parts.length == 2 && parts[0].equalsIgnoreCase(targetUsername)) {
                    //     // Update password
                    //     updatedLines.add(parts[0] + "," + newPassword + "," + newStatus);
                    // } else {
                    //     updatedLines.add(line);
                    }
                }
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }

        // Step 2: Write updated lines back
        try (BufferedWriter writer = new BufferedWriter(new FileWriter("accounts.txt"))) {
            for (int i = 0; i < updatedLines.size(); i++) {
                writer.write(updatedLines.get(i));
                if (i < updatedLines.size() - 1) {
                    writer.newLine(); // no extra blank line
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }

        Alert alert = new Alert(AlertType.INFORMATION);
        alert.setTitle("Information Dialog");
        alert.setHeaderText("This is the header");
        alert.setContentText("User '" + targetUsername + "' has been updated.");
        alert.showAndWait();
        loadData();
        return true;
    }

    @FXML  
    public boolean deleteuser(ActionEvent event) {

        // Get selected row from table
        User user = Atable.getSelectionModel().getSelectedItem();

        String username = (User.getUsername(user));

        System.out.println(username);

        //String filename = "accounts.txt";
        String userToDelete = username;

        List<String> updatedLines = new ArrayList<>();

        // Step 1: Read and filter lines
        try (BufferedReader reader = new BufferedReader(new FileReader("accounts.txt"))) {
            String line;
            while ((line = reader.readLine()) != null) {
                if (!line.trim().isEmpty()) { // skip empty lines
                    String[] parts = line.split(",");
                    if (!parts[0].equalsIgnoreCase(userToDelete)) {
                        updatedLines.add(line);
                    }
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }

        // Step 2: Write back without trailing newline
        try (BufferedWriter writer = new BufferedWriter(new FileWriter("accounts.txt"))) {
            for (int i = 0; i < updatedLines.size(); i++) {
                writer.write(updatedLines.get(i));
                if (i < updatedLines.size() - 1) {
                    writer.newLine(); // add newline except after the last line
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }

        Alert alert = new Alert(AlertType.INFORMATION);
        alert.setTitle("Information Dialog");
        alert.setHeaderText("This is the header");
        alert.setContentText("User '" + userToDelete + "' has been deleted (if existed).");
        alert.showAndWait();        
        loadData();

        return true;
    }
}