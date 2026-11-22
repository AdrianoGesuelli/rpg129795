package it.unicam.cs.mpgc.rpg129795.Gui.Presentation;

import it.unicam.cs.mpgc.rpg129795.Character.Stats;
import it.unicam.cs.mpgc.rpg129795.Classes.CharacterClass;
import it.unicam.cs.mpgc.rpg129795.Classes.ClassCatalog;
import it.unicam.cs.mpgc.rpg129795.Classes.Subclass;
import it.unicam.cs.mpgc.rpg129795.Combat.Affinities.Affinity;
import it.unicam.cs.mpgc.rpg129795.Combat.Affinities.DamageType;
import it.unicam.cs.mpgc.rpg129795.Combat.Stance;
import it.unicam.cs.mpgc.rpg129795.Items.Relic.Relic;
import it.unicam.cs.mpgc.rpg129795.Items.Relic.RelicCatalog;
import it.unicam.cs.mpgc.rpg129795.Weapons.StartingWeapons;
import it.unicam.cs.mpgc.rpg129795.Weapons.Weapon;

import java.util.ArrayList;
import java.util.List;

public final class TutorialContent {

    private TutorialContent() {}

    //! Azioni ---------------------

    public static List<TutorialBlock> actions() {
        return List.of(
                new TutorialBlock("Attack",
                        "Strike with your weapon. Every attack rolls a d100:\n"
                                + "- a roll above (100 - your Critical chance) is a critical hit and deals double damage;\n"
                                + "- otherwise, a roll equal to or below the target's Evasion is a miss;\n"
                                + "- anything else is a normal hit.\n"
                                + "If the attack lands, its damage is rolled within the weapon's range "
                                + "(for example 11-18): every blow is different. Enemy attacks work the same way.\n"
                                + "The target's Defense then reduces the damage by its percentage."),
                new TutorialBlock("Class abilities",
                        "Some subclasses grant an extra action. Its damage range is a portion of your weapon's range.\n"
                                + "Poisoned Attack (Poison Daggers): a weaker strike that always poisons on hit.\n"
                                + "Holy Strike (Holy Sword): a weaker strike that deals Holy damage, "
                                + "then needs a few turns to recharge."),
                new TutorialBlock("Defend", describeStance(Stance.DEFENSE)
                        + "\nA defensive stance: you cannot dodge, but every blow hurts less "
                        + "and you may shrug off statuses."),
                new TutorialBlock("Dodge", describeStance(Stance.DODGE)
                        + "\nA risky stance: you are much harder to hit, but a blow that lands hurts more."),
                new TutorialBlock("Stances last one turn",
                        "Defend and Dodge protect you until your next turn begins, then you return to a neutral stance."),
                new TutorialBlock("Observe",
                        "Spend your turn studying the enemy: you learn what it fears, what it resists "
                                + "and what it feeds upon.\n"
                                + "It can be used once per fight, and the enemy still acts during that turn."));
    }

    private static String describeStance(Stance stance) {
        return "Until your next turn:\n"
                + "- evasion: " + describeEvasion(stance) + ";\n"
                + "- damage taken from normal hits: " + stance.getDamageMultiplier() + "%;\n"
                + "- damage taken from critical hits: " + stance.getCriticalMultiplier() + "%;\n"
                + "- chance to block a status: " + stance.getStatusBlockChance() + "% (critical hits are never blocked).";
    }

    private static String describeEvasion(Stance stance) {
        int evasionWithoutBase = stance.getAdjustesEvasion(0);
        int evasionWithBase = stance.getAdjustesEvasion(1);
        if (evasionWithBase == 0) {
            return "none, you cannot dodge";
        }
        return "+" + evasionWithoutBase + "%";
    }

    //! Tipi di danno e affinità ---------------------

    public static List<TutorialBlock> damageAndAffinities() {
        String types = "";
        DamageType[] damageTypes = DamageType.values();
        for (int i = 0; i < damageTypes.length; i++) {
            types += "- " + damageTypes[i] + ": " + describeDamageType(damageTypes[i]) + "\n";
        }

        String affinities = "Every creature reacts differently to each damage type:\n";
        Affinity[] values = Affinity.values();
        for (int i = 0; i < values.length; i++) {
            affinities += "- " + values[i] + ": " + describeAffinity(values[i]) + "\n";
        }
        affinities += "\nDamage is calculated in this order: rolled damage, stance, affinity, then Defense.\n"
                + "Poison and Necrosis always deal a fixed amount.\n"
                + "An enemy's affinities are hidden: try different attacks and watch the numbers.";

        return List.of(
                new TutorialBlock("Damage types", types.trim()),
                new TutorialBlock("Affinities", affinities));
    }

