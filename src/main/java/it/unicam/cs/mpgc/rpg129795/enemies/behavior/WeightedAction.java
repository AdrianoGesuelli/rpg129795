package it.unicam.cs.mpgc.rpg129795.enemies.behavior;

public record WeightedAction(EnemyAction action, int actionWeight) {

    public WeightedAction {
        if (action == null || actionWeight <= 0) {
            throw new IllegalArgumentException("Values not valid");
        }
    }
}
