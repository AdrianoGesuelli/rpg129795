package it.unicam.cs.mpgc.rpg129795;

import it.unicam.cs.mpgc.rpg129795.CastleMap.CastleEntrance;
import it.unicam.cs.mpgc.rpg129795.Character.CharacterBuilder;
import it.unicam.cs.mpgc.rpg129795.Character.PlayerCharacter;
import it.unicam.cs.mpgc.rpg129795.Classes.CharacterClass;
import it.unicam.cs.mpgc.rpg129795.Classes.ClassCatalog;
import it.unicam.cs.mpgc.rpg129795.Classes.Subclass;
import it.unicam.cs.mpgc.rpg129795.Combat.CombatActions;
import it.unicam.cs.mpgc.rpg129795.Combat.CombatOutcome;
import it.unicam.cs.mpgc.rpg129795.Combat.CombatSession;
import it.unicam.cs.mpgc.rpg129795.Combat.EnemyTurnResult;
import it.unicam.cs.mpgc.rpg129795.Combat.Resolvers.AttackResolve;
import it.unicam.cs.mpgc.rpg129795.Combat.Resolvers.HitResolve;
import it.unicam.cs.mpgc.rpg129795.Enemies.EnemiesFactory;
import it.unicam.cs.mpgc.rpg129795.Enemies.Enemy;

import java.util.List;
import java.util.Random;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {

        Scanner input = new Scanner(System.in);
        Random random = new Random();

        //? 1 - Classe e sottoclasse
        CharacterClass choosenClass = choose(input, "Choose your class", ClassCatalog.classes());
        Subclass choosenSubclass = choose(input, "Choose your subclass", ClassCatalog.subclassesOf(choosenClass));
        PlayerCharacter player = new CharacterBuilder().withClass(choosenClass).withSubclass(choosenSubclass).build();

        //? 2 - Entrata e guardiano
        CastleEntrance entrance = choose(input, "Choose the entrance", CastleEntrance.all());
        System.out.println(entrance.description());
        Enemy enemy = new EnemiesFactory(random).create(entrance.guardian());

        //? 3 - Combattimento
        AttackResolve attackResolve = new AttackResolve(new HitResolve(random), random);
        CombatSession combat = new CombatSession(player, enemy, attackResolve);

        while (combat.getOutcome() == CombatOutcome.ONGOING) {
            printStatus(player, enemy);

            CombatActions action = choose(input, "Your turn", player.getActions());
            int enemyHpBefore = enemy.getCurrentHp();
            combat.playerTurn(action);

            System.out.println("Your choise: " + action.getName() + ". Seems like the " + enemy.getName() + " loses " +
                    (enemyHpBefore + enemy.getCurrentHp()) + " HP.");


            if (combat.getOutcome() != CombatOutcome.ONGOING) {
                break;
            }

            int playerHpBefore = player.getCurrentHp();
            EnemyTurnResult enemyTurn = combat.enemyTurn();

            if (enemyTurn != null) {
                System.out.println("Seems like the " + enemy.getName() + " is using " + enemyTurn.action().name() + "." +
                        "(" + enemyTurn.result().result() + "). Oh my, you lose " +
                        (playerHpBefore - player.getCurrentHp()) + "HP.");
            }
        }

        if (combat.getOutcome() == CombatOutcome.VICTORY) {
            System.out.println("You win! How fortuitous");
        } else {
            System.out.println("You lost! Pure skill issues! :D");
        }
    }

    private static void printStatus(PlayerCharacter player, Enemy enemy) {
        System.out.println();
        System.out.println(player.getName() + " HP: " + player.getCurrentHp() + "/" + player.getStats().maxHp()
                + "   |   " + enemy.getName() + " HP: " + enemy.getCurrentHp() + "/" + enemy.getStats().maxHp());
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

}
