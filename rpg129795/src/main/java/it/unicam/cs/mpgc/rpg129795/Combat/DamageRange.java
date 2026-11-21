package it.unicam.cs.mpgc.rpg129795.Combat;

import java.util.Random;

public record DamageRange (int min, int max) {

    private static final int percent = 100;

    public DamageRange {
        if (min < 0 || max < min) {
            throw new IllegalArgumentException("Invalid damage range: " + min + "-" + max);
        }
    }

    public int roll(Random random) {
        return random.nextInt(min, max + 1);
    }

    //? Lo stesso intervallo ridotto in percentuale (usato dalle abilità: 75% dell'arma, ecc.)
    public DamageRange scaled(int percent) {
        return new DamageRange(min * percent / percent, max * percent / percent);
    }

    public String toString() {
        return min + "-" + max;
    }
}
