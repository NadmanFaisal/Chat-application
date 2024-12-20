package com.example.Controllers;

import javafx.fxml.FXML;
import javafx.scene.control.TextField;

public class HomeScreenController {

    @FXML
    private TextField usernameField;

    // Method to handle the 'onAction' event from the TextField
    @FXML
    private void setUserName() {
        String username = usernameField.getText();
        System.out.println("Username: " + username);
    }
}