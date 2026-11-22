package it.unicam.cs.mpgc.rpg129795.combat.affinities;

import java.util.Map;

public record AffinityTable(Map<DamageType, Affinity> affinities) {

    public AffinityTable {
        if (affinities == null) {
            throw new IllegalArgumentException("Affinity table cannot be null.");
        }

        affinities = Map.copyOf(affinities); //? Copia immutabile
    }

    public static AffinityTable empty() {
        return new AffinityTable(Map.of()); //? Crea una mappa vuota, poi una tabella passandole la mappa vuota
    }

    //? Se l'Affinity non è specificata, è NORMAL
    public Affinity of(DamageType type) {
        return affinities.getOrDefault(type, Affinity.NORMAL);
    }
    //* In questo modo evito di dover scrivere casi di Affinity Neutrali
}
