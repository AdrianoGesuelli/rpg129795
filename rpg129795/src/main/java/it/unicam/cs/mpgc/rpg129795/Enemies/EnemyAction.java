package it.unicam.cs.mpgc.rpg129795.Enemies;

import it.unicam.cs.mpgc.rpg129795.Combat.Affinities.DamageType;

public record EnemyAction(String id, String name, int damage, DamageType damageType) {

    public EnemyAction {
        if (id == null || name == null || damage < 0 || damageType == null) {
            throw new IllegalArgumentException("Non valid enemy action");
        }
    }
}
