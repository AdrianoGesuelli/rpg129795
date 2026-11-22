package it.unicam.cs.mpgc.rpg129795.Items.Relic.RelicList;

import it.unicam.cs.mpgc.rpg129795.Items.Relic.Relic;

public final class GrimHourglass implements Relic {

    private static final int thresholdPercent = 30;
    private static final int desperateBonus = 25;

    public String id() {
        return "grim_hourglass_id";
    }

    public String name() {
        return "Grim Hourglass";
    }

    public String description() {
        return "Its sand runs faster when death is near.";
    }

    public String effect() {
        return "Below 30% HP: +25% critical chance and +25% evasion.";
    }

    public int getCritBonus(int currentHp, int maxHp) {
        if (isDesperate(currentHp, maxHp)) {
            return desperateBonus;
        }
        return 0;
    }

    public int getEvasionBonus(int currentHp, int maxHp) {
        if (isDesperate(currentHp, maxHp)) {
            return desperateBonus;
        }
        return 0;
    }

    //? Sotto il 30% degli HP massimi
    private boolean isDesperate(int currentHp, int maxHp) {
        return currentHp * 100 < maxHp * thresholdPercent;
    }
}