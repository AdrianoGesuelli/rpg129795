package it.unicam.cs.mpgc.rpg129795.Combat.ActionList;

import it.unicam.cs.mpgc.rpg129795.Combat.CombatActions;
import it.unicam.cs.mpgc.rpg129795.Combat.CombatContext;
import it.unicam.cs.mpgc.rpg129795.Combat.Resolvers.ActionResult;
import it.unicam.cs.mpgc.rpg129795.Enemies.Observable;

public final class ObserveAction implements CombatActions {

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