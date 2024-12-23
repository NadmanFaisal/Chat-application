package com.example.Backend;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.function.Consumer;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Client {
    private static final Logger logger = Logger.getLogger(Client.class.getName());

    private String name;
    private Socket socket;
    private DataOutputStream out;
    private DataInputStream in;
    private final ExecutorService executor = Executors.newSingleThreadExecutor();
	private Consumer<String> messageListener;

    public Client(String name, String address, int port) throws IOException {
        this.name = name;
        connectToServer(address, port);
    }

    private void connectToServer(String address, int port) throws IOException {
        try {
            socket = new Socket(address, port);
            logger.info("Connected to server at " + address + ":" + port);

            // Initialize I/O streams
            out = new DataOutputStream(socket.getOutputStream());
            in = new DataInputStream(socket.getInputStream());

            startListening();
        } catch (UnknownHostException e) {
            throw new IOException("Unknown host: " + address, e);
        } catch (IOException e) {
            throw new IOException("Could not establish connection to " + address + ":" + port, e);
        }
    }

	public void setOnMessageReceived(Consumer<String> listener) {
		this.messageListener = listener;
	}

    // Listens for messages from other client through server
    private void startListening() {
        executor.submit(() -> {
            try {
                while (!socket.isClosed()) {
                    String serverMessage = in.readUTF();
                    logger.info("Message from server: " + serverMessage);

					if (messageListener != null) {
                        messageListener.accept(serverMessage);
                    }
                }
            } catch (IOException e) {
                logger.warning("Connection to server lost: " + e.getMessage());
                close();
            }
        });
    }

    // Send message to other clients through server
    public void sendMessage(String message) {
        try {
            synchronized (out) {
                out.writeUTF(this.name + ": " + message);
                out.flush();
            }
        } catch (IOException e) {
            logger.warning("Failed to send message: " + e.getMessage());
        }
    }

    // Close all resources
    public void close() {
        try {
            if (socket != null && !socket.isClosed()) {
                socket.close();
                logger.info("Client connection closed.");
            }
            executor.shutdownNow();
        } catch (IOException e) {
            logger.log(Level.WARNING, "Error closing client resources", e);
        }
    }

    public String getName() {
        return name;
    }
}
