package it.unicam.cs.mpgc.rpg129795.enemies;

import it.unicam.cs.mpgc.rpg129795.character.Stats;
import it.unicam.cs.mpgc.rpg129795.combat.affinities.AffinityTable;
import it.unicam.cs.mpgc.rpg129795.enemies.behavior.WeightedAction;

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
