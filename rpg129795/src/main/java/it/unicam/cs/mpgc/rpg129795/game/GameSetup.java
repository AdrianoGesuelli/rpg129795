package it.unicam.cs.mpgc.rpg129795.game;

import it.unicam.cs.mpgc.rpg129795.combat.resolvers.AttackResolver;
import it.unicam.cs.mpgc.rpg129795.combat.resolvers.HitResolver;
import it.unicam.cs.mpgc.rpg129795.enemies.EnemyFactory;
import it.unicam.cs.mpgc.rpg129795.enemies.RandomEnemySelector;
import it.unicam.cs.mpgc.rpg129795.saves.JsonSaveStorage;
import it.unicam.cs.mpgc.rpg129795.saves.SaveManager;

import java.util.Random;

public final class GameSetup {

    private GameSetup() {}

    public static GameFlow createGameFlow() {
        Random random = new Random();
        return new GameFlow(
                new SaveManager(JsonSaveStorage.inUserHome()),
                new EnemyFactory(random),
                new RandomEnemySelector(random),
                new AttackResolver(new HitResolver(random), random));
    }
}
