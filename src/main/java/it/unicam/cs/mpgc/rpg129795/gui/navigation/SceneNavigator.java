package it.unicam.cs.mpgc.rpg129795.gui.navigation;

import it.unicam.cs.mpgc.rpg129795.gui.controllers.ControllerFactory;
import javafx.fxml.FXMLLoader;
import javafx.scene.layout.BorderPane;

import java.io.IOException;

public class SceneNavigator implements Navigator {

    private final BorderPane root;
    private final ControllerFactory controllerFactory;

    public SceneNavigator(BorderPane root, ControllerFactory controllerFactory) {
        if (root == null || controllerFactory == null) {
            throw new IllegalArgumentException("Root and controller factory cannot be null");
        }
        this.root = root;
        this.controllerFactory = controllerFactory;
    }

    public void goTo(Screen screen) {
        FXMLLoader loader = new FXMLLoader(getClass().getResource(screen.getFxmlPath()));
        loader.setControllerFactory(controllerFactory);
        try {
            root.setCenter(loader.load());
        } catch (IOException e) {
            throw new IllegalStateException("Could not load screen " + screen, e);
        }
    }

}
