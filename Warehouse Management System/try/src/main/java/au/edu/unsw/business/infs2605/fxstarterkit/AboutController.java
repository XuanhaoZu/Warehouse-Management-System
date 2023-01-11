package au.edu.unsw.business.infs2605.fxstarterkit;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Hyperlink;
import javafx.scene.layout.Pane;

public class AboutController {

    @FXML
    private Button btnCopyright;
    @FXML
    private Button btnFAQ;
    @FXML
    private Pane pnCopyright;
    @FXML
    private Pane pnFAQ;

    @FXML
    private Hyperlink linkDatamanage;
    @FXML
    private Hyperlink linkSettings;
    @FXML
    private Hyperlink linkLogout;

    @FXML
    public void linkSettingsClick(ActionEvent event) throws java.io.IOException {
        System.out.println("linkSettingsClick");
        App.setRoot("settings");
    }

    public void linkDatamanageClick(ActionEvent event) throws java.io.IOException {
        System.out.println("datamanageclick");
        App.setRoot("secondary");
    }

    public void linkLogoutClick(ActionEvent event) throws java.io.IOException {
        App.setRoot("primary");
    }

    @FXML
    private void handleButtonAction(ActionEvent event) {
        //Clicking on certain buttons changes the screen
        if (event.getSource() == btnCopyright) {
            pnCopyright.toFront();
        } else if (event.getSource() == btnFAQ) {
            pnFAQ.toFront();

        }
    }

}
