package it.unicam.cs.mpgc.rpg129795.Game;

import it.unicam.cs.mpgc.rpg129795.CastleMap.CastleEntrance;
import it.unicam.cs.mpgc.rpg129795.Character.CharacterBuilder;
import it.unicam.cs.mpgc.rpg129795.Character.PlayerCharacter;
import it.unicam.cs.mpgc.rpg129795.Classes.CharacterClass;
import it.unicam.cs.mpgc.rpg129795.Classes.Subclass;
import it.unicam.cs.mpgc.rpg129795.Combat.CombatActions;
import it.unicam.cs.mpgc.rpg129795.Combat.CombatOutcome;
import it.unicam.cs.mpgc.rpg129795.Combat.CombatSession;
import it.unicam.cs.mpgc.rpg129795.Combat.EnemyTurnResult;
import it.unicam.cs.mpgc.rpg129795.Combat.Resolvers.ActionResult;
import it.unicam.cs.mpgc.rpg129795.Combat.Resolvers.AttackResolve;
import it.unicam.cs.mpgc.rpg129795.Enemies.EnemiesFactory;
import it.unicam.cs.mpgc.rpg129795.Enemies.Enemy;
import it.unicam.cs.mpgc.rpg129795.Enemies.EnemyTier;
import it.unicam.cs.mpgc.rpg129795.Enemies.RandomEnemySelector;
import it.unicam.cs.mpgc.rpg129795.Items.Relic.Relic;
import it.unicam.cs.mpgc.rpg129795.Saves.GameProgress;
import it.unicam.cs.mpgc.rpg129795.Saves.SaveManager;

public final class GameFlow {

    public static final int entranceFloor = 0;
    public static final int lastAvailableFloor = 1;
    private final SaveManager saveManager;
    private final EnemiesFactory enemiesFactory;
    private final RandomEnemySelector enemySelector;
    private final AttackResolve attackResolve;
    private PlayerCharacter player;
    private CastleEntrance entrance;
    private int floor;
    private Enemy enemy;
    private CombatSession combat;

    public GameFlow(SaveManager saveManager, EnemiesFactory enemiesFactory, RandomEnemySelector enemySelector, AttackResolve attackResolve) {
        if (saveManager == null || enemiesFactory == null || enemySelector == null || attackResolve == null) {
            throw new IllegalArgumentException("Game flow cannot have null services");
        }
        this.saveManager = saveManager;
        this.enemiesFactory = enemiesFactory;
        this.enemySelector = enemySelector;
        this.attackResolve = attackResolve;
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
            enemy = enemiesFactory.create(entrance.guardian());
        } else {
            enemy = enemiesFactory.create(enemySelector.select(tierOf(floor)));
        }
        combat = new CombatSession(player, enemy, attackResolve);
    }

    //? Un turno completo: prima il giocatore, poi il nemico (se il combattimento continua)
    public TurnReport playTurn(CombatActions action) {
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
