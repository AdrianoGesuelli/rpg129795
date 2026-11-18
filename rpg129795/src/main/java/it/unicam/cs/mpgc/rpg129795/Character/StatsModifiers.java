package it.unicam.cs.mpgc.rpg129795.Character;

public record StatsModifiers(int maxHp, int defense, int evasion, int critChance) {

    private static final StatsModifiers none = new StatsModifiers(0, 0, 0, 0);

    public static StatsModifiers none() {
        return none;
    }

}
