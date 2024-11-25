package com.example;

import java.net.*;
import java.io.*;

public class ClientHandler implements Runnable {
    private Socket client;
    private DataInputStream input;

    public ClientHandler(Socket clientSocket) throws IOException {
        this.client = clientSocket;
        input = new DataInputStream(new BufferedInputStream(this.client.getInputStream()));
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
    
}
