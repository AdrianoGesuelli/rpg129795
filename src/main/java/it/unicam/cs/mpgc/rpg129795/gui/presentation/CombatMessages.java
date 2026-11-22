package it.unicam.cs.mpgc.rpg129795.gui.presentation;

import it.unicam.cs.mpgc.rpg129795.combat.EnemyTurnResult;
import it.unicam.cs.mpgc.rpg129795.combat.resolvers.ActionResult;
import it.unicam.cs.mpgc.rpg129795.combat.resolvers.AttackResult;
import it.unicam.cs.mpgc.rpg129795.combat.resolvers.HitResult;
import it.unicam.cs.mpgc.rpg129795.enemies.behavior.EnemyAction;
import it.unicam.cs.mpgc.rpg129795.enemies.behavior.EnemyActionType;

public class CombatMessages {

    private CombatMessages() {}

    public static String playerTurn(String playerName, String enemyName, ActionResult result) {
        if (!result.isAttack()) {
            return "You use " + result.actionName() + ".";
        }
        return "You use " + result.actionName() + ". " + describeAttack(playerName, enemyName, result.attackResult());
    }

    public static String enemyTurn(String enemyName, String playerName, EnemyTurnResult result) {
        if (result.isPreparation()) {
            return enemyPreparation(enemyName, result.action());
        }
        String verb = " uses ";
        if (result.action().type() == EnemyActionType.CURSE) {
            verb = " unleashes ";
        }
        return enemyName + verb + result.action().name() + ". "
                + describeAttack(enemyName, playerName, result.result());
    }

    //? L'analisi: stesso stile delle azioni normali
    private static String enemyPreparation(String enemyName, EnemyAction action) {
        return enemyName + " uses " + action.name() + ". It is preparing a precise attack!";
    }

    //? Il presentimento che precede la maledizione
    public static String omen() {
        return "You have a bad feeling about this...";
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
