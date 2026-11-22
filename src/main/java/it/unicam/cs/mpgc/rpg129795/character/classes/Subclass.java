package it.unicam.cs.mpgc.rpg129795.character.classes;

import it.unicam.cs.mpgc.rpg129795.character.StatsModifiers;
import it.unicam.cs.mpgc.rpg129795.character.classes.subclassAbilityList.SubclassWeaponAbility;

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
