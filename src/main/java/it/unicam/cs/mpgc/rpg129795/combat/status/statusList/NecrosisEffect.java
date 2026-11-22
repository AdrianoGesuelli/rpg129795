package it.unicam.cs.mpgc.rpg129795.combat.status.statusList;

import it.unicam.cs.mpgc.rpg129795.combat.Combatant;
import it.unicam.cs.mpgc.rpg129795.combat.status.DoTEffect;
import it.unicam.cs.mpgc.rpg129795.combat.status.StatusType;

public final class NecrosisEffect extends DoTEffect {

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
