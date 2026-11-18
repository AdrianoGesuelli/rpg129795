package it.unicam.cs.mpgc.rpg129795.Combat.Status;

import it.unicam.cs.mpgc.rpg129795.Combat.Affinities.DamageType;
import it.unicam.cs.mpgc.rpg129795.Combat.Status.StatusList.NecrosisEffect;
import it.unicam.cs.mpgc.rpg129795.Combat.Status.StatusList.PoisonEffect;

public enum StatusType {

    POISON(DamageType.POISON),
    NECROSIS(DamageType.NECROSIS);

    private final DamageType damageType;

    StatusType(DamageType damageType) {
        this.damageType = damageType;
    }

    public DamageType getDamageType() {
        return damageType;
    }

    //? Nuova istanza dello status con durata piena
    public StatusEffect create() {
        if (damageType == DamageType.POISON) {
            return new PoisonEffect();
        }
        return new NecrosisEffect();
    }
}
