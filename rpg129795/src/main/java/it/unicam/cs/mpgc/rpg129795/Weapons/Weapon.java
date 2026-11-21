package it.unicam.cs.mpgc.rpg129795.Weapons;

import it.unicam.cs.mpgc.rpg129795.Combat.Affinities.DamageType;
import it.unicam.cs.mpgc.rpg129795.Combat.DamageRange;

public interface Weapon {

    String id();
    String name();
    DamageRange damage();
    DamageType damageType();

}
