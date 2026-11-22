package it.unicam.cs.mpgc.rpg129795.game;

import it.unicam.cs.mpgc.rpg129795.character.CharacterBuilder;
import it.unicam.cs.mpgc.rpg129795.character.PlayerCharacter;
import it.unicam.cs.mpgc.rpg129795.character.classes.CharacterClass;
import it.unicam.cs.mpgc.rpg129795.character.classes.Subclass;
import it.unicam.cs.mpgc.rpg129795.combat.CombatAction;
import it.unicam.cs.mpgc.rpg129795.combat.CombatOutcome;
import it.unicam.cs.mpgc.rpg129795.combat.CombatSession;
import it.unicam.cs.mpgc.rpg129795.combat.EnemyTurnResult;
import it.unicam.cs.mpgc.rpg129795.combat.resolvers.ActionResult;
import it.unicam.cs.mpgc.rpg129795.combat.resolvers.AttackResolver;
import it.unicam.cs.mpgc.rpg129795.enemies.EnemyFactory;
import it.unicam.cs.mpgc.rpg129795.enemies.Enemy;
import it.unicam.cs.mpgc.rpg129795.enemies.EnemyTier;
import it.unicam.cs.mpgc.rpg129795.enemies.RandomEnemySelector;
import it.unicam.cs.mpgc.rpg129795.items.relics.Relic;
import it.unicam.cs.mpgc.rpg129795.saves.GameProgress;
import it.unicam.cs.mpgc.rpg129795.saves.SaveManager;

public final class GameFlow {

    public static final int entranceFloor = 0;
    public static final int lastAvailableFloor = 1;
    private final SaveManager saveManager;
    private final EnemyFactory enemyFactory;
    private final RandomEnemySelector enemySelector;
    private final AttackResolver attackResolver;
    private PlayerCharacter player;
    private CastleEntrance entrance;
    private int floor;
    private Enemy enemy;
    private CombatSession combat;

    public GameFlow(SaveManager saveManager, EnemyFactory enemyFactory, RandomEnemySelector enemySelector, AttackResolver attackResolver) {
        if (saveManager == null || enemyFactory == null || enemySelector == null || attackResolver == null) {
            throw new IllegalArgumentException("Game flow cannot have null services");
        }
        this.saveManager = saveManager;
        this.enemyFactory = enemyFactory;
        this.enemySelector = enemySelector;
        this.attackResolver = attackResolver;
    }

    //! Inizio partita ---------------------

    public void startNewGame(String name, CharacterClass characterClass, Subclass subclass,
                             Relic relic, CastleEntrance chosenEntrance) {
        player = new CharacterBuilder()
                .withName(name)
                .withClass(characterClass)
                .withSubclass(subclass)
                .withRelic(relic)
                .build();
        entrance = chosenEntrance;
        floor = entranceFloor;
    }

    public void continueGame() {
        GameProgress progress = saveManager.loadProgress();
        player = progress.player();
        entrance = progress.entrance();
        floor = progress.floor();
    }

    public boolean hasSave() {
        return saveManager.hasSave();
    }

    //! Combattimento ---------------------

    public boolean isDemoFinished() {
        return floor > lastAvailableFloor;
    }

    //? Prepara il nemico del piano attuale: il guardiano al piano 0, uno casuale dal piano 1
    public void startFight() {
        if (isDemoFinished()) {
            throw new IllegalStateException("There are no more floors to fight on");
        }
        if (floor == entranceFloor) {
            enemy = enemyFactory.create(entrance.guardian());
        } else {
            enemy = enemyFactory.create(enemySelector.select(tierOf(floor)));
        }
        combat = new CombatSession(player, enemy, attackResolver);
    }

    //? Un turno completo: prima il giocatore, poi il nemico (se il combattimento continua)
    public TurnReport playTurn(CombatAction action) {
        ActionResult playerResult = combat.playerTurn(action);
        EnemyTurnResult enemyResult = null;

        if (combat.getOutcome() == CombatOutcome.ONGOING) {
            enemyResult = combat.enemyTurn();
        }
        if (combat.getOutcome() != CombatOutcome.ONGOING) {
            endFight();
        }
        return new TurnReport(playerResult, enemyResult, combat.getOutcome());
    }

    //? A fine combattimento: via status e ricariche; se hai vinto, si passa al piano successivo
    private void endFight() {
        player.getActiveEffects().clear();
        player.resetCooldowns();
        if (combat.getOutcome() == CombatOutcome.VICTORY) {
            floor++;
        }
    }

    //? Salva il piano successivo (floor è già stato aumentato dalla vittoria)
    public void saveProgress() {
        saveManager.saveProgress(player, entrance, floor);
    }

    private EnemyTier tierOf(int floorNumber) {
        if (floorNumber == 1) {
            return EnemyTier.FLOOR_1;
        }
        throw new IllegalArgumentException("No enemies defined for floor " + floorNumber);
    }

    //! Lettura dello stato ---------------------

    public PlayerCharacter getPlayer() {
        return player;
    }

    public Enemy getEnemy() {
        return enemy;
    }

    public CastleEntrance getEntrance() {
        return entrance;
    }

    public int getFloor() {
        return floor;
    }
}
