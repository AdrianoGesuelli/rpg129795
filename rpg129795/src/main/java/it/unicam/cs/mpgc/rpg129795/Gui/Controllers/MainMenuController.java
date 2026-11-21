package it.unicam.cs.mpgc.rpg129795.Gui.Controllers;

import it.unicam.cs.mpgc.rpg129795.Gui.GameServices;
import it.unicam.cs.mpgc.rpg129795.Gui.Navigation.Navigator;
import it.unicam.cs.mpgc.rpg129795.Gui.Navigation.Screen;
import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.scene.control.Button;

public final class MainMenuController {

    private final Navigator navigator;
    private final GameServices services;

    @FXML
    private Button continueButton;

    public MainMenuController(Navigator navigator, GameServices services) {
        this.navigator = navigator;
        this.services = services;
    }

    //? Chiamato da JavaFX appena gli elementi dell'FXML sono collegati
    @FXML
    private void initialize() {
        continueButton.setDisable(!services.gameFlow().hasSave());
    }

    @FXML
    private void onNewGame() {
        navigator.goTo(Screen.characterCreation);
    }

    @FXML
    private void onContinue() {
        //TODO: caricare la partita quando ci sarà la schermata di combattimento
    }

    @FXML
    private void onQuit() {
        Platform.exit();
    }
}
