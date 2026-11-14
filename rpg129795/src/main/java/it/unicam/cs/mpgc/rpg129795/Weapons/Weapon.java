package it.unicam.cs.mpgc.rpg129795.Weapons;

import it.unicam.cs.mpgc.rpg129795.Combat.Affinities.DamageType;

public interface Weapon {

    String id();
    String name();
    int damage();
    DamageType damageType();

}
