package it.unicam.cs.mpgc.rpg129795.Combat.Resolvers;

import it.unicam.cs.mpgc.rpg129795.Combat.Affinities.Affinity;
import it.unicam.cs.mpgc.rpg129795.Combat.Affinities.DamageType;
import it.unicam.cs.mpgc.rpg129795.Combat.Combatant;
import it.unicam.cs.mpgc.rpg129795.Combat.Stance;
import it.unicam.cs.mpgc.rpg129795.Combat.Status.StatusApplication;

import java.util.List;
import java.util.Random;

public class AttackResolve {

    private static final int percent = 100;

    private final HitResolve hitResolve;
    private final Random random;

    public AttackResolve (HitResolve hitResolve, Random random) {
        if (hitResolve == null || random == null) {
            throw new IllegalArgumentException("Arguments cannot be null");
        }

        this.hitResolve = hitResolve;
        this.random = random;
    }

    public AttackResult resolve (Combatant attacker, Combatant target, int baseDamage,
                                 DamageType damageType, List<StatusApplication> statuses) {

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
        applyStatuses (target, outcome, statuses);
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

    private void applyStatuses(Combatant target, HitResult outcome, List<StatusApplication> statuses) {
        for (int i = 0; i < statuses.size(); i++) {
            StatusApplication status = statuses.get(i);

            if (isImmune(target, status) || isBlocked(target, outcome)) {
                continue;
            }

            if (roll() <= status.chance()) {
                target.getActiveEffects().applyEffect(status.type());
            }
        }
    }

    //? controllo chi è immune al tipo di danno dello status, danno che non riceverà
    private boolean isImmune(Combatant target, StatusApplication status) {
        return target.getAffinity(status.type().getDamageType()) == Affinity.IMMUNE;
    }

    //? il blocco degli status via stance non avviene se il colpo è un critico
    private boolean isBlocked(Combatant target, HitResult outcome) {
        if (outcome == HitResult.CRITICAL) {
            return false;
        }

        return roll() <= target.getStance().getStatusBlockChance();
    }

    private int roll() {
        return random.nextInt(100);
    }

}
