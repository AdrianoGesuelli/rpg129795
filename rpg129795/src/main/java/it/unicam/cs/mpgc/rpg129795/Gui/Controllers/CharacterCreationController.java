package it.unicam.cs.mpgc.rpg129795.Gui.Controllers;

import it.unicam.cs.mpgc.rpg129795.CastleMap.CastleEntrance;
import it.unicam.cs.mpgc.rpg129795.Character.CharacterBuilder;
import it.unicam.cs.mpgc.rpg129795.Classes.CharacterClass;
import it.unicam.cs.mpgc.rpg129795.Classes.ClassCatalog;
import it.unicam.cs.mpgc.rpg129795.Classes.Subclass;
import it.unicam.cs.mpgc.rpg129795.Game.GameFlow;
import it.unicam.cs.mpgc.rpg129795.Gui.Navigation.Navigator;
import it.unicam.cs.mpgc.rpg129795.Gui.Navigation.Screen;
import javafx.fxml.FXML;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

import java.util.List;

public final class CharacterCreationController {

    private final Navigator navigator;
    private final GameFlow gameFlow;
    private List<CharacterClass> classes;
    private List<Subclass> subclasses = List.of();
    private List<CastleEntrance> entrances;

    @FXML
    private TextField nameField;
    @FXML
    private ComboBox<String> classBox;
    @FXML
    private ComboBox<String> subclassBox;
    @FXML
    private ComboBox<String> entranceBox;
    @FXML
    private Label descriptionLabel;
    @FXML
    private Label errorLabel;

    public CharacterCreationController(Navigator navigator, GameFlow gameFlow) {
        this.navigator = navigator;
        this.gameFlow = gameFlow;
    }

    @FXML
    private void initialize() {
        classes = ClassCatalog.classes();
        entrances = CastleEntrance.all();
        for (int i = 0; i < classes.size(); i++) {
            classBox.getItems().add(classes.get(i).name());
        }
        for (int i = 0; i < entrances.size(); i++) {
            entranceBox.getItems().add(entrances.get(i).name());
        }
    }

    //? Scelta la classe, si caricano le sue sottoclassi
    @FXML
    private void onClassSelected() {
        int index = classBox.getSelectionModel().getSelectedIndex();
        if (index < 0) {
            return;
        }
        CharacterClass chosen = classes.get(index);
        subclasses = ClassCatalog.subclassesOf(chosen);
        subclassBox.getItems().clear();
        for (int i = 0; i < subclasses.size(); i++) {
            subclassBox.getItems().add(subclasses.get(i).name());
        }
        descriptionLabel.setText(chosen.description());
    }

    @FXML
    private void onSubclassSelected() {
        int index = subclassBox.getSelectionModel().getSelectedIndex();
        if (index >= 0) {
            descriptionLabel.setText(subclasses.get(index).description());
        }
    }

    @FXML
    private void onEntranceSelected() {
        int index = entranceBox.getSelectionModel().getSelectedIndex();
        if (index >= 0) {
            descriptionLabel.setText(entrances.get(index).description());
        }
    }

    @FXML
    private void onStart() {
        String name = nameField.getText().trim();
        int classIndex = classBox.getSelectionModel().getSelectedIndex();
        int subclassIndex = subclassBox.getSelectionModel().getSelectedIndex();
        int entranceIndex = entranceBox.getSelectionModel().getSelectedIndex();

        if (name.isEmpty() || name.length() > CharacterBuilder.maxNameLength) {
            errorLabel.setText("Your name shall be between 1 and " + CharacterBuilder.maxNameLength + " characters.");
            return;
        }
        if (classIndex < 0 || subclassIndex < 0 || entranceIndex < 0) {
            errorLabel.setText("Choose a class, a subclass and an entrance.");
            return;
        }

        gameFlow.startNewGame(name, classes.get(classIndex), subclasses.get(subclassIndex), entrances.get(entranceIndex));

        //TODO: andare alla schermata di combattimento (fase 4)
        errorLabel.setText(name + " is ready to enter: " + gameFlow.getEntrance().name() + ".");
    }

    @FXML
    private void onBack() {
        navigator.goTo(Screen.mainMenu);
    }
}
