package it.unicam.cs.mpgc.rpg129795.Character;

import it.unicam.cs.mpgc.rpg129795.Classes.CharacterClass;
import it.unicam.cs.mpgc.rpg129795.Classes.Subclass;
import it.unicam.cs.mpgc.rpg129795.Weapons.StartingWeapons;
import it.unicam.cs.mpgc.rpg129795.Weapons.Weapon;

public class CharacterBuilder {

    private CharacterClass characterClass;
    private Subclass subclass;

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
        if (characterClass == null || subclass == null) {
            throw new IllegalStateException("Both class and subclass are required");
        }
        Weapon weapon = StartingWeapons.WeaponById(characterClass.weaponId());
        return new PlayerCharacter(characterClass, subclass, weapon);
    }
}
