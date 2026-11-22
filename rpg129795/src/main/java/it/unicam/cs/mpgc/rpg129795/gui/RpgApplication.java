package it.unicam.cs.mpgc.rpg129795.gui;

import it.unicam.cs.mpgc.rpg129795.game.GameFlow;
import it.unicam.cs.mpgc.rpg129795.game.GameSetup;
import it.unicam.cs.mpgc.rpg129795.gui.controllers.*;
import it.unicam.cs.mpgc.rpg129795.gui.navigation.Navigator;
import it.unicam.cs.mpgc.rpg129795.gui.navigation.SceneNavigator;
import it.unicam.cs.mpgc.rpg129795.gui.navigation.Screen;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

public class RpgApplication extends Application {

    private static final int windowWidth = 960;
    private static final int windowHeight = 640;
    public static final String styleSheet = "/css/style.css";

    public void start(Stage stage) {
        GameFlow gameFlow = GameSetup.createGameFlow();

        BorderPane root = new BorderPane();
        ControllerFactory controllerFactory = new ControllerFactory();
        Navigator navigator = new SceneNavigator(root, controllerFactory);

        controllerFactory.register(MainMenuController.class,
                () -> new MainMenuController(navigator, gameFlow));
        controllerFactory.register(CharacterCreationController.class,
                () -> new CharacterCreationController(navigator, gameFlow));
        controllerFactory.register(CombatController.class,
                () -> new CombatController(navigator, gameFlow));
        controllerFactory.register(HowToPlayController.class,
                () -> new HowToPlayController(navigator));

        navigator.goTo(Screen.mainMenu);

        stage.setTitle("RPG 129795");
        Scene scene = new Scene(root, windowWidth, windowHeight);
        scene.getStylesheets().add(getClass().getResource(styleSheet).toExternalForm());
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
