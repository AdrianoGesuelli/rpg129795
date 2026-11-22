package it.unicam.cs.mpgc.rpg129795.Combat.ActionList;

import it.unicam.cs.mpgc.rpg129795.Combat.CombatActions;
import it.unicam.cs.mpgc.rpg129795.Combat.CombatContext;
import it.unicam.cs.mpgc.rpg129795.Combat.Resolvers.ActionResult;

public class CooldownAction implements CombatActions {

    private final CombatActions action;
    private final int abilityCooldownTurns;
    private int turnsLeft = 0;

    public CooldownAction(CombatActions action, int abilityCooldownTurns) {
        if (action == null || abilityCooldownTurns <= 0) {
            throw new IllegalArgumentException("Invalid action cooldown");
        }
        this.action = action;
        this.abilityCooldownTurns = abilityCooldownTurns;
    }

    public String getName() {
        return action.getName();
    }

    public ActionResult execute(CombatContext context) {
        turnsLeft = abilityCooldownTurns;
        return action.execute(context);
    }

    public boolean isReady() {
        return turnsLeft == 0;
    }

    public int getCooldownLeft() {
        return turnsLeft;
    }

    public void onTurnStart() {
        if (turnsLeft > 0) {
            turnsLeft--;
        }
    }

    public void resetCooldown() {
        turnsLeft = 0;
    }

}
