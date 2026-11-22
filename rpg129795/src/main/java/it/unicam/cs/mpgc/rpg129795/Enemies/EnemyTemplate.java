package it.unicam.cs.mpgc.rpg129795.Enemies;

import it.unicam.cs.mpgc.rpg129795.Character.Stats;
import it.unicam.cs.mpgc.rpg129795.Combat.Affinities.AffinityTable;
import it.unicam.cs.mpgc.rpg129795.Enemies.ActionsAndBehavior.WeightedAction;

import java.util.List;

public record EnemyTemplate(String id, String name, EnemyTier tier, Stats stats, AffinityTable affinities,
                            List<WeightedAction> actions, String observation) {

    public EnemyTemplate {
        if (id == null || name == null || tier == null || stats == null || affinities == null || actions == null || observation == null) {
            throw new IllegalArgumentException("Enemy data not valid");
        }

        actions = List.copyOf(actions);
    }
}
