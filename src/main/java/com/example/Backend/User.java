package com.example.Backend;

import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

public class User {
    private static final Logger logger = Logger.getLogger(User.class.getName());
    private Client client;
    private String username;
    private final String address;
    private final int port;

    public User(String username, String address, int port) {
        this.username = username;
        this.address = address;
        this.port = port;
        this.initializeClient();
    }

    public void initializeClient() {
        if (client == null) {
            try {
                client = new Client(this.username, address, port);
            } catch (IOException e) {
                logger.log(Level.SEVERE, "Failed to connect to server: " + e.getMessage());
            }
        }
    }

    // Sends message to other clients through the server
    public void sendMessage(String message) {
        if (client != null) {
            client.sendMessage(message);
        } else {
            logger.warning("Client is not connected. Message not sent.");
        }
    }

    // Closes the client connection
    public void disconnect() {
        if (client != null) {
            client.close();
            client = null;
        }
    }

    public String getUsername() {
        return this.username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public Client getClient() {
        return this.client;
    }

    public boolean isClientConnected() {
        return client != null;
    }
}
