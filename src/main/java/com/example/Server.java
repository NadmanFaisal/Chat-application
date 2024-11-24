package com.example;

import java.net.*;
import java.io.*;

public class Server
{
	// Initizlizss the socket, server and input
	private Socket socket = null;
	private ServerSocket server = null;
	private DataInputStream input = null;


	public Server(int port)
	{

		try
		{
            // Creates a server and waits for connection
			server = new ServerSocket(port);
			System.out.println("Server started");

			System.out.println("Waiting for a client ...");

            // Accepts connection with client
			socket = server.accept();
			System.out.println("Client accepted");

			// Creates inputstream to get input from client
			input = new DataInputStream(
				new BufferedInputStream(socket.getInputStream()));

			String clientMessage = "";

			// Reads messages sent from the client until
			while (!clientMessage.equals("Over"))
			{
				try
				{
					clientMessage = input.readUTF();
					System.out.println(clientMessage);

				}
				catch(IOException e)
				{
					System.out.println(e);
				}
			}
			System.out.println("Closing connection");

			// Close connections to avoid leaks
			socket.close();
			input.close();
		}
		catch(IOException e)
		{
			System.out.println(e);
		}
	}

	public static void main(String args[])
	{
		Server server = new Server(5000);
	}
}
