package it.unicam.cs.mpgc.rpg129795.Gui.Presentation;

import it.unicam.cs.mpgc.rpg129795.Combat.EnemyTurnResult;
import it.unicam.cs.mpgc.rpg129795.Combat.Resolvers.ActionResult;
import it.unicam.cs.mpgc.rpg129795.Combat.Resolvers.AttackResult;
import it.unicam.cs.mpgc.rpg129795.Combat.Resolvers.HitResult;

public class CombatMessages {

    private CombatMessages() {}

    public static String playerTurn(String playerName, String enemyName, ActionResult result) {
        if (!result.isAttack()) {
            return "You use " + result.actionName() + ".";
        }
        return "You use " + result.actionName() + ". " + describeAttack(playerName, enemyName, result.attackResult());
    }

    public static String enemyTurn(String enemyName, String playerName, EnemyTurnResult result) {
        return enemyName + " uses " + result.action().name() + ". " + describeAttack(enemyName, playerName, result.result());
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
}
