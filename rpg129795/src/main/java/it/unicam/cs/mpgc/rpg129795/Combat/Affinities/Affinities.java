package it.unicam.cs.mpgc.rpg129795.Combat.Affinities;

public enum Affinities {

    WEAK(200),
    NORMAL(100),
    STRONG(50),
    IMMUNE(0),
    ABSORBS(-100);

    private final int multiplier;

    Affinities(int multiplier) {
        this.multiplier = multiplier;
    }

    public int getMultiplier() {
        return multiplier;
    }

}
