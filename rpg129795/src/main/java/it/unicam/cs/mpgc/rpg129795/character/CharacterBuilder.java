package it.unicam.cs.mpgc.rpg129795.character;

import it.unicam.cs.mpgc.rpg129795.character.classes.CharacterClass;
import it.unicam.cs.mpgc.rpg129795.character.classes.Subclass;
import it.unicam.cs.mpgc.rpg129795.items.relics.Relic;
import it.unicam.cs.mpgc.rpg129795.items.weapons.StartingWeapons;
import it.unicam.cs.mpgc.rpg129795.items.weapons.Weapon;

public class CharacterBuilder {

    public static final int maxNameLength = 20;
    private String name;
    private CharacterClass characterClass;
    private Subclass subclass;
    private Relic relic;

    public CharacterBuilder withName(String name) {
        if (name == null || name.trim().isEmpty()) {
            throw new IllegalArgumentException("Name cannot be empty");
        }
        if (name.trim().length() > maxNameLength) {
            throw new IllegalArgumentException("Name cannot be longer than " + maxNameLength + " characters");
        }
        this.name = name.trim();
        return this;
    }

    public CharacterBuilder withClass(CharacterClass characterClass) {
        if (characterClass == null)
            throw new IllegalArgumentException("CharacterClass cannot be null");
        this.characterClass = characterClass;
        this.subclass = null;
        return this;
    }

    public CharacterBuilder withSubclass(Subclass subclass) {
        if (characterClass == null) {
            throw new IllegalArgumentException("Choose the class first");
        }

        if (subclass == null) {
            throw new IllegalArgumentException("Subclass cannot be null");
        }

        if (!subclass.belongsTo(characterClass)) {
            throw new IllegalArgumentException(subclass.name() + " is not a subclass of " + characterClass.name());
        }

        this.subclass = subclass;
        return this;
    }

    public CharacterBuilder withRelic(Relic relic) {
        this.relic = relic;
        return this;
    }

    public PlayerCharacter build() {
        if (name == null || characterClass == null || subclass == null || relic == null) {
            throw new IllegalStateException("Name, class, subclass and relic are required");
        }
        Weapon weapon = StartingWeapons.WeaponById(characterClass.weaponId());
        return new PlayerCharacter(name, characterClass, subclass, relic, weapon);
    }
}
