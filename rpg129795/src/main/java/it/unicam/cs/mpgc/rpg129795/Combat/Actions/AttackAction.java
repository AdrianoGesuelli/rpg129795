package it.unicam.cs.mpgc.rpg129795.Combat.Actions;

import it.unicam.cs.mpgc.rpg129795.Combat.Affinities.DamageType;
import it.unicam.cs.mpgc.rpg129795.Combat.CombatActions;
import it.unicam.cs.mpgc.rpg129795.Combat.CombatContext;
import it.unicam.cs.mpgc.rpg129795.Combat.Status.StatusApplication;

import java.util.List;

public final class AttackAction implements CombatActions {

    private final String name;
    private final int damage;
    private final DamageType damageType;
    private final List<StatusApplication> statuses;

    public AttackAction(String name, int damage, DamageType damageType, List<StatusApplication> statuses) {
        if (name == null || damage < 0 || damageType == null || statuses == null) {
            throw new IllegalArgumentException("Invalid attack data");
        }

        this.name = name;
        this.damage = damage;
        this.damageType = damageType;
        this.statuses = List.copyOf(statuses);
    }

    public String getName() {
        return name;
    }

    public void execute(CombatContext context) {
        context.attackResolve().resolve(context.source(), context.target(), damage, damageType, statuses);
    }

}
