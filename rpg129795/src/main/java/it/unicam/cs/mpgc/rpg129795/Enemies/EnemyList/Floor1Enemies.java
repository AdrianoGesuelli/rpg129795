package it.unicam.cs.mpgc.rpg129795.Enemies.EnemyList;

import it.unicam.cs.mpgc.rpg129795.Character.Stats;
import it.unicam.cs.mpgc.rpg129795.Combat.Affinities.Affinity;
import it.unicam.cs.mpgc.rpg129795.Combat.Affinities.AffinityTable;
import it.unicam.cs.mpgc.rpg129795.Combat.Affinities.DamageType;
import it.unicam.cs.mpgc.rpg129795.Combat.DamageRange;
import it.unicam.cs.mpgc.rpg129795.Combat.Status.StatusApplication;
import it.unicam.cs.mpgc.rpg129795.Combat.Status.StatusType;
import it.unicam.cs.mpgc.rpg129795.Enemies.ActionsAndBehavior.EnemyAction;
import it.unicam.cs.mpgc.rpg129795.Enemies.ActionsAndBehavior.WeightedAction;
import it.unicam.cs.mpgc.rpg129795.Enemies.EnemyTemplate;
import it.unicam.cs.mpgc.rpg129795.Enemies.EnemyTier;

import java.util.List;
import java.util.Map;

public class Floor1Enemies {

    private Floor1Enemies() {}


    public static List<EnemyTemplate> all() {
        return List.of(cryptInsectoid(), lizardfolkBerserker(), darkMage(), fleshOoze());
    }

    public static EnemyTemplate cryptInsectoid() {
        EnemyAction mandibles = new EnemyAction("mandibles_id", "Mandibles", new DamageRange(9, 15), DamageType.PHYSICAL);
        EnemyAction stinger = new EnemyAction("stinger_id",  "Stinger", new DamageRange(6, 10), DamageType.PHYSICAL,
                List.of(new StatusApplication(StatusType.POISON, 60)));
        EnemyAction acidSpray = new EnemyAction("acid_spray_id", "Acid Spray", new DamageRange(3, 6), DamageType.POISON,
                List.of(new StatusApplication(StatusType.POISON, 60)));

        return new EnemyTemplate("crypt_insectoid_id", "Crypt Insectoid", EnemyTier.FLOOR_1,
                new Stats(48, 5, 20, 10),
                new AffinityTable(Map.of(
                        DamageType.WIND, Affinity.WEAK,
                        DamageType.FIRE, Affinity.WEAK,
                        DamageType.POISON, Affinity.IMMUNE)),
                List.of(new WeightedAction(mandibles, 4),
                        new WeightedAction(stinger, 2),
                        new WeightedAction(acidSpray, 2)),
                ("A chitinous horror the size of a hound. " +
                "Its frail wings could never withstand a strong gust of wind, its body is saturated with its own venom, " +
                "and it keeps a safe distance from the torches that light the corridor."));

    }

    public static EnemyTemplate lizardfolkBerserker() {
        EnemyAction savageSlash = new EnemyAction("savage_slash_id", "Savage Slash", new DamageRange(9, 15), DamageType.PHYSICAL);
        EnemyAction bite = new EnemyAction("bite_id", "Bite", new DamageRange(6, 11), DamageType.PHYSICAL);
        EnemyAction blindFury = new EnemyAction("blind_fury_id", "Blind Fury", new DamageRange(15, 25), DamageType.PHYSICAL);

        return new EnemyTemplate("lizardfolk_berserker_id", "Lizardfolk Berserker", EnemyTier.FLOOR_1,
                new Stats(45, 0, 5, 15),
                new AffinityTable(Map.of(
                        DamageType.WATER, Affinity.WEAK,
                        DamageType.DARK, Affinity.WEAK,
                        DamageType.EARTH, Affinity.STRONG,
                        DamageType.PHYSICAL, Affinity.STRONG,
                        DamageType.FIRE, Affinity.IMMUNE,
                        DamageType.POISON, Affinity.IMMUNE)),
                List.of(new WeightedAction(savageSlash, 4),
                        new WeightedAction(bite, 3),
                        new WeightedAction(blindFury, 1)),
                ("A red-scaled lizardfolk foaming with rage. " +
                "Like all its kind it is cold-blooded, and its thick scales offer great protection against physical blows. " +
                "Their reddish hue marks a tribe born among volcanoes."));
    }

    public static EnemyTemplate darkMage() {
        EnemyAction eldritchBlast = new EnemyAction("eldritch_blast_id", "Eldritch Blast", new DamageRange(12, 20), DamageType.DARK);
        EnemyAction rottingCurse = new EnemyAction("rotting_curse_id", "Rotting Curse", new DamageRange(5, 8), DamageType.NECROSIS,
                List.of(new StatusApplication(StatusType.NECROSIS, 80)));
        EnemyAction darkLightning = new EnemyAction("dark_lightning_id", "Dark Lightning", new DamageRange(9, 16), DamageType.DARK);

        return new EnemyTemplate("dark_mage_id", "Dark Mage", EnemyTier.FLOOR_1,
                new Stats(45, 0, 15, 20),
                new AffinityTable(Map.of(
                        DamageType.HOLY, Affinity.WEAK,
                        DamageType.PHYSICAL, Affinity.WEAK,
                        DamageType.MAGIC, Affinity.STRONG,
                        DamageType.DARK, Affinity.ABSORBS,
                        DamageType.NECROSIS, Affinity.ABSORBS)),
                List.of(new WeightedAction(eldritchBlast, 3),
                        new WeightedAction(rottingCurse, 2),
                        new WeightedAction(darkLightning, 3)),
                ("A hooded figure muttering in a forgotten tongue. " +
                "Beneath the robe there is nothing but skin and bones: a fragile body. " +
                "He sold his soul to the darkness and draws his power from it, " +
                "and long years of sorcery have made him accustomed to magic."));
    }

    public static EnemyTemplate fleshOoze() {
        EnemyAction engulf = new EnemyAction("engulf_id", "Engulf", new DamageRange(6, 10), DamageType.PHYSICAL);
        EnemyAction putridSpray = new EnemyAction("putrid_spray_id", "Putrid Spray", new DamageRange(3, 6), DamageType.NECROSIS,
                List.of(new StatusApplication(StatusType.NECROSIS, 80)));
        EnemyAction boneStab = new EnemyAction("bone_stab_id",  "Bone Stab", new DamageRange(8, 13), DamageType.PHYSICAL,
                List.of(new StatusApplication(StatusType.NECROSIS, 30)));

        return new EnemyTemplate("flesh_ooze_id", "Flesh Ooze", EnemyTier.FLOOR_1,
                new Stats(55, 0, 0, 5),
                new AffinityTable(Map.of(
                        DamageType.HOLY, Affinity.WEAK,
                        DamageType.FIRE, Affinity.WEAK,
                        DamageType.PHYSICAL, Affinity.STRONG,
                        DamageType.DARK, Affinity.STRONG,
                        DamageType.NECROSIS, Affinity.ABSORBS)),
                List.of(new WeightedAction(engulf, 3),
                        new WeightedAction(putridSpray, 2),
                        new WeightedAction(boneStab, 3)),
                ("A heaving mass of rotten flesh and bone. " +
                "A blade will sink in, only for the flesh to close up behind it. " +
                "It moves while avoiding the hot spots in the corridor, and it seems to be made of rot, and to feed upon it."));
    }
}
