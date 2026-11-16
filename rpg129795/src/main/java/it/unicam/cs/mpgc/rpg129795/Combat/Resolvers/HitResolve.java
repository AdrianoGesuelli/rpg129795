package it.unicam.cs.mpgc.rpg129795.Combat.Resolvers;

import it.unicam.cs.mpgc.rpg129795.Combat.Combatant;

import java.util.Random;

public class HitResolve {

    private static final int diceFaces = 100;

    private final Random random;

    public HitResolve(Random random) {
        this.random = random;
    }

    public HitResult resolve(int criticalChance, Combatant target) {
        int roll = random.nextInt(diceFaces);

        if (roll > diceFaces - criticalChance) { //* 100 - % di critico
            return HitResult.CRITICAL; //* perché il roll è > dei numeri non critici
        }

        int evasion = target.getStance().getEvasionBonus(target.getStats().evasion()); //* l'evasione del target

        if (roll <= evasion) {
            return HitResult.MISS;
        }

        return HitResult.HIT;
    }
}
