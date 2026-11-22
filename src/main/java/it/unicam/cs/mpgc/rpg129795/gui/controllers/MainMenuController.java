package it.unicam.cs.mpgc.rpg129795.gui.controllers;

import it.unicam.cs.mpgc.rpg129795.game.GameFlow;
import it.unicam.cs.mpgc.rpg129795.gui.navigation.Navigator;
import it.unicam.cs.mpgc.rpg129795.gui.navigation.Screen;
import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.scene.control.Button;

public final class MainMenuController {

    private final Navigator navigator;
    private final GameFlow gameFlow;

    @FXML
    private Button continueButton;

    public MainMenuController(Navigator navigator, GameFlow gameFlow) {
        this.navigator = navigator;
        this.gameFlow = gameFlow;
    }

    //? Chiamato da JavaFX appena gli elementi dell'FXML sono collegati
    @FXML
    private void initialize() {
        continueButton.setDisable(!gameFlow.hasSave());
    }

    @FXML
    private void onNewGame() {
        navigator.goTo(Screen.characterCreation);
    }

    @FXML
    private void onContinue() {
        gameFlow.continueGame();
        navigator.goTo(Screen.combat);
    }

    @FXML
    private void onHowToPlay() {
        navigator.goTo(Screen.howToPlay);
    }

    @FXML
    private void onQuit() {
        Platform.exit();
    }
}
