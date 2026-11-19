package it.unicam.cs.mpgc.rpg129795.Classes;

import it.unicam.cs.mpgc.rpg129795.Character.Stats;
import it.unicam.cs.mpgc.rpg129795.Character.StatsModifiers;
import it.unicam.cs.mpgc.rpg129795.Classes.Abilities.HolySwordAbility;
import it.unicam.cs.mpgc.rpg129795.Classes.Abilities.NoAbility;
import it.unicam.cs.mpgc.rpg129795.Classes.Abilities.PoisonDaggersAbility;
import it.unicam.cs.mpgc.rpg129795.Weapons.StartingWeapons;

import java.util.ArrayList;
import java.util.List;

public final class ClassCatalog {

    public static final String banditId = "bandit_id";
    public static final String paladinId = "paladin_id";

    private ClassCatalog() {}

    public static List<CharacterClass> classes() {
        return List.of(
                new CharacterClass(banditId, "Bandit", "Quick and elusive.",
                        new Stats(100, 10, 10, 10), StartingWeapons.twin_blades_id),
                new CharacterClass(paladinId, "Paladin", "Sturdy and powerful.",
                        new Stats(120, 25, 5, 10), StartingWeapons.greatsword_id));
    }

    public static List<Subclass> subclasses() {
        return List.of(
                new Subclass("poison_daggers", "Poison Daggers", "Your blades can poison the enemy.",
                        banditId, StatsModifiers.none(), new PoisonDaggersAbility()),
                new Subclass("light_boots", "Light Boots", "+10% evasion.",
                        banditId, new StatsModifiers(0, 0, 10, 0), new NoAbility()),
                new Subclass("heavy_armor", "Heavy Armor", "+15% Defense, -5% evasion.",
                        paladinId, new StatsModifiers(0, 15, -5, 0), new NoAbility()),
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
