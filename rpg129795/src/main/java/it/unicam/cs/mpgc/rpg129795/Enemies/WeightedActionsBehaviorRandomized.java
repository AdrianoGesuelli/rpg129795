package it.unicam.cs.mpgc.rpg129795.Enemies;

import java.util.List;
import java.util.Random;

public class WeightedActionsBehaviorRandomized implements EnemyBehavior{

    private final Random random;

    public WeightedActionsBehaviorRandomized(Random random) {
        if (random == null) {
            throw new NullPointerException("random");
        }
        this.random = random;
    }

    public EnemyAction chooseAction(List<WeightedAction> pool) {
        int totalWeight = 0;

        for (int i = 0; i < pool.size(); i++) {
            totalWeight += pool.get(i).actionWeight();
        }

        int roll = random.nextInt(1, totalWeight);

        for (int i = 0; i < pool.size(); i++) {
            roll -= pool.get(i).actionWeight();
            if (roll < 0) {
                return pool.get(i).action();
            }
        }

        throw new IllegalArgumentException("The action pool is empty");
    }
}
