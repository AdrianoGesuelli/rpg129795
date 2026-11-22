package it.unicam.cs.mpgc.rpg129795.items.weapons;

import it.unicam.cs.mpgc.rpg129795.combat.affinities.DamageType;
import it.unicam.cs.mpgc.rpg129795.combat.DamageRange;

import java.util.List;

public class StartingWeapons {

    public static final String twin_blades_id = "twin_blades";
    public static final String greatsword_id = "greatsword";
    public static final String dagger_id = "dagger";

    private StartingWeapons() {}

    public static List<Weapon> all() {
        return List.of(

                new WeaponTemplate(twin_blades_id, "Twin Blades", new DamageRange(9, 15), DamageType.PHYSICAL),
                new WeaponTemplate(greatsword_id, "Greatsword", new DamageRange(11, 18), DamageType.PHYSICAL),
                new WeaponTemplate(dagger_id, "Dagger", new DamageRange(6, 10), DamageType.PHYSICAL)

        );
    }

    public static Weapon WeaponById(String WeaponId) {
        List<Weapon> weapons = all();
        for (int i = 0; i < weapons.size(); i++) {
            if (weapons.get(i).id().equals(WeaponId)) {
                return weapons.get(i);
            }
        }
        throw new IllegalArgumentException("Unknown weapon id");
    }

}
