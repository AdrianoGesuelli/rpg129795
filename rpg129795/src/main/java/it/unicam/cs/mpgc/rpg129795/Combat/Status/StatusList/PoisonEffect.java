package it.unicam.cs.mpgc.rpg129795.Combat.Status.StatusList;

import it.unicam.cs.mpgc.rpg129795.Combat.Combatant;
import it.unicam.cs.mpgc.rpg129795.Combat.Status.DoTEffect;
import it.unicam.cs.mpgc.rpg129795.Combat.Status.StatusType;

public class PoisonEffect extends DoTEffect {

    private static final int duration = 3;
    private static final int damagePerStack = 2;
    private int stacks = 1;

    public PoisonEffect() {
        super(duration);
    }

    public StatusType getStatusType() {
        return StatusType.POISON;
    }

    public void onTurnStart(Combatant statusOwner) {
        tick(statusOwner);
    }

    public int tickDamage() {
        return stacks * damagePerStack;
    }

    public void reapply() {
        super.reapply();
        stacks++;
    }

    public int getStacks() {
        return stacks;
    }
}
