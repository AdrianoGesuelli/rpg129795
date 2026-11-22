package it.unicam.cs.mpgc.rpg129795.combat.affinities;

public enum Affinity {

    WEAK(200),
    NORMAL(100),
    STRONG(50),
    IMMUNE(0),
    ABSORBS(-100);

    private final int multiplier;

    Affinity(int multiplier) {
        this.multiplier = multiplier;
    }

    public int getMultiplier() {
        return multiplier;
    }

    public boolean isAbsorbing() {
        return this ==  ABSORBS;
    }

}
