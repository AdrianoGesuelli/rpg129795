package it.unicam.cs.mpgc.rpg129795.items.relics.relicList;

import it.unicam.cs.mpgc.rpg129795.combat.Stance;
import it.unicam.cs.mpgc.rpg129795.items.relics.Relic;

public final class FeatherboneCharm implements Relic {

    private static final int evasionBonus = 10;
    private static final int normalDamage = 100;

    public String id() {
        return "featherbone_charm";
    }

    public String name() {
        return "Featherbone Charm";
    }

    public String description() {
        return "Hollow bones bound with black feathers.";
    }

    public String effect() {
        return "+10% evasion. While dodging, hits that land deal normal damage (100%) instead of 150%.";
    }

    public int getEvasionBonus(int currentHp, int maxHp) {
        return evasionBonus;
    }

    public int adjustStanceDamage(Stance stance, int damageMultiplier) {
        if (stance == Stance.DODGE) {
            return normalDamage;
        }
        return damageMultiplier;
    }
}