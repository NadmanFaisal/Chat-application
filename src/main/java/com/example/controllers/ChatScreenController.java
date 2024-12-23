package com.example.controllers;

import com.example.Backend.User;

import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;

public class ChatScreenController {

    @FXML
    private TextArea chatArea;

    @FXML
    private TextField messageField;

    private User user;

    public void setUser(User user) {
        this.user = user;
        chatArea.appendText("Welcome, " + user.getUsername() + "!\n");

        // Update the TextArea on the GUI through thread created in the method
        this.user.getClient().setOnMessageReceived(message -> {
            Platform.runLater(() -> {
                chatArea.appendText(message + "\n");
            });
        });
    }

    @FXML
    public void sendMessage() {
        String message = messageField.getText();

        if(message.isEmpty()) {
            chatArea.appendText("Please enter a text to send. ");
            return;
        } else {
            chatArea.appendText("Me: " + message + "\n");
            user.sendMessage(message);
        }
    }

}
