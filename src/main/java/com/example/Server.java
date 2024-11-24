package com.example;
import java.io.*;
import java.net.*;

public class Server {
    private static final String SERVER_ADDRESS = "localhost";
    private static final int PORT = 8080;

    private static void main(String[] args) {
        try {
        Socket socket = new Socket(SERVER_ADDRESS, PORT);
        System.out.println("Connected to the server succesfully!");

        PrintWriter out = new PrintWriter(socket.getOutputStream(), true);
        BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));

        new Thread(() -> {
            try {
                String message;
                while ((message = in.readLine()) != null) {
                    System.out.println(message);
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }).start();

        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        String userInput;
        while ((userInput = reader.readLine()) != null) {
            out.println(userInput);
        }

    } catch(IOException e) {
        e.printStackTrace();
    }
           
    }
}
