package it.unicam.cs.mpgc.rpg129795.items.relics;

import it.unicam.cs.mpgc.rpg129795.items.relics.relicList.*;

import java.util.List;

public final class RelicCatalog {

    private static final List<Relic> RELICS = List.of(
            new PilgrimsPendant(),
            new GrimHourglass(),
            new WatchersEye(),
            new FeatherboneCharm(),
            new AshenRosary());

    private RelicCatalog() {}

    public static List<Relic> relics() {
        return RELICS;
    }

    public static Relic byId(String id) {
        for (int i = 0; i < RELICS.size(); i++) {
            if (RELICS.get(i).id().equals(id)) {
                return RELICS.get(i);
            }
        }
        throw new IllegalArgumentException("Unknown relic id: " + id);
    }
}