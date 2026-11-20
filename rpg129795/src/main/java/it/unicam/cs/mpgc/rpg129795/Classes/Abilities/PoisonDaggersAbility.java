package it.unicam.cs.mpgc.rpg129795.Classes.Abilities;

import it.unicam.cs.mpgc.rpg129795.Combat.Actions.AttackAction;
import it.unicam.cs.mpgc.rpg129795.Combat.CombatActions;
import it.unicam.cs.mpgc.rpg129795.Combat.Status.StatusApplication;
import it.unicam.cs.mpgc.rpg129795.Combat.Status.StatusType;
import it.unicam.cs.mpgc.rpg129795.Weapons.Weapon;

import java.util.List;

public class PoisonDaggersAbility implements SubclassWeaponAbility{

    private static final int poisonDamagePercent = 85;

    public List<CombatActions> createExtraActions (Weapon weapon) {
        int damage = weapon.damage() * poisonDamagePercent / 100;

        return List.of(new AttackAction("Poisoned Attack", damage, weapon.damageType(),
                List.of(new StatusApplication(StatusType.POISON, 100))));
    }
}
