package it.unicam.cs.mpgc.rpg129795.Character;

import it.unicam.cs.mpgc.rpg129795.Classes.CharacterClass;
import it.unicam.cs.mpgc.rpg129795.Classes.Subclass;
import it.unicam.cs.mpgc.rpg129795.Combat.AbstractCombatant;
import it.unicam.cs.mpgc.rpg129795.Combat.Actions.AttackAction;
import it.unicam.cs.mpgc.rpg129795.Combat.Actions.DefendAction;
import it.unicam.cs.mpgc.rpg129795.Combat.Actions.DodgeAction;
import it.unicam.cs.mpgc.rpg129795.Combat.Affinities.Affinity;
import it.unicam.cs.mpgc.rpg129795.Combat.Affinities.DamageType;
import it.unicam.cs.mpgc.rpg129795.Combat.CombatActions;
import it.unicam.cs.mpgc.rpg129795.Weapons.Weapon;

import java.util.ArrayList;
import java.util.List;

public class PlayerCharacter extends AbstractCombatant {

    private final CharacterClass characterClass;
    private final Subclass subclass;
    private final Weapon weapon;
    private final List<CombatActions> actions;

    PlayerCharacter(CharacterClass characterClass, Subclass subclass, Weapon weapon) {
        super(characterClass.baseStats().with(subclass.modifiers()).maxHp());
        this.characterClass = characterClass;
        this.subclass = subclass;
        this.weapon = weapon;
        this.actions = buildActions();
    }

    private List<CombatActions> buildActions() {
        List<CombatActions> result = new ArrayList<>();
        result.add(new AttackAction("Attack", weapon.damage(), weapon.damageType(), List.of()));
        result.addAll(subclass.ability().createExtraActions(weapon));
        result.add(new DefendAction());
        result.add(new DodgeAction());
        return List.copyOf(result);
    }

    public String getName() {
        return characterClass.name();
    }

    public Stats getStats() {
        return characterClass.baseStats().with(subclass.modifiers());
    }

    public Affinity getAffinity(DamageType type) {
        return Affinity.NORMAL;
    }

    public List<CombatActions> getActions() {
        return actions;
    }

    public CharacterClass getCharacterClass() {
        return characterClass;
    }

    public Subclass getSubclass() {
        return subclass;
    }

    public Weapon getWeapon() {
        return weapon;
    }
}
