package it.unicam.cs.mpgc.rpg129795.Gui.Navigation;

public enum Screen {

    mainMenu("/fxml/main_menu.fxml"),
    characterCreation("/fxml/character_creation.fxml");

    private final String fxmlPath;

    Screen(String fxmlPath) {
        this.fxmlPath = fxmlPath;
    }

    public String getFxmlPath() {
        return fxmlPath;
    }
}
