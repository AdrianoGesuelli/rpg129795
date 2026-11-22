package it.unicam.cs.mpgc.rpg129795.enemies;

import it.unicam.cs.mpgc.rpg129795.combat.Combatant;
import it.unicam.cs.mpgc.rpg129795.enemies.behavior.EnemyAction;

public interface Enemy extends Combatant {

    String getId();
    EnemyTier getTier();
    EnemyAction chooseAction();
    EnemyAction chooseAttack();

}
