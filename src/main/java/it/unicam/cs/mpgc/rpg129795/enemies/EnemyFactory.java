package it.unicam.cs.mpgc.rpg129795.enemies;

import it.unicam.cs.mpgc.rpg129795.enemies.behavior.WeightedRandomBehavior;

import java.util.Random;

public class EnemyFactory {

    private final Random random;

    public EnemyFactory(Random random) {
        if (random == null) {
            throw new IllegalArgumentException("random's value be null");
        }

        this.random = random;
    }

    public Enemy create (EnemyTemplate template) {
        return new StandardEnemy(new WeightedRandomBehavior(random), template);
    }

}
