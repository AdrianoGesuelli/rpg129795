package it.unicam.cs.mpgc.rpg129795.Enemies;

import it.unicam.cs.mpgc.rpg129795.Enemies.EnemyList.EnemyBestiary;

import java.util.List;
import java.util.Random;

public final class RandomEnemySelector {

    private final Random random;

    public RandomEnemySelector(Random random) {
        if (random == null) {
            throw new IllegalArgumentException("Random cannot be null");
        }

        this.random = random;
    }

    public EnemyTemplate select(EnemyTier tier) {
        if (tier == EnemyTier.ENTRY) {
            throw new IllegalArgumentException("Entry guardians are not chosen at random");
        }

        List<EnemyTemplate> candidates = EnemyBestiary.ofTier(tier);

        if (candidates.isEmpty()) {
            throw new IllegalStateException("No enemies for Tier: " + tier);
        }

        return candidates.get(random.nextInt(candidates.size()));
    }

}
