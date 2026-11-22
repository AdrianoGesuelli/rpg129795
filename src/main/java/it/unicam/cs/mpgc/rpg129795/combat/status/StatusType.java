package it.unicam.cs.mpgc.rpg129795.combat.status;

import it.unicam.cs.mpgc.rpg129795.combat.affinities.DamageType;
import it.unicam.cs.mpgc.rpg129795.combat.status.statusList.NecrosisEffect;
import it.unicam.cs.mpgc.rpg129795.combat.status.statusList.PoisonEffect;
import it.unicam.cs.mpgc.rpg129795.combat.status.statusList.VulnerableEffect;
import it.unicam.cs.mpgc.rpg129795.combat.status.statusList.WeakenedEffect;

public enum StatusType {

    POISON(DamageType.POISON),
    NECROSIS(DamageType.NECROSIS),
    VULNERABLE(null),
    WEAKENED(null);

    private final DamageType damageType;

    StatusType(DamageType damageType) {
        this.damageType = damageType;
    }

    public DamageType getDamageType() {
        return damageType;
    }

    public boolean hasDamageType() {
        return damageType != null;
    }

    public StatusEffect create() {
        if (this == POISON) {
            return new PoisonEffect();
        }
        if (this == NECROSIS) {
            return new NecrosisEffect();
        }
        if (this == VULNERABLE) {
            return new VulnerableEffect();
        }
        return new WeakenedEffect();
    }
}
