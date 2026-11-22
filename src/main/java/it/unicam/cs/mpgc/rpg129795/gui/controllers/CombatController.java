package it.unicam.cs.mpgc.rpg129795.gui.controllers;

import it.unicam.cs.mpgc.rpg129795.character.PlayerCharacter;
import it.unicam.cs.mpgc.rpg129795.combat.CombatAction;
import it.unicam.cs.mpgc.rpg129795.combat.CombatOutcome;
import it.unicam.cs.mpgc.rpg129795.combat.EnemyTurnResult;
import it.unicam.cs.mpgc.rpg129795.enemies.Enemy;
import it.unicam.cs.mpgc.rpg129795.enemies.Observable;
import it.unicam.cs.mpgc.rpg129795.game.GameFlow;
import it.unicam.cs.mpgc.rpg129795.game.TurnReport;
import it.unicam.cs.mpgc.rpg129795.gui.navigation.Navigator;
import it.unicam.cs.mpgc.rpg129795.gui.navigation.Screen;
import it.unicam.cs.mpgc.rpg129795.gui.presentation.CombatMessages;
import it.unicam.cs.mpgc.rpg129795.gui.presentation.CombatantView;
import it.unicam.cs.mpgc.rpg129795.gui.RpgApplication;
import it.unicam.cs.mpgc.rpg129795.items.relics.Relic;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

import java.util.List;

public final class CombatController {

    //? Classi di stile del registro (definite in style.css)
    private static final String logEntry = "log-entry";
    private static final String playerStyle = "log-player";
    private static final String enemyStyle = "log-enemy";
    private static final String narratorStyle = "log-narrator";
    private static final String victoryStyle = "log-victory";
    private static final String defeatStyle = "log-defeat";
    private static final String clueStyle = "log-clue";
    private static final String turnStyle = "log-turn";
    private static final String saveStyle = "log-save";
    private static final String separator = "────────────────────────────";

    private final Navigator navigator;
    private final GameFlow gameFlow;
    private int turn = 0;
    private static final String omenStyle = "log-omen";

    @FXML
    private Label floorLabel;
    @FXML
    private Label playerNameLabel;
    @FXML
    private ProgressBar playerHpBar;
    @FXML
    private Label playerHpLabel;
    @FXML
    private Label playerStatusLabel;
    @FXML
    private Label enemyNameLabel;
    @FXML
    private ProgressBar enemyHpBar;
    @FXML
    private Label enemyHpLabel;
    @FXML
    private Label enemyStatusLabel;
    @FXML
    private ScrollPane logScroll;
    @FXML
    private VBox logBox;
    @FXML
    private Label cooldownLabel;
    @FXML
    private HBox actionBox;

    public CombatController(Navigator navigator, GameFlow gameFlow) {
        this.navigator = navigator;
        this.gameFlow = gameFlow;
    }

    @FXML
    private void initialize() {
        //? Ogni volta che il registro cresce, scorre fino all'ultimo messaggio
        logBox.heightProperty().addListener((observable, oldHeight, newHeight) -> logScroll.setVvalue(1.0));

        floorLabel.setText(floorTitle());
        gameFlow.startFight();
        log(gameFlow.getEnemy().getName() + " blocks your way.", narratorStyle);
        revealWithRelic();
        refresh();
        showActions();
    }

    //? Watcher's Eye: la descrizione del nemico compare subito, senza spendere il turno
    private void revealWithRelic() {
        Relic relic = gameFlow.getPlayer().getRelic();
        if (relic.revealsEnemies() && gameFlow.getEnemy() instanceof Observable observable) {
            log(relic.name() + " reveals what lies before you.", narratorStyle);
            log(observable.getObservation(), clueStyle);
        }
    }

    //! Turni ---------------------

    private void onActionChosen(CombatAction action) {
        turn++;
        log("Turn " + turn + "  " + separator, turnStyle);

        TurnReport report = gameFlow.playTurn(action);
        PlayerCharacter player = gameFlow.getPlayer();
        Enemy enemy = gameFlow.getEnemy();

        if (report.playerResult() != null) {
            log(CombatMessages.playerTurn(player.getName(), enemy.getName(), report.playerResult()), playerStyle);
            if (report.playerResult().hasInsight()) {
                log(report.playerResult().insight(), clueStyle);
            }
        }
        EnemyTurnResult enemyResult = report.enemyResult();
        if (enemyResult != null) {
            log(CombatMessages.enemyTurn(enemy.getName(), player.getName(), enemyResult), enemyStyle);
            if (enemyResult.curseIncoming()) {
                log(separator, omenStyle);
                log(CombatMessages.omen(), omenStyle);
                log(separator, omenStyle);
            }
        }
        refresh();

        if (report.outcome() == CombatOutcome.ONGOING) {
            showActions();
        } else if (report.outcome() == CombatOutcome.VICTORY) {
            onVictory();
        } else {
            onDefeat();
        }
    }

