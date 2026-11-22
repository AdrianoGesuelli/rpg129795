package it.unicam.cs.mpgc.rpg129795.combat.actionList;

import it.unicam.cs.mpgc.rpg129795.combat.CombatAction;
import it.unicam.cs.mpgc.rpg129795.combat.CombatContext;
import it.unicam.cs.mpgc.rpg129795.combat.resolvers.ActionResult;
import it.unicam.cs.mpgc.rpg129795.combat.Stance;

public final class DefendAction implements CombatAction {

    public String getName() {
        return "Defend";
    }

    public ActionResult execute(CombatContext context) {
        context.source().setStance(Stance.DEFENSE);
        return ActionResult.withoutAttack(getName());
    }
}
