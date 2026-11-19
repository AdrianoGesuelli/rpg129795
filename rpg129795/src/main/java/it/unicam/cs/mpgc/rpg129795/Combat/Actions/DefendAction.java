package it.unicam.cs.mpgc.rpg129795.Combat.Actions;

import it.unicam.cs.mpgc.rpg129795.Combat.CombatActions;
import it.unicam.cs.mpgc.rpg129795.Combat.CombatContext;
import it.unicam.cs.mpgc.rpg129795.Combat.Resolvers.ActionResult;
import it.unicam.cs.mpgc.rpg129795.Combat.Stance;

public final class DefendAction implements CombatActions {

    public String getName() {
        return "Defend";
    }

    public ActionResult execute(CombatContext context) {
        context.source().setStance(Stance.DEFENSE);
        return ActionResult.withoutAttack(getName());
    }
}
