package it.unicam.cs.mpgc.rpg129795.Classes.SubclassAbilityList;

import it.unicam.cs.mpgc.rpg129795.Combat.CombatActions;
import it.unicam.cs.mpgc.rpg129795.Weapons.Weapon;

import java.util.List;

public final class NoAbility implements SubclassWeaponAbility{

    public List<CombatActions> createExtraActions (Weapon weapon) {
        return List.of();
    }

}
