package it.unicam.cs.mpgc.rpg129795.combat.actionList;

import it.unicam.cs.mpgc.rpg129795.combat.CombatAction;
import it.unicam.cs.mpgc.rpg129795.combat.CombatContext;
import it.unicam.cs.mpgc.rpg129795.combat.resolvers.ActionResult;
import it.unicam.cs.mpgc.rpg129795.enemies.Observable;

public final class ObserveAction implements CombatAction {

    private static final String name = "Observe";
    private static final String nothingToLearn = "There is nothing to learn here.";

    private boolean used;

    public String getName() {
        return name;
    }

    public ActionResult execute(CombatContext context) {
        used = true;
        String observation = nothingToLearn;
        if (context.target() instanceof Observable observable) {
            observation = observable.getObservation();
        }
        return ActionResult.insight(name, observation);
    }

    public boolean isReady() {
        return !used;
    }

    public void resetCooldown() {
        used = false;
    }
}