package it.unicam.cs.mpgc.rpg129795.game;

import it.unicam.cs.mpgc.rpg129795.enemies.enemyList.EntryEnemies;
import it.unicam.cs.mpgc.rpg129795.enemies.EnemyTemplate;
import it.unicam.cs.mpgc.rpg129795.enemies.EnemyTier;

import java.util.List;

public record CastleEntrance(String id, String name, String description, EnemyTemplate guardian){

    public CastleEntrance {
        if (id == null || name == null || description == null || guardian == null) {
            throw new IllegalArgumentException("Invalid castle entrance");
        }
        if (guardian.tier() != EnemyTier.ENTRY) {
            throw new IllegalArgumentException("The guardian of an entrance must be an ENTRY Tier enemy");
        }
    }

    public static List<CastleEntrance> all() {
        return List.of(
                new CastleEntrance("rooftop_access", "Rooftop Access",
                        "A climb up the crumbling walls, past the bell tower. Something perches up there, unmoving.",
                        EntryEnemies.gargoyle()),
                new CastleEntrance("castle_gates", "Castle Gates",
                        "The great gates stand wide open. Far too inviting.",
                        EntryEnemies.giantSkeleton()),
                new CastleEntrance("sewer_canal", "Sewer Canal",
                        "Black water flows out from under the castle. Someone is singing down there.",
                        EntryEnemies.siren()));
    }

    public static CastleEntrance byId(String id) {
        List<CastleEntrance> entrances = all();
        for (int i = 0; i < entrances.size(); i++) {
            if (entrances.get(i).id().equals(id)) {
                return entrances.get(i);
            }
        }
        throw new IllegalArgumentException("Unknown entrance id: " + id);
    }
}
