package it.unicam.cs.mpgc.rpg129795.character.classes;

import it.unicam.cs.mpgc.rpg129795.character.Stats;
import it.unicam.cs.mpgc.rpg129795.character.StatsModifiers;
import it.unicam.cs.mpgc.rpg129795.character.classes.subclassAbilityList.HolySwordAbility;
import it.unicam.cs.mpgc.rpg129795.character.classes.subclassAbilityList.NoAbility;
import it.unicam.cs.mpgc.rpg129795.character.classes.subclassAbilityList.PoisonDaggersAbility;
import it.unicam.cs.mpgc.rpg129795.items.weapons.StartingWeapons;

import java.util.ArrayList;
import java.util.List;

public final class ClassCatalog {

    public static final String banditId = "bandit_id";
    public static final String paladinId = "paladin_id";

    private ClassCatalog() {}

    public static List<CharacterClass> classes() {
        return List.of(
                new CharacterClass(banditId, "Bandit", "Quick and elusive.",
                        new Stats(85, 5, 15, 20), StartingWeapons.twin_blades_id),
                new CharacterClass(paladinId, "Paladin", "Sturdy and powerful.",
                        new Stats(90, 20, 5, 5), StartingWeapons.greatsword_id));
    }

    public static List<Subclass> subclasses() {
        return List.of(
                new Subclass("poison_daggers", "Poison Daggers", "Your blades can poison the enemy.",
                        banditId, StatsModifiers.none(), new PoisonDaggersAbility()),
                new Subclass("light_boots", "Light Boots", "+10% evasion.",
                        banditId, new StatsModifiers(0, 0, 10, 0), new NoAbility()),
                new Subclass("heavy_armor", "Heavy Armor", "+10% Defense, -5% evasion.",
                        paladinId, new StatsModifiers(0, 10, -5, 0), new NoAbility()),
                new Subclass("holy_sword", "Holy Sword", "Your sword can deal holy damage.",
                        paladinId, StatsModifiers.none(), new HolySwordAbility()));
    }

    public static List<Subclass> subclassesOf(CharacterClass characterClass) {
        List<Subclass> result = new ArrayList<>();
        List<Subclass> all = subclasses();

        for (int i = 0; i < all.size(); i++) {
            if (all.get(i).belongsTo(characterClass)) {
                result.add(all.get(i));
            }
        }
        return List.copyOf(result);
    }

    public static CharacterClass classById(String id) {
        List<CharacterClass> all = classes();

        for (int i = 0; i < all.size(); i++) {
            if (all.get(i).id().equals(id)) {
                return all.get(i);
            }
        }
        throw new IllegalArgumentException("Unknown class id: " + id);
    }

    public static Subclass subclassById(CharacterClass characterClass, String id) {
        List<Subclass> subclasses = subclassesOf(characterClass);

        for (int i = 0; i < subclasses.size(); i++) {
            if (subclasses.get(i).id().equals(id)) {
                return subclasses.get(i);
            }
        }
        throw new IllegalArgumentException("Unknown subclass id: " + id);
    }

}
