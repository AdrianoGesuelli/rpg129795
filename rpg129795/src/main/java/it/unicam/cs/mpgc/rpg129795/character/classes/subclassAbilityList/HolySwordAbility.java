package it.unicam.cs.mpgc.rpg129795.character.classes.subclassAbilityList;

import it.unicam.cs.mpgc.rpg129795.combat.actionList.AttackAction;
import it.unicam.cs.mpgc.rpg129795.combat.actionList.CooldownAction;
import it.unicam.cs.mpgc.rpg129795.combat.affinities.DamageType;
import it.unicam.cs.mpgc.rpg129795.combat.CombatAction;
import it.unicam.cs.mpgc.rpg129795.combat.DamageRange;
import it.unicam.cs.mpgc.rpg129795.items.weapons.Weapon;

import java.util.List;

public final class HolySwordAbility implements SubclassWeaponAbility{

    private static final int holyDamagePercent = 65;
    private static final int abilityCooldownTurns = 2;

    public List<CombatAction> createExtraActions(Weapon weapon) {
        DamageRange damage = weapon.damage().scaled(holyDamagePercent);
        CombatAction holyStrike = new AttackAction("Holy Strike", damage, DamageType.HOLY, List.of());
        return List.of(new CooldownAction(holyStrike, abilityCooldownTurns));
    }

}
