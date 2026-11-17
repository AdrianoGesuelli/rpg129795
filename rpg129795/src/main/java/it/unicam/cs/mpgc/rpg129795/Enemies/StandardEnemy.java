package it.unicam.cs.mpgc.rpg129795.Enemies;

import it.unicam.cs.mpgc.rpg129795.Character.Stats;
import it.unicam.cs.mpgc.rpg129795.Combat.AbstractCombatant;
import it.unicam.cs.mpgc.rpg129795.Combat.Affinities.Affinity;
import it.unicam.cs.mpgc.rpg129795.Combat.Affinities.DamageType;
import it.unicam.cs.mpgc.rpg129795.Enemies.ActionsAndBehavior.EnemyAction;
import it.unicam.cs.mpgc.rpg129795.Enemies.ActionsAndBehavior.EnemyBehavior;

import java.util.List;

public final class StandardEnemy extends AbstractCombatant implements Enemy {

    private final EnemyBehavior behavior;
    private final EnemyTemplate template;

    public StandardEnemy (EnemyBehavior behavior, EnemyTemplate  template) {
        super(template.stats().maxHp());
        this.behavior = behavior;
        this.template = template;
    }

    public String getId(){
        return template.id();
    }

    public String getName(){
        return template.name();
    }

    public EnemyTier getTier(){
        return template.tier();
    }

    public Stats getStats() {
        return template.stats();
    }

    public Affinity getAffinity(DamageType type) {
        return template.affinities().of(type);
    }

    public EnemyAction chooseAction() {
        return behavior.chooseAction(template.actions());
    }

    public List<String> getObservationHints() {
        return template.observeHints();
    }

}
