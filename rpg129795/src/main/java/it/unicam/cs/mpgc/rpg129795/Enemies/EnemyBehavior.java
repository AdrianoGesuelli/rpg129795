package it.unicam.cs.mpgc.rpg129795.Enemies;

import java.util.List;

public interface EnemyBehavior {

    EnemyAction chooseAction(List<WeightedAction> pool);

}
