package it.unicam.cs.mpgc.rpg129795.Combat.Status;

import it.unicam.cs.mpgc.rpg129795.Combat.Affinities.Affinity;
import it.unicam.cs.mpgc.rpg129795.Combat.Combatant;

public abstract class DoTEffect implements StatusEffect {

    private static final int percent = 100;

    private final int maxDuration;
    private int remainingTurns;

    public DoTEffect(int duration) {
        this.maxDuration = duration;
        this.remainingTurns = duration;
    }

    public abstract int tickDamage();

    public void tick(Combatant statusOwner) {
        Affinity affinity = statusOwner.getAffinity(getStatusType().getDamageType());
        int damage = tickDamage();

        if (affinity.isAbsorbing()) {
            statusOwner.heal(damage);
        } else {
            statusOwner.takeDamage(damage * affinity.getMultiplier() / percent);
        }

        remainingTurns--;
    }

    public void reapply() {
        remainingTurns = maxDuration;
    }

    public boolean hasEnded() {
        return remainingTurns <= 0;
    }

}