package it.unicam.cs.mpgc.rpg129795.character;

import it.unicam.cs.mpgc.rpg129795.character.classes.CharacterClass;
import it.unicam.cs.mpgc.rpg129795.character.classes.Subclass;
import it.unicam.cs.mpgc.rpg129795.combat.AbstractCombatant;
import it.unicam.cs.mpgc.rpg129795.combat.actionList.AttackAction;
import it.unicam.cs.mpgc.rpg129795.combat.actionList.DefendAction;
import it.unicam.cs.mpgc.rpg129795.combat.actionList.DodgeAction;
import it.unicam.cs.mpgc.rpg129795.combat.actionList.ObserveAction;
import it.unicam.cs.mpgc.rpg129795.combat.affinities.Affinity;
import it.unicam.cs.mpgc.rpg129795.combat.affinities.DamageType;
import it.unicam.cs.mpgc.rpg129795.combat.CombatAction;
import it.unicam.cs.mpgc.rpg129795.items.relics.Relic;
import it.unicam.cs.mpgc.rpg129795.items.weapons.Weapon;

import java.util.ArrayList;
import java.util.List;

public class PlayerCharacter extends AbstractCombatant {

    private final String name;
    private final CharacterClass characterClass;
    private final Subclass subclass;
    private final Relic relic;
    private final Weapon weapon;
    private final List<CombatAction> actions;
    private static final int percent = 100;

    PlayerCharacter(String name, CharacterClass characterClass, Subclass subclass, Relic relic, Weapon weapon) {
        super(characterClass.baseStats().with(subclass.modifiers()).maxHp() * relic.getMaxHpPercent() / percent);
        this.name = name;
        this.characterClass = characterClass;
        this.subclass = subclass;
        this.relic = relic;
        this.weapon = weapon;
        this.actions = buildActions();
    }

    private List<CombatAction> buildActions() {
        List<CombatAction> result = new ArrayList<>();
        result.add(new AttackAction("Attack", weapon.damage(), weapon.damageType(), List.of()));
        result.addAll(subclass.ability().createExtraActions(weapon));
        result.add(new DefendAction());
        result.add(new DodgeAction());
        result.add(new ObserveAction());
        return List.copyOf(result);
    }

    public String getName() {
        return name;
    }

    public Stats getStats() {
        Stats base = characterClass.baseStats().with(subclass.modifiers());
        int maxHp = base.maxHp() * relic.getMaxHpPercent() / percent;
        int currentHp = getCurrentHp();
        return new Stats(maxHp,
                base.defense(),
                base.evasion() + relic.getEvasionBonus(currentHp, maxHp),
                base.critChance() + relic.getCritBonus(currentHp, maxHp));
    }

    public Affinity getAffinity(DamageType type) {
        return Affinity.NORMAL;
    }

    public List<CombatAction> getActions() {
        return actions;
    }

    public CharacterClass getCharacterClass() {
        return characterClass;
    }

    public Subclass getSubclass() {
        return subclass;
    }

    public Relic getRelic() {
        return relic;
    }

    public Weapon getWeapon() {
        return weapon;
    }

    //! Effetti della reliquia sul combattimento ---------------------

    @Override
    public int getStanceDamageMultiplier() {
        return relic.adjustStanceDamage(getStance(), getStance().getDamageMultiplier());
    }

    @Override
    public int getCurseDamagePercent() {
        return relic.getCurseDamagePercent();
    }

    @Override
    public boolean blocksCurseEffects() {
        return relic.blocksCurseEffects();
    }

    public List<CombatAction> getReadyActions() {
        List<CombatAction> ready = new ArrayList<>();

        for (int i = 0; i < actions.size(); i++) {
            if (actions.get(i).isReady()) {
                ready.add(actions.get(i));
            }
        }
        return List.copyOf(ready);
    }

    public void tickCooldowns() {
        for (int i = 0; i < actions.size(); i++) {
            actions.get(i).onTurnStart();
        }
    }

    public void resetCooldowns() {
        for (int i = 0; i < actions.size(); i++) {
            actions.get(i).resetCooldown();
        }
    }
}
