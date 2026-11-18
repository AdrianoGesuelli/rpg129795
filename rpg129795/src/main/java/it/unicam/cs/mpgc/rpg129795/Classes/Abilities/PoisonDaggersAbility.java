package it.unicam.cs.mpgc.rpg129795.Classes.Abilities;

import it.unicam.cs.mpgc.rpg129795.Combat.Actions.AttackAction;
import it.unicam.cs.mpgc.rpg129795.Combat.CombatActions;
import it.unicam.cs.mpgc.rpg129795.Combat.Status.StatusApplication;
import it.unicam.cs.mpgc.rpg129795.Combat.Status.StatusType;
import it.unicam.cs.mpgc.rpg129795.Weapons.Weapon;

import java.util.List;

public class PoisonDaggersAbility implements SubclassWeaponAbility{

    public List<CombatActions> createExtraActions (Weapon weapon) {
        return List.of(new AttackAction("Poisoned Attack", weapon.damage(), weapon.damageType(),
                List.of(new StatusApplication(StatusType.POISON, 100))));
    }
}
