package it.unicam.cs.mpgc.rpg129795.enemies.behavior;

import java.util.List;

public interface EnemyBehavior {

    EnemyAction chooseAction(List<WeightedAction> pool);

}
