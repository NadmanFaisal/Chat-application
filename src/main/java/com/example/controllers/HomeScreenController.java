package com.example.controllers;

import java.io.IOException;

import com.example.Backend.User;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Scene;  // Fix for Node import
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class HomeScreenController {

    @FXML
    private TextField usernameField;

    @FXML
    public void createUser(ActionEvent action) {
        System.out.println(usernameField.getText());
        System.out.println("Reaching till here");
        User user = new User(usernameField.getText(), "127.0.0.1", 5000);
        user.setUsername(usernameField.getText());
        switchToChatScreen(action, user);
    }

    public void switchToChatScreen(ActionEvent action, User user) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/example/UI/chatScreen.fxml"));
            Scene chatScene = new Scene(loader.load());

            ChatScreenController controller = loader.getController();
            controller.setUser(user);

            Stage stage = (Stage) ((Node) action.getSource()).getScene().getWindow();
            stage.setScene(chatScene);
            stage.setTitle("Chat Screen - " + user.getUsername());
            stage.show();

        } catch (IOException e) {
            System.out.println("Failed to load ChatScreen: " + e.getMessage());
        }
    }
}
