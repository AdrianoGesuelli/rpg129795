package it.unicam.cs.mpgc.rpg129795.Gui;

import it.unicam.cs.mpgc.rpg129795.Combat.Resolvers.AttackResolve;
import it.unicam.cs.mpgc.rpg129795.Combat.Resolvers.HitResolve;
import it.unicam.cs.mpgc.rpg129795.Enemies.EnemiesFactory;
import it.unicam.cs.mpgc.rpg129795.Enemies.RandomEnemySelector;
import it.unicam.cs.mpgc.rpg129795.Game.GameFlow;
import it.unicam.cs.mpgc.rpg129795.Saves.JsonSaveStorage;
import it.unicam.cs.mpgc.rpg129795.Saves.SaveManager;

import java.util.Random;

public record GameServices(GameFlow gameFlow) {

    public GameServices {
        if (gameFlow == null) {
            throw new IllegalArgumentException("Game flow cannot be null");
        }
    }

    public static GameServices createDefault() {
        Random random = new Random();
        GameFlow gameFlow = new GameFlow(
                new SaveManager(JsonSaveStorage.inUserHome()),
                new EnemiesFactory(random),
                new RandomEnemySelector(random),
                new AttackResolve(new HitResolve(random), random));
        return new GameServices(gameFlow);
    }
}