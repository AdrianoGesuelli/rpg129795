package it.unicam.cs.mpgc.rpg129795.combat;

import it.unicam.cs.mpgc.rpg129795.combat.resolvers.ActionResult;

public interface CombatAction {

    String getName();
    ActionResult execute(CombatContext context);
    default void onTurnStart() {}
    default void resetCooldown() {}

    default boolean isReady() {
        return true;
    }

    default int getCooldownLeft() {
        return 0;
    }
}
