package it.unicam.cs.mpgc.rpg129795.items.weapons;

import it.unicam.cs.mpgc.rpg129795.combat.affinities.DamageType;
import it.unicam.cs.mpgc.rpg129795.combat.DamageRange;

public record WeaponTemplate(String id, String name, DamageRange damage, DamageType damageType) implements Weapon{

    public WeaponTemplate {
        if (id == null || name == null || damage == null || damageType == null) {
            throw new IllegalArgumentException("Invalid weapon data");
        }
    }
}
