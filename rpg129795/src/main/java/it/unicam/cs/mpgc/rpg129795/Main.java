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
import it.unicam.cs.mpgc.rpg129795.Saves.JsonSaveStorage;
import it.unicam.cs.mpgc.rpg129795.Saves.SaveData;
import it.unicam.cs.mpgc.rpg129795.Saves.SaveManager;
import it.unicam.cs.mpgc.rpg129795.Saves.SaveStorage;

import java.util.List;
import java.util.Random;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {

        Scanner input = new Scanner(System.in);
        Random random = new Random();

        //? 1 - Classe e sottoclasse
        String playerName = askName(input);
        CharacterClass choosenClass = choose(input, "Choose your class", ClassCatalog.classes());
        Subclass choosenSubclass = choose(input, "Choose your subclass", ClassCatalog.subclassesOf(choosenClass));
        PlayerCharacter player = new CharacterBuilder().withName(playerName).withClass(choosenClass).withSubclass(choosenSubclass).build();

        //? 2 - Entrata e guardiano
        CastleEntrance entrance = choose(input, "Choose the entrance", CastleEntrance.all());
        System.out.println(entrance.description());
        Enemy enemy = new EnemiesFactory(random).create(entrance.guardian());

        //? 3 - Combattimento
        AttackResolve attackResolve = new AttackResolve(new HitResolve(random), random);
        if (fight(input, player, enemy, attackResolve) == CombatOutcome.DEFEAT) {
            System.out.println("You lost! Pure skill issues! :D");
            return;
        }
        System.out.println("You win! How fortuitous...");

        //? 4 - salvataggio
        SaveManager saveManager = new SaveManager(JsonSaveStorage.inUserHome());
        offerSave(input, saveManager, player, entrance, 1);

        //? 5 - Piano 1: nemico casuale
        System.out.println();
        System.out.println("=== FLOOR 1 ===");
        EnemyTemplate floorTemplate = new RandomEnemySelector(random).select(EnemyTier.FLOOR_1);
        Enemy floorEnemy = new EnemiesFactory(random).create(floorTemplate);
        System.out.println("A " + floorEnemy.getName() + " blocks your way.");

        if (fight(input, player, floorEnemy, attackResolve) == CombatOutcome.DEFEAT) {
            System.out.println("You lost! Just as I thought.");
            return;
        }
        System.out.println("You won again? How nice...");



        //? 6 - Fine della demo
        System.out.println();
        System.out.println("=====================================================");
        System.out.println(" WORK IN PROGRESS");
        System.out.println(" The rest of the castle is still being built.");
        System.out.println(" Thank you for playing!");
        System.out.println("=====================================================");
    }



    //! Metodi di appoggio ---------------------

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
        System.out.println(title + " : ");

        for (int i = 0; i < options.size(); i++) {
            System.out.println("  " + (i + 1) + ") " + describe(options.get(i)));
        }

        while (true) {
            System.out.print("> ");
            String line = input.nextLine();

            if (isNumber(line)) {
                int index = Integer.parseInt(line) - 1;
                if (index >= 0 && index < options.size()) {
                    return options.get(index);
                }
            }

            System.out.println("Invalid choice, try again.");
        }
    }

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
    private static void offerSave(Scanner input, SaveManager saveManager,
                                  PlayerCharacter player, CastleEntrance entrance, int nextFloor) {
        if (askYesNo(input, "Do you want to save your progress?")) {
            saveManager.saveProgress(player, entrance, nextFloor);
            System.out.println("Game saved.");
        }
    }

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
