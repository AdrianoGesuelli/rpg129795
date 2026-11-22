package it.unicam.cs.mpgc.rpg129795.enemies.behavior;

import java.util.List;
import java.util.Random;

public class WeightedRandomBehavior implements EnemyBehavior {

    private final Random random;

    public WeightedRandomBehavior(Random random) {
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

        if (totalWeight == 0) {
            throw new IllegalArgumentException("The action pool is empty");
        }

        int roll = random.nextInt(totalWeight); //* da 0 a totalWeight - 1

        for (int i = 0; i < pool.size(); i++) {
            roll -= pool.get(i).actionWeight();
            if (roll < 0) {
                return pool.get(i).action();
            }
        }
        throw new IllegalStateException("No action was chosen");
    }
}
