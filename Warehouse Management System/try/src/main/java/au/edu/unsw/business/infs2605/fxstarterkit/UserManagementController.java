package au.edu.unsw.business.infs2605.fxstarterkit;

import java.io.IOException;
import javafx.fxml.FXML;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import javafx.event.ActionEvent;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.control.PasswordField;
import javafx.scene.control.Hyperlink;
import javafx.scene.layout.Pane;

public class UserManagementController {

    @FXML
    private TextField txtUsername;

    @FXML
    private PasswordField txtPassword;

    @FXML
    private Button btnLogin;

    @FXML
    private Label lblIncorrectLogin;

    @FXML
    private Label lblIncorrectDetails;

    @FXML
    private Pane pnSignUp;

    @FXML
    private Pane pnSignIn;

    @FXML
    private Hyperlink hyperLinkSignUp;

    @FXML
    private Hyperlink hyperLinkSignIn;

    @FXML
    private TextField txtUsername1;

    @FXML
    private PasswordField txtPassword1;

    @FXML
    private TextField txtFullName;

    @FXML
    private void switchToSecondary() throws IOException {
        App.setRoot("secondary");
    }

    @FXML
    private void switchPane(ActionEvent event) {
        //Clicking on certain buttons changes the screen
        if (event.getSource() == hyperLinkSignUp) {
            pnSignUp.toFront();
        } else if (event.getSource() == hyperLinkSignIn) {
            pnSignIn.toFront();
        }
    }

    @FXML
    private void switchToFAQ(ActionEvent event) throws IOException {
        App.setRoot("faq");
    }

    @FXML
    private void handleButtonAction(ActionEvent event) throws IOException, SQLException {
        String username = txtUsername.getText().trim();
        String password = txtPassword.getText();
        Database databaseObj = new Database();
        boolean success = databaseObj.login(username, password);
        
        if (success) {
            App.setRoot("secondary");
        } else {
            lblIncorrectLogin.setText("Incorrect username or password");
            lblIncorrectLogin.setVisible(true);
        }
    }

    @FXML
    private void handleRegisterButton(ActionEvent event) throws IOException, SQLException {
        String username = txtUsername1.getText();
        String password = txtPassword1.getText();
        String fullName = txtFullName.getText();
        try {
            Connection conn = DriverManager.getConnection("jdbc:sqlite:groupassignmentdatabase.db");
            Statement st = conn.createStatement();
            String sqlQuery = "INSERT INTO user (username, password, full_name) "
                    + "VALUES ('" + username + "','" + password + "','" + fullName + "');";
            st.execute(sqlQuery);

            switchToSecondary();
        } catch (SQLException e) {

            if (e.getErrorCode() == 19) {
                lblIncorrectDetails.setText("Username already exists");
            }

        }
    }
}
