package it.unicam.cs.mpgc.rpg129795.Combat.Resolvers;

import it.unicam.cs.mpgc.rpg129795.Combat.Affinities.Affinity;
import it.unicam.cs.mpgc.rpg129795.Combat.Affinities.DamageType;
import it.unicam.cs.mpgc.rpg129795.Combat.Combatant;
import it.unicam.cs.mpgc.rpg129795.Combat.DamageRange;
import it.unicam.cs.mpgc.rpg129795.Combat.Stance;
import it.unicam.cs.mpgc.rpg129795.Combat.Status.StatusApplication;
import it.unicam.cs.mpgc.rpg129795.Combat.Status.StatusType;

import java.util.List;
import java.util.Random;

public class AttackResolve {

    private static final int percent = 100;
    private static final int curseMaxHpPercent = 25;
    private static final List<StatusType> curseEffects =
            List.of(StatusType.NECROSIS, StatusType.VULNERABLE, StatusType.WEAKENED);

    private final HitResolve hitResolve;
    private final Random random;

    public AttackResolve(HitResolve hitResolve, Random random) {
        if (hitResolve == null || random == null) {
            throw new IllegalArgumentException("Arguments cannot be null");
        }
        this.hitResolve = hitResolve;
        this.random = random;
    }

    //? Attacco normale (usato dalle azioni del giocatore)
    public AttackResult resolve(Combatant attacker, Combatant target, DamageRange damageRange,
                                DamageType damageType, List<StatusApplication> statuses) {
        return resolve(attacker, target, damageRange, damageType, statuses, AttackModifiers.none);
    }

    //? Attacco con modificatori (es. dopo un'analisi)
    public AttackResult resolve(Combatant attacker, Combatant target, DamageRange damageRange,
                                DamageType damageType, List<StatusApplication> statuses,
                                AttackModifiers modifiers) {

        int critChance = attacker.getStats().critChance() + modifiers.critBonus();
        HitResult outcome = hitResolve.resolve(critChance, target);

        if (outcome == HitResult.MISS) {
            return AttackResult.missed();
        }

        int baseDamage = damageRange.roll(random) * modifiers.damagePercent() / percent;
        int damage = applyStance(baseDamage, outcome, target);

        Affinity affinity = target.getAffinity(damageType);

        if (affinity.isAbsorbing()) {
            target.heal(damage);
            return AttackResult.absorbed(outcome, damage);
        }

        damage = damage * affinity.getMultiplier() / percent;
        damage = applyDefence(damage, target.getStats().defense());
        damage = damage * attacker.getActiveEffects().getDamageDealtPercent() / percent;
        damage = damage * target.getActiveEffects().getDamageTakenPercent() / percent;
        target.takeDamage(damage);
        applyStatuses(target, outcome, statuses);
        return AttackResult.damaged(outcome, damage);
    }

    //? La maledizione del Signore dei Demoni: una percentuale degli HP massimi,
    //? ignora Difesa e affinità, non fa critico. In difesa non applica nessun effetto
    public AttackResult resolveCurse(Combatant target) {
        HitResult outcome = hitResolve.resolve(0, target);

        if (outcome == HitResult.MISS) {
            return AttackResult.missed();
        }

        int damage = target.getStats().maxHp() * curseMaxHpPercent / percent;
        damage = damage * target.getCurseDamagePercent() / percent;
        damage = damage * target.getStanceDamageMultiplier() / percent;
        damage = damage * target.getActiveEffects().getDamageTakenPercent() / percent;
        target.takeDamage(damage);

        if (target.getStance() != Stance.DEFENSE && !target.blocksCurseEffects()) {
            for (int i = 0; i < curseEffects.size(); i++) {
                target.getActiveEffects().applyEffect(curseEffects.get(i));
            }
        }
        return AttackResult.damaged(outcome, damage);
    }

    //? I critici usano il moltiplicatore della postura, i colpi normali quello del bersaglio
    //? (che la sua reliquia può cambiare, es. Featherbone Charm in Dodge)
    private int applyStance(int damage, HitResult outcome, Combatant target) {
        if (outcome == HitResult.CRITICAL) {
            return damage * target.getStance().getCriticalMultiplier() / percent;
        }
        return damage * target.getStanceDamageMultiplier() / percent;
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

    //? I debuff senza tipo di danno non hanno immunità
    private boolean isImmune(Combatant target, StatusApplication status) {
        if (!status.type().hasDamageType()) {
            return false;
        }
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
        return random.nextInt(1, percent + 1);
    }
}