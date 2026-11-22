package it.unicam.cs.mpgc.rpg129795.items.relics;

import it.unicam.cs.mpgc.rpg129795.combat.Stance;
import it.unicam.cs.mpgc.rpg129795.items.Item;

public interface Relic extends Item {

    //? Cosa fa la reliquia in gioco (description() resta il testo d'atmosfera)
    String effect();

    //? Percentuale degli HP massimi (100 = invariati)
    default int getMaxHpPercent() {
        return 100;
    }

    default int getCritBonus(int currentHp, int maxHp) {
        return 0;
    }

    default int getEvasionBonus(int currentHp, int maxHp) {
        return 0;
    }

    //? Permette di cambiare il danno subito in una certa postura
    default int adjustStanceDamage(Stance stance, int damageMultiplier) {
        return damageMultiplier;
    }

    //? Percentuale del danno della maledizione (100 = pieno)
    default int getCurseDamagePercent() {
        return 100;
    }

    default boolean blocksCurseEffects() {
        return false;
    }

    //? Se true, a inizio combattimento il nemico viene rivelato
    default boolean revealsEnemies() {
        return false;
    }
}
