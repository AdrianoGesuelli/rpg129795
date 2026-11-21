package it.unicam.cs.mpgc.rpg129795.Combat.Status.StatusList;

import it.unicam.cs.mpgc.rpg129795.Combat.Status.StatusType;
import it.unicam.cs.mpgc.rpg129795.Combat.Status.TimedEffect;

//? Vulnerabile: +50% di danno subito per 5 turni
public final class VulnerableEffect extends TimedEffect {

    private static final int duration = 5;
    private static final int damageTakenPercent = 150;

    public VulnerableEffect() {
        super(duration);
    }

    public StatusType getStatusType() {
        return StatusType.VULNERABLE;
    }

    public int getDamageTakenPercent() {
        return damageTakenPercent;
    }

}
