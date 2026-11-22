package it.unicam.cs.mpgc.rpg129795.items.weapons;

import it.unicam.cs.mpgc.rpg129795.combat.affinities.DamageType;
import it.unicam.cs.mpgc.rpg129795.combat.DamageRange;

public interface Weapon {

    String id();
    String name();
    DamageRange damage();
    DamageType damageType();

}
