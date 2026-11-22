package it.unicam.cs.mpgc.rpg129795.enemies.behavior;

public final class CommonEnemyActions {

    public static final EnemyAction analyze = EnemyAction.analyze("analyze_id", "Analyze");
    public static final EnemyAction curse = EnemyAction.curse("curse_id", "Curse of the Demon Lord");

    public static final int analyzeWeight = 1;
    public static final int curseWeight = 1;

    private CommonEnemyActions() {}

}