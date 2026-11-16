package it.unicam.cs.mpgc.rpg129795.Enemies;

import it.unicam.cs.mpgc.rpg129795.Combat.Combatant;

import java.util.List;

public interface Enemy extends Combatant {

    String getId();
    EnemyTier getTier();
    EnemyAction chooseAction();
    List<String> getObservationHints();

}
