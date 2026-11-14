package it.unicam.cs.mpgc.rpg129795.Weapons;

import it.unicam.cs.mpgc.rpg129795.Combat.Affinities.DamageType;

import java.util.List;

public class StartingWeapons {

    private StartingWeapons() {}

    public static List<Weapon> all() {
        return List.of(

                new WeaponsMold("twin_blades_id", "Twin Blades", 12, DamageType.PHYSICAL),
                new WeaponsMold("greatsword_id", "Greatsword", 16, DamageType.PHYSICAL),
                new WeaponsMold("dagger_id", "Dagger", 8, DamageType.PHYSICAL)

        );
    }

}
