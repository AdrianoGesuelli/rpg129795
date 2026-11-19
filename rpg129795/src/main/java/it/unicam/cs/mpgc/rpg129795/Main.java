package it.unicam.cs.mpgc.rpg129795;

import it.unicam.cs.mpgc.rpg129795.CastleMap.CastleEntrance;
import it.unicam.cs.mpgc.rpg129795.Character.CharacterBuilder;
import it.unicam.cs.mpgc.rpg129795.Character.PlayerCharacter;
import it.unicam.cs.mpgc.rpg129795.Classes.CharacterClass;
import it.unicam.cs.mpgc.rpg129795.Classes.ClassCatalog;
import it.unicam.cs.mpgc.rpg129795.Classes.Subclass;
import it.unicam.cs.mpgc.rpg129795.Combat.*;
import it.unicam.cs.mpgc.rpg129795.Combat.Resolvers.*;
import it.unicam.cs.mpgc.rpg129795.Combat.Status.StatusEffect;
import it.unicam.cs.mpgc.rpg129795.Enemies.*;
import it.unicam.cs.mpgc.rpg129795.Saves.*;

import java.util.List;
import java.util.Random;
import java.util.Scanner;

public class Main {

    public static final int entranceFloor = 0;
    public static final int lastAvaibleFloor = 1;

    public static void main(String[] args) {

        Scanner input = new Scanner(System.in);
        Random random = new Random();
        SaveManager saveManager = new SaveManager(JsonSaveStorage.inUserHome());
        EnemiesFactory enemiesFactory = new EnemiesFactory(random);
        RandomEnemySelector enemySelector = new RandomEnemySelector(random);
        AttackResolve attackResolve = new AttackResolve(new HitResolve(random), random);

        //? 1 - Continua o nuova partita
        GameProgress progress = startOrContinue(input, saveManager);
        PlayerCharacter player = progress.player();
        CastleEntrance entrance = progress.entrance();

        //? 2 - Un piano alla volta, partendo da quello salvato (0 = entrata)
        for (int floor = progress.floor(); floor <= lastAvaibleFloor; floor++) {
            Enemy enemy = createEnemy(floor, entrance, enemiesFactory, enemySelector);
            announceFloor(floor, enemy);

            if (fight(input, player, enemy, attackResolve) == CombatOutcome.DEFEAT) {
                System.out.println("You lost! Pure skill issues! :D");
                return;
            }
            System.out.println("You win! How fortuitous...");

            offerSave(input, saveManager, player, entrance, floor + 1);
        }

        //? 3 - Fine della demo
        System.out.println();
        System.out.println("=====================================================");
        System.out.println(" WORK IN PROGRESS");
        System.out.println(" The rest of the castle is still being built.");
        System.out.println(" Thank you for playing!");
        System.out.println("=====================================================");
    }

    //! Avvio della partita ---------------------

    private static GameProgress startOrContinue(Scanner input, SaveManager saveManager) {
        while (saveManager.hasSave()) {
            if (askYesNo(input, "A saved game was found. Do you want to continue it?")) {
                return continueGame(saveManager);
            }
            if (askYesNo(input, "Starting a new game will delete your saved game. Are you sure?")) {
                saveManager.deleteSave();
            }
        }
        return startNewGame(input);
    }

    private static GameProgress startNewGame(Scanner input) {
        String playerName = askName(input);
        CharacterClass chosenClass = choose(input, "Choose your class", ClassCatalog.classes());
        Subclass chosenSubclass = choose(input, "Choose your subclass", ClassCatalog.subclassesOf(chosenClass));
        PlayerCharacter player = new CharacterBuilder().withName(playerName).withClass(chosenClass).withSubclass(chosenSubclass).build();
        CastleEntrance entrance = choose(input, "Choose the entrance", CastleEntrance.all());
        System.out.println(entrance.description());

        return new GameProgress(player, entrance, entranceFloor);
    }

    private static GameProgress continueGame(SaveManager saveManager) {
        GameProgress progress = saveManager.loadProgress();
        PlayerCharacter player = progress.player();
        System.out.println("Welcome back, " + player.getName() + ". HP: " + player.getCurrentHp() + "/" + player.getStats().maxHp());

        return progress;
    }

    //! Piani e nemici ---------------------

    //? Piano 0: il guardiano dell'entrata. Dal piano 1: un nemico casuale del tier del piano
    private static Enemy createEnemy(int floor, CastleEntrance entrance,
                                     EnemiesFactory enemiesFactory, RandomEnemySelector enemySelector) {
        if (floor == entranceFloor) {
            return enemiesFactory.create(entrance.guardian());
        }
        return enemiesFactory.create(enemySelector.select(tierOf(floor)));
    }

    private static EnemyTier tierOf(int floor) {
        if (floor == 1) {
            return EnemyTier.FLOOR_1;
        }
        throw new IllegalArgumentException("No enemies defined for floor " + floor);
    }

    private static void announceFloor(int floor, Enemy enemy) {
        System.out.println();
        if (floor == entranceFloor) {
            System.out.println(enemy.getName() + " guards the entrance.");
            return;
        }
        System.out.println("=== FLOOR " + floor + " ===");
        System.out.println("A " + enemy.getName() + " blocks your way.");
    }

    //! Combattimento e salvataggio ---------------------

