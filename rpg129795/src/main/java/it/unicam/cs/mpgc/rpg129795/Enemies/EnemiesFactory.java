package it.unicam.cs.mpgc.rpg129795.Enemies;

import it.unicam.cs.mpgc.rpg129795.Enemies.ActionsAndBehavior.WeightedActionsBehaviorRandomized;

import java.util.Random;

public class EnemiesFactory {

    private final Random random;

    public EnemiesFactory(Random random) {
        if (random == null) {
            throw new IllegalArgumentException("random's value be null");
        }

        this.random = random;
    }

    public Enemy create (EnemyTemplate template) {
        return new StandardEnemy(new WeightedActionsBehaviorRandomized(random), template);
    }

}
