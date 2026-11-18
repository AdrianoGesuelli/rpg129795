package it.unicam.cs.mpgc.rpg129795.Character;

public record Stats(int maxHp, int defense, int evasion, int critChance) {

    public Stats{
        if (maxHp <= 0) {
            throw new IllegalArgumentException("maxHp must be greater than 0");
        }

        if (defense < 0 || evasion < 0 || critChance < 0) {
            throw new IllegalArgumentException("Percentages must be greater than 0");
        }
    }

    //? Ottengo le statistiche con i modificatori applicati
    public Stats with(StatsModifiers modifiers) {
        return new Stats(
                maxHp + modifiers.maxHp(),
                defense + modifiers.defense(),
                evasion + modifiers.evasion(),
                critChance + modifiers.critChance());
    }

}
