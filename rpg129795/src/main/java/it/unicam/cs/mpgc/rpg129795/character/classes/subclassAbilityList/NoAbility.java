package it.unicam.cs.mpgc.rpg129795.character.classes.subclassAbilityList;

import it.unicam.cs.mpgc.rpg129795.combat.CombatAction;
import it.unicam.cs.mpgc.rpg129795.items.weapons.Weapon;

import java.util.List;

public final class NoAbility implements SubclassWeaponAbility{

    public List<CombatAction> createExtraActions (Weapon weapon) {
        return List.of();
    }

}