    private void showActions() {
        actionBox.getChildren().clear();
        List<CombatAction> ready = gameFlow.getPlayer().getReadyActions();
        for (int i = 0; i < ready.size(); i++) {
            CombatAction action = ready.get(i);
            Button button = createButton(action.getName());
            button.setOnAction(e -> onActionChosen(action));
            actionBox.getChildren().add(button);
        }
        cooldownLabel.setText(describeCooldowns());
    }

    //! Fine combattimento ---------------------

    private void onVictory() {
        log("You win! How fortuitous...", victoryStyle);
        actionBox.getChildren().clear();
        cooldownLabel.setText("");

        if (gameFlow.isDemoFinished()) {
            log("WORK IN PROGRESS - The rest of the castle is still being built. Thank you for playing!", narratorStyle);
            Button menuButton = createButton("Main menu");
            menuButton.setOnAction(e -> navigator.goTo(Screen.mainMenu));
            actionBox.getChildren().add(menuButton);
            return;
        }

        askToSave();
        Button nextButton = createButton("Next floor");
        nextButton.setOnAction(e -> navigator.goTo(Screen.combat));
        actionBox.getChildren().add(nextButton);
    }

    private void onDefeat() {
        log("You lost! Pure skill issues! :D", defeatStyle);
        actionBox.getChildren().clear();
        cooldownLabel.setText("");
        Button menuButton = createButton("Main menu");
        menuButton.setOnAction(e -> navigator.goTo(Screen.mainMenu));
        actionBox.getChildren().add(menuButton);
    }

    //? Torna al menu senza salvare, dopo una conferma
    @FXML
    private void onMainMenu() {
        Alert alert = createAlert("Main menu",
                "Return to the main menu? Any progress since your last save will be lost.");
        alert.showAndWait();
        if (alert.getResult() == ButtonType.YES) {
            navigator.goTo(Screen.mainMenu);
        }
    }

    private void askToSave() {
        Alert alert = createAlert("Save", "Do you want to save your progress?");
        alert.showAndWait();
        if (alert.getResult() == ButtonType.YES) {
            gameFlow.saveProgress();
            log(separator, saveStyle);
            log("Game saved.", saveStyle);
            log(separator, saveStyle);
        }
    }

    //! Aggiornamento della schermata ---------------------

    private void refresh() {
        show(CombatantView.of(gameFlow.getPlayer()), playerNameLabel, playerHpBar, playerHpLabel, playerStatusLabel);
        show(CombatantView.of(gameFlow.getEnemy()), enemyNameLabel, enemyHpBar, enemyHpLabel, enemyStatusLabel);
    }

    private void show(CombatantView view, Label nameLabel, ProgressBar hpBar, Label hpLabel, Label statusLabel) {
        nameLabel.setText(view.name());
        hpBar.setProgress(view.hpRatio());
        hpLabel.setText("HP " + view.currentHp() + "/" + view.maxHp());
        statusLabel.setText(view.statuses());
    }

    private String floorTitle() {
        if (gameFlow.getFloor() == GameFlow.entranceFloor) {
            return gameFlow.getEntrance().name();
        }
        return "Floor " + gameFlow.getFloor();
    }

    private String describeCooldowns() {
        List<CombatAction> actions = gameFlow.getPlayer().getActions();
        String text = "";
        for (int i = 0; i < actions.size(); i++) {
            CombatAction action = actions.get(i);
            if (action.getCooldownLeft() > 0) {
                String turns = " turns";
                if (action.getCooldownLeft() == 1) {
                    turns = " turn";
                }
                text += "Recharging: " + action.getName() + " (" + action.getCooldownLeft() + turns + ")  ";
            }
        }
        return text;
    }

    //? Aggiunge una riga al registro con il suo stile (chi parla decide il colore)
    private void log(String message, String styleClass) {
        Label entry = new Label(message);
        entry.setWrapText(true);
        entry.getStyleClass().addAll(logEntry, styleClass);
        logBox.getChildren().add(entry);
    }

    private Button createButton(String text) {
        Button button = new Button(text);
        button.setPrefWidth(140);
        return button;
    }

    //? Finestra di conferma Sì/No con lo stile del gioco
    private Alert createAlert(String title, String message) {
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION, message, ButtonType.YES, ButtonType.NO);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setGraphic(null);
        alert.getDialogPane().getStylesheets().add(getClass().getResource(RpgApplication.styleSheet).toExternalForm());
        return alert;
    }
}
