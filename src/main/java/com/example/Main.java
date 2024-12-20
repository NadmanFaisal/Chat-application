package com.example;

import java.io.IOException;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class Main extends Application {

	@Override
    public void start(Stage stage) throws IOException {
		System.out.println(Main.class.getResource("UI/homeScreen.fxml"));
        FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("UI/homeScreen.fxml"));
		Scene scene = new Scene(fxmlLoader.load());
		stage.setTitle("Hello!");
		stage.setScene(scene);
		stage.show();
    }
    public static void main(String args[]) {
		launch();
	}
}
