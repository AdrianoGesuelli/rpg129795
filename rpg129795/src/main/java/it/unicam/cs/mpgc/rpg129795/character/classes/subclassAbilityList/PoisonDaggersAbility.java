package it.unicam.cs.mpgc.rpg129795.character.classes.subclassAbilityList;

import it.unicam.cs.mpgc.rpg129795.combat.actionList.AttackAction;
import it.unicam.cs.mpgc.rpg129795.combat.CombatAction;
import it.unicam.cs.mpgc.rpg129795.combat.DamageRange;
import it.unicam.cs.mpgc.rpg129795.combat.status.StatusApplication;
import it.unicam.cs.mpgc.rpg129795.combat.status.StatusType;
import it.unicam.cs.mpgc.rpg129795.items.weapons.Weapon;

import java.util.List;

public final class PoisonDaggersAbility implements SubclassWeaponAbility{

    private static final int poisonDamagePercent = 85;

    public List<CombatAction> createExtraActions (Weapon weapon) {
        DamageRange damage = weapon.damage().scaled(poisonDamagePercent);

        return List.of(new AttackAction("Poisoned Attack", damage, weapon.damageType(),
                List.of(new StatusApplication(StatusType.POISON, 100))));
    }
}
