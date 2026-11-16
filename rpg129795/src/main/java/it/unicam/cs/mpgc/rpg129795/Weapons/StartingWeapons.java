package it.unicam.cs.mpgc.rpg129795.Weapons;

import it.unicam.cs.mpgc.rpg129795.Combat.Affinities.DamageType;

import java.util.List;

public class StartingWeapons {

    private StartingWeapons() {}

    public static List<Weapon> all() {
        return List.of(

                new WeaponTemplate("twin_blades_id", "Twin Blades", 12, DamageType.PHYSICAL),
                new WeaponTemplate("greatsword_id", "Greatsword", 16, DamageType.PHYSICAL),
                new WeaponTemplate("dagger_id", "Dagger", 8, DamageType.PHYSICAL)

        );
    }

}
