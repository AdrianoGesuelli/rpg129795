package it.unicam.cs.mpgc.rpg129795.Items.Relic.RelicList;

import it.unicam.cs.mpgc.rpg129795.Items.Relic.Relic;

public final class WatchersEye implements Relic {

    private static final int critBonus = 10;

    public String id() {
        return "watchers_eye_id";
    }

    public String name() {
        return "Watcher's Eye";
    }

    public String description() {
        return "A glass eye that never blinks.";
    }

    public String effect() {
        return "+10% critical chance. At the start of every fight, reveals what you would learn "
                + "by observing the enemy, without spending your turn.";
    }

    public int getCritBonus(int currentHp, int maxHp) {
        return critBonus;
    }

    public boolean revealsEnemies() {
        return true;
    }
}