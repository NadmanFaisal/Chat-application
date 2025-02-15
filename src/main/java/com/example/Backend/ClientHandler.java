package com.example.Backend;

import java.io.BufferedInputStream;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.util.ArrayList;

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
            if(client != this) {
                try {
                    client.output.writeUTF(message);
                } catch (IOException e) {
                    System.out.println("Error sending message: " + e.getMessage());
               }
            }
        }
    }
    
}
