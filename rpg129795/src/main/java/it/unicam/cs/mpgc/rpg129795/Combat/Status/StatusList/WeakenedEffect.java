package it.unicam.cs.mpgc.rpg129795.Combat.Status.StatusList;

import it.unicam.cs.mpgc.rpg129795.Combat.Status.StatusType;
import it.unicam.cs.mpgc.rpg129795.Combat.Status.TimedEffect;

//? Indebolito: −50% di danno inflitto con gli attacchi diretti per 5 turni
public final class WeakenedEffect extends TimedEffect {

    private static final int duration = 5;
    private static final int damageDealtPercent = 50;

    public WeakenedEffect() {
        super(duration);
    }

    public StatusType getStatusType() {
        return StatusType.WEAKENED;
    }

    public int getDamageDealtPercent() {
        return damageDealtPercent;
    }
}