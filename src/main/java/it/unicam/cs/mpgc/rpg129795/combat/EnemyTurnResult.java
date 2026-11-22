package it.unicam.cs.mpgc.rpg129795.combat;

import it.unicam.cs.mpgc.rpg129795.combat.resolvers.AttackResult;
import it.unicam.cs.mpgc.rpg129795.enemies.behavior.EnemyAction;

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