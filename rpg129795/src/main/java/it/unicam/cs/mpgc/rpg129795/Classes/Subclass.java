package it.unicam.cs.mpgc.rpg129795.Classes;

import it.unicam.cs.mpgc.rpg129795.Character.StatsModifiers;
import it.unicam.cs.mpgc.rpg129795.Classes.SubclassAbilityList.SubclassWeaponAbility;

public record Subclass (String id, String name, String description, String classId,
                        StatsModifiers modifiers, SubclassWeaponAbility ability) {

    public Subclass {
        if (id == null || name == null || description == null || classId == null || modifiers == null || ability == null) {
            throw new IllegalArgumentException("Invalid subclass");
        }
    }

    public boolean belongsTo (CharacterClass characterClass) {
        return classId.equals(characterClass.id());
    }
}
