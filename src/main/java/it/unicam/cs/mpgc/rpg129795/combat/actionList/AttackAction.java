package it.unicam.cs.mpgc.rpg129795.combat.actionList;

import it.unicam.cs.mpgc.rpg129795.combat.affinities.DamageType;
import it.unicam.cs.mpgc.rpg129795.combat.CombatAction;
import it.unicam.cs.mpgc.rpg129795.combat.CombatContext;
import it.unicam.cs.mpgc.rpg129795.combat.DamageRange;
import it.unicam.cs.mpgc.rpg129795.combat.resolvers.ActionResult;
import it.unicam.cs.mpgc.rpg129795.combat.resolvers.AttackResult;
import it.unicam.cs.mpgc.rpg129795.combat.status.StatusApplication;

import java.util.List;

public final class AttackAction implements CombatAction {

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
        AttackResult result = context.attackResolver().resolve(context.source(), context.target(), damage, damageType, statuses);
        return new ActionResult(name, result);
    }

}
