package it.unicam.cs.mpgc.rpg129795.Items.Relic.RelicList;

import it.unicam.cs.mpgc.rpg129795.Items.Relic.Relic;

public final class PilgrimsPendant implements Relic {

    private static final int maxHpPercent = 115;

    public String id() {
        return "pilgrims_pendant_id";
    }

    public String name() {
        return "Pilgrim's Pendant";
    }

    public String description() {
        return "A worn pendant that once guided pilgrims through the dark.";
    }

    public String effect() {
        return "+15% maximum HP.";
    }

    public int getMaxHpPercent() {
        return maxHpPercent;
    }
}