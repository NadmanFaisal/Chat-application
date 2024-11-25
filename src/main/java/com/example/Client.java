package com.example;

import java.io.*;
import java.net.*;
import java.util.Scanner;

public class Client {
	// initialize socket and input output
	private Socket socket;
	private Scanner input;
	private DataOutputStream out;


	public Client(String address, int port) {

		try {

			// Establishes connection using the address and port
			socket = new Socket(address, port);
			System.out.println("Connected");

			// Creates input stream to gather user inputs
			input = new Scanner(System.in);

			// Creates the output stream to send user inputs to the socket
			out = new DataOutputStream(
				socket.getOutputStream());
		}
		catch (UnknownHostException u) {
			System.out.println(u);
			return;
		}
		catch (IOException e) {
			System.out.println(e);
			return;
		}

		// variable to store user input
		String message = "";

		// keep reading until "Over" is input
		while (!message.equals("Over")) {
			try {
				message = input.nextLine();

				// Sends the input to the socket
				out.writeUTF(message);
			}
			catch (IOException e) {
				System.out.println(e);
			}
		}

		// Close the connections to avoid leaks
		try {
			input.close();
			out.close();
			socket.close();
		}
		catch (IOException e) {
			System.out.println(e);
		}
	}

	public static void main(String args[])
	{
		Client client = new Client("127.0.0.1", 5000);
	}
}
