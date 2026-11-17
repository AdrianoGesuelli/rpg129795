package it.unicam.cs.mpgc.rpg129795.Combat;

import it.unicam.cs.mpgc.rpg129795.Character.Stats;
import it.unicam.cs.mpgc.rpg129795.Combat.Affinities.Affinity;
import it.unicam.cs.mpgc.rpg129795.Combat.Affinities.DamageType;

public interface Combatant {

    String getName();
    Stats getStats();
    int getCurrentHp();
    boolean isAlive();
    void takeDamage(int amount);
    void heal(int amount);
    Affinity getAffinity(DamageType type);
    Stance getStance();
    void setStance(Stance stance);

}
