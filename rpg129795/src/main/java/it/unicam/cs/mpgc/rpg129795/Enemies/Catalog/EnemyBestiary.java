package it.unicam.cs.mpgc.rpg129795.Enemies.Catalog;

import it.unicam.cs.mpgc.rpg129795.Enemies.EnemyTemplate;
import it.unicam.cs.mpgc.rpg129795.Enemies.EnemyTier;

import java.util.ArrayList;
import java.util.List;

public class EnemyBestiary {

    private EnemyBestiary() {}

    //? raggruppo tutti i nemici di ogni tier
    public static List<EnemyTemplate> all() {
        List<EnemyTemplate> allEnemies = new ArrayList<>();
        allEnemies.addAll(EntryEnemies.all());
        allEnemies.addAll(Floor1Enemies.all());
        return List.copyOf(allEnemies);
    }

    //? filtro per nemici di uno specifico tier
    public static List<EnemyTemplate> ofTier(EnemyTier  tier) {
        List<EnemyTemplate> filtredEnemies = new ArrayList<>();
        List<EnemyTemplate> allEnemies = all();

        for (int i = 0; i < allEnemies.size(); i++) {
            if (allEnemies.get(i).tier() == tier) {
                filtredEnemies.add(allEnemies.get(i));
            }
        }
        return List.copyOf(filtredEnemies);
    }
}
