package it.unicam.cs.mpgc.rpg129795.Weapons;

import it.unicam.cs.mpgc.rpg129795.Combat.Affinities.DamageType;

public record WeaponsMold(String name, int damage, DamageType damageType) implements Weapon{

    public WeaponsMold{
        if (name == null || damage <= 0 || damageType == null) {
            throw new IllegalArgumentException("Invalid weapon data");
        }
    }
}