    private static String describeDamageType(DamageType type) {
        if (type == DamageType.PHYSICAL) {
            return "weapons, claws and fangs. The most common damage";
        }
        if (type == DamageType.MAGIC) {
            return "arcane energy, chants, incantations and spells";
        }
        if (type == DamageType.FIRE) {
            return "flames and burning heat";
        }
        if (type == DamageType.WATER) {
            return "crushing waves and arctic cold";
        }
        if (type == DamageType.WIND) {
            return "violent gusts and storms";
        }
        if (type == DamageType.EARTH) {
            return "stone and tremors";
        }
        if (type == DamageType.HOLY) {
            return "the light of the old gods, feared by the undead and the corrupted";
        }
        if (type == DamageType.DARK) {
            return "the power of the abyss";
        }
        if (type == DamageType.POISON) {
            return "a status that hurts at the start of the victim's turn for 3 turns. "
                    + "Each new application adds a stack and makes it stronger. Ignores Defense";
        }
        return "a status that hurts at the end of the victim's turn for 3 turns. Ignores Defense";
    }

    private static String describeAffinity(Affinity affinity) {
        if (affinity.isAbsorbing()) {
            return "the attack heals the target instead of hurting it";
        }
        if (affinity.getMultiplier() == 0) {
            return "no damage, and the related status cannot be applied";
        }
        return affinity.getMultiplier() + "% damage";
    }

    //! Classi e sottoclassi ---------------------

    public static List<TutorialBlock> classes() {
        List<TutorialBlock> blocks = new ArrayList<>();
        List<CharacterClass> classes = ClassCatalog.classes();

        for (int i = 0; i < classes.size(); i++) {
            CharacterClass characterClass = classes.get(i);
            Weapon weapon = StartingWeapons.WeaponById(characterClass.weaponId());

            String text = characterClass.description() + "\n"
                    + "Weapon: " + weapon.name() + " (" + weapon.damage() + " " + weapon.damageType() + ")\n"
                    + describeStats(characterClass.baseStats()) + "\n\n"
                    + "Subclasses:";

            List<Subclass> subclasses = ClassCatalog.subclassesOf(characterClass);
            for (int j = 0; j < subclasses.size(); j++) {
                Subclass subclass = subclasses.get(j);
                text += "\n- " + subclass.name() + ": " + subclass.description() + "\n"
                        + "   " + describeStats(characterClass.baseStats().with(subclass.modifiers()));
            }
            blocks.add(new TutorialBlock(characterClass.name(), text));
        }
        return blocks;
    }

    private static String describeStats(Stats stats) {
        return "HP " + stats.maxHp()
                + "   Defense " + stats.defense() + "%"
                + "   Evasion " + stats.evasion() + "%"
                + "   Critical " + stats.critChance() + "%";
    }

    //! Reliquie ---------------------

    public static List<TutorialBlock> relics() {
        List<TutorialBlock> blocks = new ArrayList<>();
        blocks.add(new TutorialBlock("What relics are",
                "Before entering the castle you choose one relic. Its power is passive: "
                        + "it is always active and never needs to be used.\n"
                        + "Any class and subclass can carry any relic."));
        List<Relic> relics = RelicCatalog.relics();
        for (int i = 0; i < relics.size(); i++) {
            Relic relic = relics.get(i);
            blocks.add(new TutorialBlock(relic.name(), relic.description() + "\n\nEffect: " + relic.effect()));
        }
        return blocks;
    }

    //! Nemici e comportamenti ---------------------

    public static List<TutorialBlock> enemies() {
        return List.of(
                new TutorialBlock("A pool of actions",
                        "Every enemy has its own pool of actions. Each turn it draws one at random.\n"
                                + "Every action has a weight: the higher the weight, the more often it is chosen. "
                                + "An action with weight 4 comes up four times as often as one with weight 1."),
                new TutorialBlock("Analyze",
                        "Every enemy, without exception, can spend a turn analyzing you: "
                                + "\"It is preparing a precise attack!\".\n"
                                + "Its next attack is a normal one with +50% critical chance and +50% damage.\n"
                                + "An enemy never analyzes twice in a row.\n"
                                + "Tip: while defending, critical hits do not deal double damage."),
                new TutorialBlock("Curse of the Demon Lord",
                        "Every enemy can call upon the Demon Lord. When it does, you will have a bad feeling: "
                                + "on its next turn the curse strikes.\n"
                                + "It takes 25% of your maximum HP, ignoring Defense, and if it hits you also suffer:\n"
                                + "- NECROSIS;\n"
                                + "- VULNERABLE: +50% damage taken for 5 turns;\n"
                                + "- WEAKENED: -50% damage dealt with your attacks for 5 turns.\n"
                                + "Defend: you take 70% of the damage and none of the effects.\n"
                                + "Dodge: if the curse misses, nothing happens; if it hits, it hurts even more."));
    }
}