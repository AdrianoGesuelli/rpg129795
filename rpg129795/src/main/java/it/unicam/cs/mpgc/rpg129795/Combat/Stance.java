package it.unicam.cs.mpgc.rpg129795.Combat;

public enum Stance {

    //*     | evasion bonus | remove evasion | critical mult. | hit mult.
    NEUTRAL (0, false, 200, 100),
    DEFENSE (0, true, 100, 70),
    DODGE   (40, false, 200, 150);

    private final int evasionBonus;
    private final boolean removeEvasion;
    private final int criticalMultiplier;
    private final int damageMultiplier;

    Stance (int evasionBonus, boolean removeEvasion, int criticalMultiplier, int damageMultiplier) {
        this.evasionBonus = evasionBonus;
        this.removeEvasion = removeEvasion;
        this.criticalMultiplier = criticalMultiplier;
        this.damageMultiplier = damageMultiplier;
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

}
