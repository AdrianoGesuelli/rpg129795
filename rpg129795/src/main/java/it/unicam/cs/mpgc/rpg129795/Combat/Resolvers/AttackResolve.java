package it.unicam.cs.mpgc.rpg129795.Combat.Resolvers;

import it.unicam.cs.mpgc.rpg129795.Combat.Affinities.Affinity;
import it.unicam.cs.mpgc.rpg129795.Combat.Affinities.DamageType;
import it.unicam.cs.mpgc.rpg129795.Combat.Combatant;
import it.unicam.cs.mpgc.rpg129795.Combat.Stance;

public class AttackResolve {

    private static final int percent = 100;

    private final HitResolve hitResolve;

    public AttackResolve (HitResolve hitResolve) {
        this.hitResolve = hitResolve;
    }

    public AttackResult resolve (Combatant attacker, Combatant target, int baseDamage, DamageType damageType) {
        HitResult outcome = hitResolve.resolve(attacker.getStats().critChance(), target);

        if (outcome == HitResult.MISS) {
            return AttackResult.missed();
        }

        int damage = applyStance(baseDamage, outcome, target.getStance());

        Affinity affinity = target.getAffinity(damageType);

        if (affinity.isAbsorbing()) {
            target.heal(damage);
            return AttackResult.absorbed(outcome, damage);
        }

        damage = damage * affinity.getMultiplier() / percent;
        damage = applyDefence(damage, target.getStats().defense());
        target.takeDamage(damage);
        return AttackResult.damaged(outcome, damage);
    }

    private int applyStance(int damage, HitResult outcome, Stance stance) {
        if (outcome == HitResult.CRITICAL) {
            return damage * stance.getCriticalMultiplier() / percent;
        }
        return damage * stance.getDamageMultiplier() / percent;
    }

    private int applyDefence(int damage, int defencePercent) {
        return damage * (percent - defencePercent) / percent;
    }

}
