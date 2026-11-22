package it.unicam.cs.mpgc.rpg129795.Combat.ActionList;

import it.unicam.cs.mpgc.rpg129795.Combat.Affinities.DamageType;
import it.unicam.cs.mpgc.rpg129795.Combat.CombatActions;
import it.unicam.cs.mpgc.rpg129795.Combat.CombatContext;
import it.unicam.cs.mpgc.rpg129795.Combat.DamageRange;
import it.unicam.cs.mpgc.rpg129795.Combat.Resolvers.ActionResult;
import it.unicam.cs.mpgc.rpg129795.Combat.Resolvers.AttackResult;
import it.unicam.cs.mpgc.rpg129795.Combat.Status.StatusApplication;

import java.util.List;

public final class AttackAction implements CombatActions {

    private final String name;
    private final DamageRange damage;
    private final DamageType damageType;
    private final List<StatusApplication> statuses;

    public AttackAction(String name, DamageRange damage, DamageType damageType, List<StatusApplication> statuses) {
        if (name == null || damage == null || damageType == null || statuses == null) {
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

    public ActionResult execute(CombatContext context) {
        AttackResult result = context.attackResolve().resolve(context.source(), context.target(), damage, damageType, statuses);
        return new ActionResult(name, result);
    }

}
