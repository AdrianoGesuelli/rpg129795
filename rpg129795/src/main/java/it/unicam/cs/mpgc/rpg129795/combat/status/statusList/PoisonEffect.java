package it.unicam.cs.mpgc.rpg129795.combat.status.statusList;

import it.unicam.cs.mpgc.rpg129795.combat.Combatant;
import it.unicam.cs.mpgc.rpg129795.combat.status.DoTEffect;
import it.unicam.cs.mpgc.rpg129795.combat.status.StatusType;

public final class PoisonEffect extends DoTEffect {

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
