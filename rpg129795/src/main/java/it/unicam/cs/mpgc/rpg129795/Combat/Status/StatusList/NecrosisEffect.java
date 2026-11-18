package it.unicam.cs.mpgc.rpg129795.Combat.Status.StatusList;

import it.unicam.cs.mpgc.rpg129795.Combat.Combatant;
import it.unicam.cs.mpgc.rpg129795.Combat.Status.DoTEffect;
import it.unicam.cs.mpgc.rpg129795.Combat.Status.StatusType;

public class NecrosisEffect extends DoTEffect {

    private static final int duration = 3;
    private static final int damage = 5;

    public NecrosisEffect() {
        super(duration);
    }

    public StatusType getStatusType() {
        return StatusType.NECROSIS;
    }

    public void onTurnEnd(Combatant statusOwner) {
        tick(statusOwner);
    }

    public int tickDamage() {
        return damage;
    }
}
