package it.unicam.cs.mpgc.rpg129795.Combat.Status;

import it.unicam.cs.mpgc.rpg129795.Combat.Combatant;

public interface StatusEffect {

    StatusType getStatusType();
    default void onTurnStart(Combatant statusOwner) {};
    default void onTurnEnd(Combatant statusOwner) {};
    void reapply();
    boolean hasEnded();
    int remainingTurns();

    default int getStacks() {
        return 1;
    }

    //? Percentuale di danno subito mentre lo status è attivo (100 = normale)
    default int getDamageTakenPercent() {
        return 100;
    }

    //? Percentuale di danno inflitto (con gli attacchi diretti) mentre lo status è attivo
    default int getDamageDealtPercent() {
        return 100;
    }

}
