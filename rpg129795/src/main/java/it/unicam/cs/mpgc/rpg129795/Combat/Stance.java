package it.unicam.cs.mpgc.rpg129795.Combat;

public enum Stance {

    //*     | evasion bonus | remove evasion | critical mult. | hit mult. | status block
    NEUTRAL (0, false, 200, 100, 0),
    DEFENSE (0, true, 100, 70, 50),
    DODGE   (40, false, 200, 150, 0);

    private final int evasionBonus;
    private final boolean removeEvasion;
    private final int criticalMultiplier;
    private final int damageMultiplier;
    private final int statusBlockChance;

    Stance (int evasionBonus, boolean removeEvasion, int criticalMultiplier, int damageMultiplier, int statusBlockChance) {
        this.evasionBonus = evasionBonus;
        this.removeEvasion = removeEvasion;
        this.criticalMultiplier = criticalMultiplier;
        this.damageMultiplier = damageMultiplier;
        this.statusBlockChance = statusBlockChance;
    }

    public int getEvasionBonus(int evasion) {
        if (removeEvasion) {
            return 0;
        }
        return evasion + evasionBonus;
    }

    public int getCriticalMultiplier() {
        return criticalMultiplier;
    }

    public int getDamageMultiplier() {
        return damageMultiplier;
    }

    public int getStatusBlockChance() {
        return statusBlockChance;
    }

}
