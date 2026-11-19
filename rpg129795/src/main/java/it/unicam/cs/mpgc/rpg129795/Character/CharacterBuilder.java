package it.unicam.cs.mpgc.rpg129795.Character;

import it.unicam.cs.mpgc.rpg129795.Classes.CharacterClass;
import it.unicam.cs.mpgc.rpg129795.Classes.Subclass;
import it.unicam.cs.mpgc.rpg129795.Weapons.StartingWeapons;
import it.unicam.cs.mpgc.rpg129795.Weapons.Weapon;

public class CharacterBuilder {

    private static final int maxNameLenght = 20;
    private String name;
    private CharacterClass characterClass;
    private Subclass subclass;

    public CharacterBuilder withName(String name) {
        if (name == null || name.trim().isEmpty()) {
            throw new IllegalArgumentException("Name cannot be empty");
        }
        if (name.trim().length() > maxNameLenght) {
            throw new IllegalArgumentException("Name cannot be longer than " + maxNameLenght + " characters");
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

    public PlayerCharacter build() {
        if (name == null || characterClass == null || subclass == null) {
            throw new IllegalStateException("Name, class and subclass are required");
        }
        Weapon weapon = StartingWeapons.WeaponById(characterClass.weaponId());
        return new PlayerCharacter(name, characterClass, subclass, weapon);
    }
}
