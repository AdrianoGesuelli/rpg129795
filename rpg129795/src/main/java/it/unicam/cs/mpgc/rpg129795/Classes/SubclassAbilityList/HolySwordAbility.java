package it.unicam.cs.mpgc.rpg129795.Classes.SubclassAbilityList;

import it.unicam.cs.mpgc.rpg129795.Combat.ActionList.AttackAction;
import it.unicam.cs.mpgc.rpg129795.Combat.ActionList.CooldownAction;
import it.unicam.cs.mpgc.rpg129795.Combat.Affinities.DamageType;
import it.unicam.cs.mpgc.rpg129795.Combat.CombatActions;
import it.unicam.cs.mpgc.rpg129795.Combat.DamageRange;
import it.unicam.cs.mpgc.rpg129795.Weapons.Weapon;

import java.util.List;

public final class HolySwordAbility implements SubclassWeaponAbility{

    private static final int holyDamagePercent = 65;
    private static final int abilityCooldownTurns = 2;

    public List<CombatActions> createExtraActions(Weapon weapon) {
        DamageRange damage = weapon.damage().scaled(holyDamagePercent);
        CombatActions holyStrike = new AttackAction("Holy Strike", damage, DamageType.HOLY, List.of());
        return List.of(new CooldownAction(holyStrike, abilityCooldownTurns));
    }

}
