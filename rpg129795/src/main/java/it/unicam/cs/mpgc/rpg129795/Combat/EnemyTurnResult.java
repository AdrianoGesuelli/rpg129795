package it.unicam.cs.mpgc.rpg129795.Combat;

import it.unicam.cs.mpgc.rpg129795.Combat.Resolvers.AttackResult;
import it.unicam.cs.mpgc.rpg129795.Enemies.ActionsAndBehavior.EnemyAction;
import it.unicam.cs.mpgc.rpg129795.Enemies.ActionsAndBehavior.EnemyActionType;

public record EnemyTurnResult(EnemyAction action, AttackResult result, boolean curseIncoming) {

    //? Turno normale, senza annuncio della maledizione
    public EnemyTurnResult(EnemyAction action, AttackResult result) {
        this(action, result, false);
    }

    public static EnemyTurnResult preparing(EnemyAction action) {
        return new EnemyTurnResult(action, null);
    }

    public boolean isPreparation() {
        return result() == null;
    }

}