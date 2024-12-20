package com.example.controllers;

import com.example.User;

import javafx.fxml.FXML;
import javafx.scene.control.TextField;

public class HomeScreenController {

    
    @FXML
    private TextField usernameField;

    @FXML
    public void createUser() {
        User user = new User(usernameField.getText());
        user.setUsername(usernameField.getText());
    }
}