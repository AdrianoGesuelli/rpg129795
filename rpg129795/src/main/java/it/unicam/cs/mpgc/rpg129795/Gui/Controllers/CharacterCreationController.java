package it.unicam.cs.mpgc.rpg129795.Gui.Controllers;

import it.unicam.cs.mpgc.rpg129795.Gui.Navigation.Navigator;
import it.unicam.cs.mpgc.rpg129795.Gui.Navigation.Screen;
import javafx.fxml.FXML;

public final class CharacterCreationController {

    private final Navigator navigator;

    public CharacterCreationController(Navigator navigator) {
        this.navigator = navigator;
    }

    @FXML
    private void onBack() {
        navigator.goTo(Screen.mainMenu);
    }
}
