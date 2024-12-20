package com.example;

import java.io.IOException;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class HelloFX extends Application {

	@Override
    public void start(Stage stage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(HelloFX.class.getResource("UI/homeScreen.fxml"));
		Scene scene = new Scene(fxmlLoader.load());
		stage.setTitle("Hello!");
		stage.setScene(scene);
		stage.show();
    }
    public static void main(String args[]) {

		launch();
		// String name = "Faisal";
		// Client client = new Client(name, "127.0.0.1", 5000);
	}
}
