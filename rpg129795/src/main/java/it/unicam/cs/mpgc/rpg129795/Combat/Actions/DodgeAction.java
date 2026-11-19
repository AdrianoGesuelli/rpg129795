package it.unicam.cs.mpgc.rpg129795.Combat.Actions;

import it.unicam.cs.mpgc.rpg129795.Combat.CombatActions;
import it.unicam.cs.mpgc.rpg129795.Combat.CombatContext;
import it.unicam.cs.mpgc.rpg129795.Combat.Resolvers.ActionResult;
import it.unicam.cs.mpgc.rpg129795.Combat.Stance;

public final class DodgeAction implements CombatActions {

    public String getName() {
        return "Dodge";
    }

    public ActionResult execute(CombatContext context) {
        context.source().setStance(Stance.DODGE);
        return ActionResult.withoutAttack(getName());
    }
}
