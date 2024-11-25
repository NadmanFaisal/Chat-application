package com.example;

import java.net.*;
import java.util.ArrayList;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.io.*;

public class Server {
	// Initizlizss the socket, server and input
	private Socket socket = null;
	private ServerSocket server = null;
	private DataInputStream input = null;
	private ArrayList<ClientHandler> clients = new ArrayList<>();
	private ExecutorService pool = Executors.newFixedThreadPool(4);


	public Server(int port) {

		try {
            // Creates a server and waits for connection
			server = new ServerSocket(port);
			System.out.println("Server started");

			while(true) {
            	// Accepts connection with client
				socket = server.accept();
				System.out.println("Client accepted");
				ClientHandler clientThread = new ClientHandler(socket, clients);
				clients.add(clientThread);

				pool.execute(clientThread);
			}

			
		}
		catch(IOException e) {
			System.out.println(e);
		}
	}

	public static void main(String args[]) throws IOException {
		Server server = new Server(5000);
	}
}
