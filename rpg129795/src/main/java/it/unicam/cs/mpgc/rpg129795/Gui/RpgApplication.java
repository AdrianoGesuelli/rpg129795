package it.unicam.cs.mpgc.rpg129795.Gui;

import it.unicam.cs.mpgc.rpg129795.Gui.Controllers.CharacterCreationController;
import it.unicam.cs.mpgc.rpg129795.Gui.Controllers.ControllerFactory;
import it.unicam.cs.mpgc.rpg129795.Gui.Controllers.MainMenuController;
import it.unicam.cs.mpgc.rpg129795.Gui.Navigation.Navigator;
import it.unicam.cs.mpgc.rpg129795.Gui.Navigation.SceneNavigator;
import it.unicam.cs.mpgc.rpg129795.Gui.Navigation.Screen;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

public class RpgApplication extends Application {

    private static final int windowWidth = 960;
    private static final int windowHeight = 640;

    public void start(Stage stage) {
        GameServices services = GameServices.createDefault();

        BorderPane root = new BorderPane();
        ControllerFactory controllerFactory = new ControllerFactory();
        Navigator navigator = new SceneNavigator(root, controllerFactory);

        controllerFactory.register(MainMenuController.class,
                () -> new MainMenuController(navigator, services));
        controllerFactory.register(CharacterCreationController.class,
                () -> new CharacterCreationController(navigator));

        navigator.goTo(Screen.mainMenu);

        stage.setTitle("RPG 129795");
        stage.setScene(new Scene(root, windowWidth, windowHeight));
        stage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
