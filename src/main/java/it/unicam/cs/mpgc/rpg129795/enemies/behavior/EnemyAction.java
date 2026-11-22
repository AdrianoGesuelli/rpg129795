package it.unicam.cs.mpgc.rpg129795.enemies.behavior;

import it.unicam.cs.mpgc.rpg129795.combat.affinities.DamageType;
import it.unicam.cs.mpgc.rpg129795.combat.DamageRange;
import it.unicam.cs.mpgc.rpg129795.combat.status.StatusApplication;

import java.util.List;

public record EnemyAction(String id, String name, DamageRange damage, DamageType damageType,
                          List<StatusApplication> statuses, EnemyActionType type) {

    //? Attacchi con status effects
    public EnemyAction {
        if (id == null || name == null || damage == null || damageType == null || statuses == null || type == null) {
            throw new IllegalArgumentException("Non valid enemy action");
        }
    }

    //? Attacco normale con status
    public EnemyAction(String id, String name, DamageRange damage, DamageType damageType,
                       List<StatusApplication> statuses) {
        this(id, name, damage, damageType, statuses, EnemyActionType.ATTACK);
    }

    //? Attacco normale senza status
    public EnemyAction(String id, String name, DamageRange damage, DamageType damageType) {
        this(id, name, damage, damageType, List.of(), EnemyActionType.ATTACK);
    }

    //? Analisi: non fa danni, rende più forte l'attacco successivo
    public static EnemyAction analyze(String id, String name) {
        return new EnemyAction(id, name, new DamageRange(0, 0), DamageType.PHYSICAL, List.of(), EnemyActionType.ANALYZE);
    }

    //? La maledizione: i suoi effetti sono gestiti da AttackResolve, qui serve solo a identificarla
    public static EnemyAction curse(String id, String name) {
        return new EnemyAction(id, name, new DamageRange(0, 0), DamageType.DARK, List.of(), EnemyActionType.CURSE);
    }
}
