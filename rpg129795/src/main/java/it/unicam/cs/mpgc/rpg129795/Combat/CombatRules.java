package it.unicam.cs.mpgc.rpg129795.Combat;

public interface CombatRules {

    public boolean checkHit();

    public boolean checkBonusDodge();

    public boolean checkCritical();

    public boolean getAffinityMultiplier();

    public int applyDefense();

    public boolean resolveAttack();

    public int checkStatus();

}
