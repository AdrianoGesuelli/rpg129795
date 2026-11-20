package it.unicam.cs.mpgc.rpg129795.Enemies.Catalog;

import it.unicam.cs.mpgc.rpg129795.Character.Stats;
import it.unicam.cs.mpgc.rpg129795.Combat.Affinities.Affinity;
import it.unicam.cs.mpgc.rpg129795.Combat.Affinities.AffinityTable;
import it.unicam.cs.mpgc.rpg129795.Combat.Affinities.DamageType;
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
        EnemyAction mandibles = new EnemyAction("mandibles_id", "Mandibles", 12, DamageType.PHYSICAL);
        EnemyAction stinger = new EnemyAction("stinger_id",  "Stinger", 8, DamageType.PHYSICAL,
                List.of(new StatusApplication(StatusType.POISON, 60)));
        EnemyAction acidSpray = new EnemyAction("acid_spray_id", "Acid Spray", 5, DamageType.POISON,
                List.of(new StatusApplication(StatusType.POISON, 60)));

        return new EnemyTemplate("crypt_insectoid_id", "Crypt Insectoid", EnemyTier.FLOOR_1,
                new Stats(48, 5, 20, 10),
                new AffinityTable(Map.of(
                        DamageType.WIND, Affinity.WEAK,
                        DamageType.FIRE, Affinity.WEAK,
                        DamageType.POISON, Affinity.IMMUNE)),
                List.of(new WeightedAction(mandibles, 5),
                        new WeightedAction(stinger, 2),
                        new WeightedAction(acidSpray, 3)),
                List.of("Its frail wings  do not seem capable of withstanding strong gusts of wind.",
                        "It appears saturated with its own venom.",
                        "It keeps a safe distance from the torches that illuminate the corridor."));

    }

    public static EnemyTemplate lizardfolkBerserker() {
        EnemyAction savageSlash = new EnemyAction("savage_slash_id", "Savage Slash", 12, DamageType.PHYSICAL);
        EnemyAction bite = new EnemyAction("bite_id", "Bite", 9, DamageType.PHYSICAL);
        EnemyAction blindFury = new EnemyAction("blind_fury_id", "Blind Fury", 20, DamageType.PHYSICAL);

        return new EnemyTemplate("lizardfolk_berserker_id", "Lizardfolk Berserker", EnemyTier.FLOOR_1,
                new Stats(45, 0, 5, 15),
                new AffinityTable(Map.of(
                        DamageType.WATER, Affinity.WEAK,
                        DamageType.DARK, Affinity.WEAK,
                        DamageType.EARTH, Affinity.STRONG,
                        DamageType.PHYSICAL, Affinity.STRONG,
                        DamageType.FIRE, Affinity.IMMUNE,
                        DamageType.POISON, Affinity.IMMUNE)),
                List.of(new WeightedAction(savageSlash, 5),
                        new WeightedAction(bite, 4),
                        new WeightedAction(blindFury, 1)),
                List.of("Lizardfolks tend to be cold-blooded creatures.",
                        "Their scales offer great protection against physical impacts.",
                        "The reddish color of the scales indicates that this one belongs to a tribe native to colcanic regions"));
    }

    public static EnemyTemplate darkMage() {
        EnemyAction eldritchBlast = new EnemyAction("eldritch_blast_id", "Eldritch Blast", 17, DamageType.DARK);
        EnemyAction rottingCurse = new EnemyAction("rotting_curse_id", "Rotting Curse", 9, DamageType.NECROSIS,
                List.of(new StatusApplication(StatusType.NECROSIS, 80)));
        EnemyAction darkLightning = new EnemyAction("dark_lightning_id", "Dark Lightning", 14, DamageType.DARK);

        return new EnemyTemplate("dark_mage_id", "Dark Mage", EnemyTier.FLOOR_1,
                new Stats(45, 0, 15, 20),
                new AffinityTable(Map.of(
                        DamageType.HOLY, Affinity.WEAK,
                        DamageType.PHYSICAL, Affinity.WEAK,
                        DamageType.MAGIC, Affinity.STRONG,
                        DamageType.DARK, Affinity.ABSORBS,
                        DamageType.NECROSIS, Affinity.ABSORBS)),
                List.of(new WeightedAction(eldritchBlast, 3),
                        new WeightedAction(rottingCurse, 3),
                        new WeightedAction(darkLightning, 4)),
                List.of("Beneath the robe there is nothing but skin and bones. Quite a fragile body.",
                        "He has sold his soul to the darkness, and from it, he draws his power.",
                        "He is a skilled mage, accustomed to living with magic."));
    }

    public static EnemyTemplate fleshOoze() {
        EnemyAction engulf = new EnemyAction("engulf_id", "Engulf", 8, DamageType.PHYSICAL);
        EnemyAction putridSpray = new EnemyAction("putrid_spray_id", "Putrid Spray", 5, DamageType.NECROSIS,
                List.of(new StatusApplication(StatusType.NECROSIS, 80)));
        EnemyAction boneStab = new EnemyAction("bone_stab_id",  "Bone Stab", 11, DamageType.PHYSICAL,
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
                        new WeightedAction(putridSpray, 3),
                        new WeightedAction(boneStab, 4)),
                List.of("The blade will probably sink in, while the flesh closes up.",
                        "It seems to move while avoiding the hot spots in the corridor.",
                        "It appears to be composed of - and to feed upon - rot"));
    }



}
