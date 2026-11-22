package it.unicam.cs.mpgc.rpg129795.Combat.Resolvers;

public record AttackModifiers(int critBonus, int damagePercent) {

    private static final int analysisCritBonus = 50;
    private static final int analysisDamagePercent = 150;

    public static final AttackModifiers none = new AttackModifiers(0, 100);
    public static final AttackModifiers analyzed = new AttackModifiers(analysisCritBonus, analysisDamagePercent);
}
