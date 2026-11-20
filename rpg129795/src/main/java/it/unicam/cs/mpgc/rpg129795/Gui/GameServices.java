package it.unicam.cs.mpgc.rpg129795.Gui;

import it.unicam.cs.mpgc.rpg129795.Combat.Resolvers.AttackResolve;
import it.unicam.cs.mpgc.rpg129795.Combat.Resolvers.HitResolve;
import it.unicam.cs.mpgc.rpg129795.Enemies.EnemiesFactory;
import it.unicam.cs.mpgc.rpg129795.Enemies.RandomEnemySelector;
import it.unicam.cs.mpgc.rpg129795.Saves.JsonSaveStorage;
import it.unicam.cs.mpgc.rpg129795.Saves.SaveManager;

import java.util.Random;

public final record GameServices(SaveManager saveManager, EnemiesFactory enemiesFactory,
                           RandomEnemySelector enemySelector, AttackResolve attackResolve) {

    public GameServices {
        if (saveManager == null || enemiesFactory == null || enemySelector == null || attackResolve == null) {
            throw new IllegalArgumentException("Game services cannot be null");
        }
    }

    //? I servizi usati dal gioco vero e proprio
    public static GameServices createDefault() {
        Random random = new Random();
        return new GameServices(
                new SaveManager(JsonSaveStorage.inUserHome()),
                new EnemiesFactory(random),
                new RandomEnemySelector(random),
                new AttackResolve(new HitResolve(random), random));
    }
}