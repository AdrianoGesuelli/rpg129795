package it.unicam.cs.mpgc.rpg129795.combat.status;

import it.unicam.cs.mpgc.rpg129795.combat.Combatant;

public abstract class TimedEffect implements StatusEffect{

    private final int duration;
    private int remainingTurns;

    protected TimedEffect(int duration) {
        this.duration = duration;
        this.remainingTurns = duration;
    }

    public void onTurnEnd(Combatant statusOwner) {
        remainingTurns--;
    }

    public void reapply() {
        remainingTurns = duration;
    }

    public boolean hasEnded() {
        return remainingTurns <= 0;
    }

    public int remainingTurns() {
        return remainingTurns;
    }
}