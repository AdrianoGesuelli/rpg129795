package it.unicam.cs.mpgc.rpg129795.Classes.Abilities;

import it.unicam.cs.mpgc.rpg129795.Combat.CombatActions;
import it.unicam.cs.mpgc.rpg129795.Weapons.Weapon;

import java.util.List;

public interface SubclassWeaponAbility {

    List<CombatActions> createExtraActions(Weapon weapon);

}
