package it.unicam.cs.mpgc.rpg129795.Weapons;

import it.unicam.cs.mpgc.rpg129795.Combat.Affinities.DamageType;

public record WeaponTemplate(String id, String name, int damage, DamageType damageType) implements Weapon{

    public WeaponTemplate {
        if (id == null || name == null || damage <= 0 || damageType == null) {
            throw new IllegalArgumentException("Invalid weapon data");
        }
    }
}
