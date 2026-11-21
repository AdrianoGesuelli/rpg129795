package it.unicam.cs.mpgc.rpg129795.Enemies;

import it.unicam.cs.mpgc.rpg129795.Character.Stats;
import it.unicam.cs.mpgc.rpg129795.Combat.AbstractCombatant;
import it.unicam.cs.mpgc.rpg129795.Combat.Affinities.Affinity;
import it.unicam.cs.mpgc.rpg129795.Combat.Affinities.DamageType;
import it.unicam.cs.mpgc.rpg129795.Enemies.ActionsAndBehavior.EnemyAction;
import it.unicam.cs.mpgc.rpg129795.Enemies.ActionsAndBehavior.EnemyBehavior;
import it.unicam.cs.mpgc.rpg129795.Enemies.ActionsAndBehavior.WeightedAction;

import java.util.ArrayList;
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

    //? Pesca dal pool del nemico più le azioni comuni a tutti (analisi e maledizione)
    public EnemyAction chooseAction() {
        List<WeightedAction> pool = new ArrayList<>(template.actions());
        pool.add(new WeightedAction(CommonEnemyActions.ANALYZE, CommonEnemyActions.ANALYZE_WEIGHT));
        pool.add(new WeightedAction(CommonEnemyActions.CURSE, CommonEnemyActions.CURSE_WEIGHT));
        return behavior.chooseAction(pool);
    }

    //? Dopo un'analisi: solo gli attacchi del catalogo (niente seconda analisi, niente maledizione)
    public EnemyAction chooseAttack() {
        return behavior.chooseAction(template.actions());
    }

    public List<String> getObservationHints() {
        return template.observeHints();
    }

}
