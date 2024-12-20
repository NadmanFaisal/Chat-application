package com.example.Backend;


public class User {
    private Client client;
    private String username;
    private final String ADDRESS = "127.0.0.1";
    private final int PORT = 5000;

    public User(String username) {
        this.username = username;
        client = new Client(this.username, ADDRESS, PORT);
    }

    public String getUsername() {
        return this.username;
    }

    public void setUsername(String username) {
        this.username = username;
    }
}
