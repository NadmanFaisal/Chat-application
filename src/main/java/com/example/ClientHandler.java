package com.example;

import java.net.*;
import java.util.ArrayList;
import java.io.*;

public class ClientHandler implements Runnable {
    private Socket client;
    private DataInputStream input;
    private DataOutputStream output;
    private ArrayList<ClientHandler> clients;

    public ClientHandler(Socket clientSocket, ArrayList<ClientHandler> clients) throws IOException {
        this.client = clientSocket;
        this.clients = clients;
        input = new DataInputStream(new BufferedInputStream(this.client.getInputStream()));
        output = new DataOutputStream(this.client.getOutputStream());
    }

    @Override
    public void run() {
        try {
			// Creates inputstream to get input from client
			input = new DataInputStream(
				new BufferedInputStream(this.client.getInputStream()));

			String clientMessage = "";

			// Reads messages sent from the client until
			while (!clientMessage.equals("Over")) {
				try {
					clientMessage = input.readUTF();
					System.out.println(clientMessage);
                    broadcastMessage(clientMessage);
				}
				catch(IOException e) {
					System.out.println(e);
				}
			}
			System.out.println("Closing connection");

			// Close connections to avoid leaks
			this.client.close();
			input.close();
		}
		catch(IOException e) {
			System.out.println(e);
		}

    }

    private void broadcastMessage(String message) {
        for(ClientHandler client : clients) {
            try {
                client.output.writeUTF(message);
            } catch (IOException e) {
                System.out.println("Error sending message: " + e.getMessage());
            }
        }
    }
    
}
