/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package au.edu.unsw.business.infs2605.fxstarterkit;

import java.io.IOException;
import java.sql.SQLException;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Hyperlink;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.Pane;

/**
 *
 * @author 91296
 */
public class SettingsController {
    Database databaseObj = new Database();
    
    @FXML
    private Hyperlink linkLogout;
    @FXML
    private Hyperlink linkFAQ;
    @FXML
    private Hyperlink linkDataManagement;
    
    
    @FXML
    private Button btnAccount;
    @FXML
    private Button btnPreference;
    @FXML
    private Button btnVersion;
    
    
    @FXML
    private Pane pnAccount;
    @FXML
    private TextField txtCurrentPassword;
    @FXML
    private TextField txtNewPassword;
    @FXML
    private TextField txtReEnterPassword;
    @FXML
    private Button btnChangePassword;
    @FXML
    private Label labPasswordChanged;
    
    
    
    @FXML
    private Pane pnPreference;
    @FXML
    private Button btnChangeToDescending;
    @FXML
    private Button btnSizeLarger;
    @FXML
    private Button btnSizeSmaller;
    
 
    @FXML
    private Pane pnVersion;
    
    @FXML
    public void initialize() throws SQLException, ClassNotFoundException, IOException {
        
     labPasswordChanged.setVisible​(false);   
     
    }
    
    //switch panes
     @FXML
    private void handleButtonAction(ActionEvent event) {
        //Clicking on certain buttons changes the screen
        if (event.getSource() == btnAccount) {
            pnAccount.toFront();
        } else if (event.getSource() == btnPreference) {
            pnPreference.toFront();
        } else if (event.getSource() == btnVersion) {
            pnVersion.toFront();
        }
    }
    
    //navigation
    @FXML
         public void linkAboutClick(ActionEvent event) throws IOException{
         System.out.println("aboutlink");
         App.setRoot("about");
     }
    
   
     public void linkDatamanageClick(ActionEvent event) throws java.io.IOException{
         System.out.println("datamanageclick");
         App.setRoot("secondary");
     }
     
     public void linkLogoutClick(ActionEvent event) throws java.io.IOException{
         App.setRoot("primary");
     }
     
     
     
     //Account Pane - set account 
     @FXML
     private void setAccount(ActionEvent event) throws IOException, SQLException{
         String cp = txtCurrentPassword.getText();
         String np = txtNewPassword.getText();
         String re = txtReEnterPassword.getText();
        
         boolean check = databaseObj.setAccount1(cp); //check current password  
         System.out.println("current password is right");
         if(check){
                if(np.equals(re)){
                    System.out.println("two password matched");
                 boolean success = databaseObj.setAccount2(cp, np); 
                 System.out.println("change password");
                        if(success){
                            labPasswordChanged.setText("Password changed successfully!");
                            labPasswordChanged.setVisible(true);                 
                         }
                        else{
                          labPasswordChanged.setText("Failed to change password. Please try again");
                          labPasswordChanged.setVisible(true);                   
                        }
                }  
                else{
                    labPasswordChanged.setText("Two Passwords don't match, try again");
                    labPasswordChanged.setVisible(true);    
                }
         }
         else{
             labPasswordChanged.setText("Incorrcet Password, try again");
             labPasswordChanged.setVisible(true);
         }
     }
     
     
    
    
    
}
