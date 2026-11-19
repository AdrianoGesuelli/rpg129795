package it.unicam.cs.mpgc.rpg129795.Combat;

import it.unicam.cs.mpgc.rpg129795.Combat.Actions.AttackAction;
import it.unicam.cs.mpgc.rpg129795.Combat.Resolvers.ActionResult;

public interface CombatActions {

    String getName();
    ActionResult execute(CombatContext context);
}
