package it.unicam.cs.mpgc.rpg129795.Combat.Resolvers;

import it.unicam.cs.mpgc.rpg129795.Combat.CombatRules;
import it.unicam.cs.mpgc.rpg129795.Combat.Combatant;

import static it.unicam.cs.mpgc.rpg129795.Combat.Resolvers.HitResult.MISS;

public class AttackResolve {

    private static final int percent = 100;

    private final HitResolve hitResolve;

    public AttackResolve (HitResolve hitResolve) {
        this.hitResolve = hitResolve;
    }

    public AttackResult resolve (Combatant attacker, Combatant target, int baseDamage, int damageType) {
        HitResult hitResult = hitResolve.resolve(attacker.getStats().critChance(), target);

        if (hitResult == HitResult.MISS) {
            return AttackResult.miss();
        }

        return AttackResult.miss(); //TODO completare
    }


}
