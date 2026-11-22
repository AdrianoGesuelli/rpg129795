package it.unicam.cs.mpgc.rpg129795.items.relics.relicList;

import it.unicam.cs.mpgc.rpg129795.items.relics.Relic;

public final class AshenRosary implements Relic {

    private static final int curseDamagePercent = 50;

    public String id() {
        return "ashen_rosary";
    }

    public String name() {
        return "Ashen Rosary";
    }

    public String description() {
        return "Prayer beads made from the ashes of saints.";
    }

    public String effect() {
        return "The Curse of the Demon Lord deals half damage, and none of its effects "
                + "(Necrosis, Vulnerable, Weakened) can touch you.";
    }

    public int getCurseDamagePercent() {
        return curseDamagePercent;
    }

    public boolean blocksCurseEffects() {
        return true;
    }
}