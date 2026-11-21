package it.unicam.cs.mpgc.rpg129795.Weapons;

import it.unicam.cs.mpgc.rpg129795.Combat.Affinities.DamageType;
import it.unicam.cs.mpgc.rpg129795.Combat.DamageRange;

public record WeaponTemplate(String id, String name, DamageRange damage, DamageType damageType) implements Weapon{

    public WeaponTemplate {
        if (id == null || name == null || damage == null || damageType == null) {
            throw new IllegalArgumentException("Invalid weapon data");
        }
    }
}
