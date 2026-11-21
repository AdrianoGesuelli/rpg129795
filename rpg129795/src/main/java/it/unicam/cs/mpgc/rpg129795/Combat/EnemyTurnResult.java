package it.unicam.cs.mpgc.rpg129795.Combat;

import it.unicam.cs.mpgc.rpg129795.Combat.Resolvers.AttackResult;
import it.unicam.cs.mpgc.rpg129795.Enemies.ActionsAndBehavior.EnemyAction;
import it.unicam.cs.mpgc.rpg129795.Enemies.ActionsAndBehavior.EnemyActionType;

public record EnemyTurnResult(EnemyAction action, AttackResult result) {

    //? Turno in cui il nemico prepara qualcosa (analisi o maledizione): nessun attacco, nessun risultato
    public static EnemyTurnResult preparing(EnemyAction action) {
        return new EnemyTurnResult(action, null);
    }

    public boolean isPreparation() {
        return result() == null;
    }

    //? Il nemico sta invocando la maledizione: si mostra solo il presentimento
    public boolean isCharging() {
        return isPreparation() && action().type() == EnemyActionType.CURSE;
    }

}