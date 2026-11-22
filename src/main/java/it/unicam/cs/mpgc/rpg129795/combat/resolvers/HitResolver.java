package it.unicam.cs.mpgc.rpg129795.combat.resolvers;

import it.unicam.cs.mpgc.rpg129795.combat.Combatant;

import java.util.Random;

public class HitResolver {

    private static final int diceFaces = 100;

    private final Random random;

    public HitResolver(Random random) {
        this.random = random;
    }

    public HitResult resolve(int criticalChance, Combatant target) {
        int roll = random.nextInt(1, diceFaces + 1); //* tiro da 1 a 100

        if (roll > diceFaces - criticalChance) { //* 100 - % di critico
            return HitResult.CRITICAL; //* perché il roll è > dei numeri non critici
        }

        int evasion = target.getStance().getAdjustesEvasion(target.getStats().evasion()); //* l'evasione del target

        if (roll <= evasion) {
            return HitResult.MISS;
        }

        return HitResult.HIT;
    }
}
