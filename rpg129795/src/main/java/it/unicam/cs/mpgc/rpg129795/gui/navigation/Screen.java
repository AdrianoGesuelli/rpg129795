package it.unicam.cs.mpgc.rpg129795.gui.navigation;

public enum Screen {

    mainMenu("/fxml/main_menu.fxml"),
    characterCreation("/fxml/character_creation.fxml"),
    combat("/fxml/combat.fxml"),
    howToPlay("/fxml/how_to_play.fxml");

    private final String fxmlPath;

    Screen(String fxmlPath) {
        this.fxmlPath = fxmlPath;
    }

    public String getFxmlPath() {
        return fxmlPath;
    }
}
