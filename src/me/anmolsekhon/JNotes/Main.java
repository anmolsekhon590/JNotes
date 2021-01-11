package me.anmolsekhon.JNotes;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class Main extends Application {
    public static void main(String[] args) {
        Application.launch(args);
    }

    @Override
    public void start(Stage stage) throws Exception {
        Parent root = FXMLLoader.load(getClass().getResource("FXML/JNotes.fxml"));
        Scene scene = new Scene(root);
        scene.getStylesheets().add(getClass().getResource("css/JNotes.css").toExternalForm());
        stage.setScene(scene);
        stage.setResizable(false);
        stage.show();
    }
}
