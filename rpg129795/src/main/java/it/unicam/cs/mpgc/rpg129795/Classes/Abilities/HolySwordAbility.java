package it.unicam.cs.mpgc.rpg129795.Classes.Abilities;

import it.unicam.cs.mpgc.rpg129795.Combat.Actions.AttackAction;
import it.unicam.cs.mpgc.rpg129795.Combat.Actions.CooldownAction;
import it.unicam.cs.mpgc.rpg129795.Combat.Affinities.DamageType;
import it.unicam.cs.mpgc.rpg129795.Combat.CombatActions;
import it.unicam.cs.mpgc.rpg129795.Weapons.Weapon;

import java.util.List;

public final class HolySwordAbility implements SubclassWeaponAbility{

    private static final int holyDamagePercent = 65;
    private static final int abilityCooldownTurns = 2;

    public List<CombatActions> createExtraActions(Weapon weapon) {
        int damage = weapon.damage() * holyDamagePercent / 100;
        CombatActions holyStrike = new AttackAction("Holy Strike", damage, DamageType.HOLY, List.of());
        return List.of(new CooldownAction(holyStrike, abilityCooldownTurns));
    }

}
