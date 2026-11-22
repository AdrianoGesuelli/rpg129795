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

public class EntryEnemies {

    private EntryEnemies() {}

    public static List<EnemyTemplate> all() {
        return List.of(gargoyle(), giantSkeleton(), siren());
    }

    public static EnemyTemplate gargoyle() {
        EnemyAction stoneClaw = new EnemyAction("stone_claw_id", "Stone Claw", new DamageRange(6, 11), DamageType.PHYSICAL);
        EnemyAction divingStrike = new EnemyAction("diving_strike_id", "Diving Strike", new DamageRange(9, 16), DamageType.PHYSICAL);
        EnemyAction bodyStomp = new EnemyAction("body_stomp_id",  "Body Stomp", new DamageRange(8, 13), DamageType.PHYSICAL);

        return new EnemyTemplate("gargoyle_id", "Gargoyle", EnemyTier.ENTRY,
                new Stats(36, 10, 5, 5),
                new AffinityTable(Map.of(
                        DamageType.EARTH, Affinity.WEAK,
                        DamageType.MAGIC,  Affinity.WEAK,
                        DamageType.PHYSICAL, Affinity.STRONG,
                        DamageType.WIND,  Affinity.STRONG,
                        DamageType.FIRE,  Affinity.IMMUNE,
                        DamageType.NECROSIS, Affinity.IMMUNE,
                        DamageType.POISON, Affinity.IMMUNE)),
                List.of(new WeightedAction(stoneClaw, 3),
                        new WeightedAction(divingStrike, 3),
                        new WeightedAction(bodyStomp, 2)),
                ("A hulking statue of cracked stone. A crushing blow of earth could split it apart, " +
                "yet a mere weapon swing only scrapes its surface. There is no flesh nor bone within it, " +
                "and it is so heavy that not even a storm could budge it."));
    }

    public static EnemyTemplate giantSkeleton() {
        EnemyAction boneClub = new EnemyAction("bone_club_id", "Bone Club", new DamageRange(11, 18), DamageType.PHYSICAL);
        EnemyAction crushingStomp = new EnemyAction("crushing_stomp_id", "Crushing Stomp", new DamageRange(9, 15), DamageType.PHYSICAL);
        EnemyAction coldBreath = new EnemyAction("cold_breath_id",  "Cold Breath", new DamageRange(6, 10), DamageType.WIND,
                List.of(new StatusApplication(StatusType.NECROSIS, 30)));

        return new EnemyTemplate("giant_skeleton_id", "Giant Skeleton", EnemyTier.ENTRY,
                new Stats(70, 20, 10, 10),
                new AffinityTable(Map.of(
                        DamageType.HOLY, Affinity.WEAK,
                        DamageType.EARTH,  Affinity.WEAK,
                        DamageType.PHYSICAL, Affinity.WEAK,
                        DamageType.WIND,  Affinity.WEAK,
                        DamageType.DARK,  Affinity.STRONG,
                        DamageType.POISON, Affinity.IMMUNE,
                        DamageType.NECROSIS, Affinity.ABSORBS)),
                List.of(new WeightedAction(boneClub, 3),
                        new WeightedAction(crushingStomp, 3),
                        new WeightedAction(coldBreath, 2)),
                ("A towering heap of ancient bones. There is nothing left to rot and nothing left to poison: " +
                "such things only seem to feed whatever moves it. The force that binds it fears the light of the old gods, " +
                "and its brittle frame looks ready to shatter under a good push."));
    }

    public static EnemyTemplate siren() {
        EnemyAction tentacleSwing = new EnemyAction("tentacle_swing_id", "Tentacle Swing", new DamageRange(7, 12), DamageType.PHYSICAL);
        EnemyAction sirenSong = new EnemyAction("siren_song_id", "Siren Song", new DamageRange(9, 15), DamageType.MAGIC);
        EnemyAction bigWave = new EnemyAction("big_wave_id",  "Big Wave", new DamageRange(10, 17), DamageType.WATER);

        return new EnemyTemplate("siren_id", "Siren", EnemyTier.ENTRY,
                new Stats(40, 0, 25, 5),
                new AffinityTable(Map.of(
                        DamageType.EARTH, Affinity.WEAK,
                        DamageType.WATER, Affinity.ABSORBS,
                        DamageType.FIRE,  Affinity.STRONG,
                        DamageType.POISON, Affinity.WEAK,
                        DamageType.MAGIC,  Affinity.STRONG)),
                List.of(new WeightedAction(tentacleSwing, 2),
                        new WeightedAction(sirenSong, 4),
                        new WeightedAction(bigWave, 2)),
                ("A pale shape coiled in the black water, singing. " +
                "The water closes its wounds, so give it no more. " +
                "Its skin glistens with a fireproof slime, and it clearly despises being on dry land."));
    }

}
