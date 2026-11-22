package it.unicam.cs.mpgc.rpg129795.enemies;

import it.unicam.cs.mpgc.rpg129795.character.Stats;
import it.unicam.cs.mpgc.rpg129795.combat.AbstractCombatant;
import it.unicam.cs.mpgc.rpg129795.combat.affinities.Affinity;
import it.unicam.cs.mpgc.rpg129795.combat.affinities.DamageType;
import it.unicam.cs.mpgc.rpg129795.enemies.behavior.CommonEnemyActions;
import it.unicam.cs.mpgc.rpg129795.enemies.behavior.EnemyAction;
import it.unicam.cs.mpgc.rpg129795.enemies.behavior.EnemyBehavior;
import it.unicam.cs.mpgc.rpg129795.enemies.behavior.WeightedAction;

import java.util.ArrayList;
import java.util.List;

public final class StandardEnemy extends AbstractCombatant implements Enemy, Observable {

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
        pool.add(new WeightedAction(CommonEnemyActions.analyze, CommonEnemyActions.analyzeWeight));
        pool.add(new WeightedAction(CommonEnemyActions.curse, CommonEnemyActions.curseWeight));
        return behavior.chooseAction(pool);
    }

    //? Dopo un'analisi: solo gli attacchi del catalogo (niente seconda analisi, niente maledizione)
    public EnemyAction chooseAttack() {
        return behavior.chooseAction(template.actions());
    }

    public String getObservation() {
        return template.observation();
    }

}
