package it.unicam.cs.mpgc.rpg129795.Gui;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.layout.StackPane;
import javafx.scene.text.Text;
import javafx.stage.Stage;

public class RpgApplication extends Application {

    private static final int windowWidth = 960;
    private static final int windowHeight = 640;

    public void start(Stage stage) {
        Text title = new Text("The Demon King's Castle");
        Scene scene = new Scene(new StackPane(title), windowWidth, windowHeight);

        stage.setTitle("RPG 129795");
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
