package it.unicam.cs.mpgc.rpg129795.Combat.Status;

import it.unicam.cs.mpgc.rpg129795.Combat.Combatant;

public interface StatusEffect {

    StatusType getStatusType();
    default void onTurnStart(Combatant statusOwner) {};
    default void onTurnEnd(Combatant statusOwner) {};
    void reapply();
    boolean hasEnded();

}
