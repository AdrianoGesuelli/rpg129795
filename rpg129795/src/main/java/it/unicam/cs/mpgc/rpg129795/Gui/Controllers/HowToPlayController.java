package it.unicam.cs.mpgc.rpg129795.Gui.Controllers;

import it.unicam.cs.mpgc.rpg129795.Gui.Navigation.Navigator;
import it.unicam.cs.mpgc.rpg129795.Gui.Navigation.Screen;
import it.unicam.cs.mpgc.rpg129795.Gui.Presentation.TutorialBlock;
import it.unicam.cs.mpgc.rpg129795.Gui.Presentation.TutorialContent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.Region;
import javafx.scene.layout.VBox;

import java.util.List;

public final class HowToPlayController {

    private final Navigator navigator;

    @FXML
    private Label sectionTitle;
    @FXML
    private ScrollPane contentScroll;
    @FXML
    private VBox contentBox;

    public HowToPlayController(Navigator navigator) {
        this.navigator = navigator;
    }

    @FXML
    private void initialize() {
        sectionTitle.setText("Choose a section on the left.");
    }

    @FXML
    private void onActions() {
        show("Actions", TutorialContent.actions());
    }

    @FXML
    private void onDamage() {
        show("Damage types & affinities", TutorialContent.damageAndAffinities());
    }

    @FXML
    private void onClasses() {
        show("Classes & subclasses", TutorialContent.classes());
    }

    @FXML
    private void onRelics() {
        show("Relics", TutorialContent.relics());
    }

    @FXML
    private void onBack() {
        navigator.goTo(Screen.mainMenu);
    }

    @FXML
    private void onEnemies() {
        show("How enemies work", TutorialContent.enemies());
    }

    //? Mostra i blocchi della sezione, con un divisore tra uno e l'altro
    private void show(String title, List<TutorialBlock> blocks) {
        sectionTitle.setText(title);
        contentBox.getChildren().clear();

        for (int i = 0; i < blocks.size(); i++) {
            if (i > 0) {
                contentBox.getChildren().add(createDivider());
            }
            Label heading = new Label(blocks.get(i).title());
            heading.getStyleClass().add("tutorial-heading");

            Label text = new Label(blocks.get(i).text());
            text.setWrapText(true);
            text.getStyleClass().add("tutorial-text");

            contentBox.getChildren().addAll(heading, text);
        }
        contentScroll.setVvalue(0);
    }

    //? Una linea sottile: una Region vuota a cui il CSS dà altezza e colore
    private Region createDivider() {
        Region divider = new Region();
        divider.getStyleClass().add("divider");
        return divider;
    }
}
