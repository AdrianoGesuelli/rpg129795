package it.unicam.cs.mpgc.rpg129795.combat;

import it.unicam.cs.mpgc.rpg129795.character.Stats;
import it.unicam.cs.mpgc.rpg129795.combat.affinities.Affinity;
import it.unicam.cs.mpgc.rpg129795.combat.affinities.DamageType;
import it.unicam.cs.mpgc.rpg129795.combat.status.ActiveEffects;

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
    ActiveEffects getActiveEffects();

    //? Moltiplicatore del danno subito nella postura attuale (le reliquie del giocatore possono cambiarlo)
    default int getStanceDamageMultiplier() {
        return getStance().getDamageMultiplier();
    }

    default int getCurseDamagePercent() {
        return 100;
    }

    default boolean blocksCurseEffects() {
        return false;
    }

}