    //? Un combattimento completo; alla fine gli status vengono tolti
    private static CombatOutcome fight(Scanner input, PlayerCharacter player, Enemy enemy, AttackResolve attackResolve) {
        CombatSession combat = new CombatSession(player, enemy, attackResolve);

        while (combat.getOutcome() == CombatOutcome.ONGOING) {
            printStatus(player, enemy);

            //? Turno del giocatore
            CombatActions action = choose(input, "Your turn", player.getActions());
            ActionResult playerResult = combat.playerTurn(action);

            if (playerResult != null) {
                if (playerResult.isAttack()) {
                    System.out.println("You use " + playerResult.actionName() + ". "
                            + describeAttack(player.getName(), enemy.getName(), playerResult.attackResult()));
                } else {
                    System.out.println("You use " + playerResult.actionName() + ".");
                }
            }

            if (combat.getOutcome() != CombatOutcome.ONGOING) {
                break;
            }

            //? Turno del nemico
            EnemyTurnResult enemyTurn = combat.enemyTurn();
            if (enemyTurn != null) {
                System.out.println(enemy.getName() + " uses " + enemyTurn.action().name() + ". "
                        + describeAttack(enemy.getName(), player.getName(), enemyTurn.result()));
            }
        }

        player.getActiveEffects().clear();
        return combat.getOutcome();
    }

    //? Chiede se salvare e, in caso, salva
    private static void offerSave(Scanner input, SaveManager saveManager, PlayerCharacter player, CastleEntrance entrance, int nextFloor) {
        if (askYesNo(input, "Do you want to save your progress?")) {
            saveManager.saveProgress(player, entrance, nextFloor);
            System.out.println("Game saved.");
        }
    }

    //! Input ---------------------

    //? Domanda sì/no: accetta y/yes oppure n/no
    private static boolean askYesNo(Scanner input, String question) {
        System.out.println();
        System.out.println(question + " (y/n)");
        while (true) {
            System.out.print("> ");
            String line = input.nextLine().trim().toLowerCase();
            if (line.equals("y") || line.equals("yes")) {
                return true;
            }
            if (line.equals("n") || line.equals("no")) {
                return false;
            }
            System.out.println("Please answer y or n.");
        }
    }

    private static String askName(Scanner input) {
        System.out.println();
        System.out.println("So, what is your name?");
        while (true) {
            System.out.print("> ");
            String line = input.nextLine().trim();
            if (!line.isEmpty() && line.length() <= 20) {
                return line;
            }
            System.out.println("Your name shall be between 1 and 20 characters.");
        }
    }

    //? Gestione delle scelte
    private static <T> T choose(Scanner input, String title, List<T> options) {
        System.out.println();
        System.out.println(title + ":");

        for (int i = 0; i < options.size(); i++) {
            System.out.println("  " + (i + 1) + ") " + describe(options.get(i)));
        }

        while (true) {
            System.out.print("> ");
            String line = input.nextLine().trim();

            if (isNumber(line)) {
                int index = Integer.parseInt(line) - 1;
                if (index >= 0 && index < options.size()) {
                    return options.get(index);
                }
            }
            System.out.println("Invalid choice, try again.");
        }
    }

    private static boolean isNumber(String text) {
        if (text.isEmpty() || text.length() > 3) {
            return false;
        }
        for (int i = 0; i < text.length(); i++) {
            if (!Character.isDigit(text.charAt(i))) {
                return false;
            }
        }
        return true;
    }

    //! Output ---------------------

    private static String describe(Object option) {
        if (option instanceof CharacterClass characterClass) {
            return characterClass.name() + " - " + characterClass.description();
        }
        if (option instanceof Subclass subclass) {
            return subclass.name() + " - " + subclass.description();
        }
        if (option instanceof CastleEntrance entrance) {
            return entrance.name();
        }
        if (option instanceof CombatActions action) {
            return action.getName();
        }
        return option.toString();
    }

    //? Una frase che descrive l'esito di un attacco
    private static String describeAttack(String attacker, String target, AttackResult attack) {
        if (attack.result() == HitResult.MISS) {
            return target + " dodges the attack!";
        }

        String critical = "";
        if (attack.result() == HitResult.CRITICAL) {
            critical = " Critical hit!";
        }

        if (attack.heal() > 0) {
            return target + " absorbs the blow and heals " + attack.heal() + " HP!" + critical;
        }

        return attacker + " deals " + attack.damage() + " damage to " + target + "." + critical;
    }

    private static void printStatus(PlayerCharacter player, Enemy enemy) {
        System.out.println();
        System.out.println(player.getName() + " HP: " + player.getCurrentHp() + "/" + player.getStats().maxHp()
                + describeStatuses(player)
                + "   |   " + enemy.getName() + " HP: " + enemy.getCurrentHp() + "/" + enemy.getStats().maxHp()
                + describeStatuses(enemy));
    }

    //? Gli status attivi, con turni rimanenti e stack (es. " [POISON 3 turns, x2]")
    private static String describeStatuses(Combatant combatant) {
        List<StatusEffect> effects = combatant.getActiveEffects().getActive();
        if (effects.isEmpty()) {
            return "";
        }
        String text = " [";
        for (int i = 0; i < effects.size(); i++) {
            StatusEffect effect = effects.get(i);
            if (i > 0) {
                text += ", ";
            }
            text += effect.getStatusType() + " " + effect.remainingTurns() + " turns";
            if (effect.getStacks() > 1) {
                text += ", x" + effect.getStacks();
            }
        }
        return text + "]";
    }
}
