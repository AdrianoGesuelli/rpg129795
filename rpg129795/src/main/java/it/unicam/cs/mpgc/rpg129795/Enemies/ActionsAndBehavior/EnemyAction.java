package it.unicam.cs.mpgc.rpg129795.Enemies.ActionsAndBehavior;

import it.unicam.cs.mpgc.rpg129795.Combat.Affinities.DamageType;
import it.unicam.cs.mpgc.rpg129795.Combat.Status.StatusApplication;

import java.util.List;

public record EnemyAction(String id, String name, int damage, DamageType damageType, List<StatusApplication> statuses) {

    //? Attacchi con status effects
    public EnemyAction {
        if (id == null || name == null || damage < 0 || damageType == null || statuses == null) {
            throw new IllegalArgumentException("Non valid enemy action");
        }
        statuses = List.copyOf(statuses);
    }

    //? Attacchi senza status effects
    public EnemyAction(String id, String name, int damage, DamageType damageType) {
        this(id, name, damage, damageType, List.of());
    }
}
