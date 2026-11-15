package it.unicam.cs.mpgc.rpg129795.Classes;

import it.unicam.cs.mpgc.rpg129795.Character.Stats;

public record CharacterClass(String id, String name, String description, Stats baseStats, String weaponId) {

    public CharacterClass {
        if (id == null || name == null || description == null || baseStats == null || weaponId == null) {
            throw new NullPointerException("Invalid data");
        }
    }

}
