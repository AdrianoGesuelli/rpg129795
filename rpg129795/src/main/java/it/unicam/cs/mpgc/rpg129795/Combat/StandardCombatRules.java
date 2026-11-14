package it.unicam.cs.mpgc.rpg129795.Combat;

import java.util.Random;

public class StandardCombatRules //! implements CombatRules

    {

    private final Random random;

    public StandardCombatRules(Random random) {
        this.random = random;
    }

    public int roll(){
        return random.nextInt(100) + 1;   //? D100, da 1 a 100
    }

    /* !
    public boolean checkHit(){
        int attackRoll = roll();
        int damageValue = getDamageValue();
        if (checkCritical(attackRoll)) {
            int criticalDamage = damageValue * 2;
        }
        checkBaseDodge(attackRoll);

        resolveAttack();
    }
     */

    /* !
    public boolean checkBaseDodge(int attackRoll){
        if (attackRoll <= getBaseDodgeValue()){
            return true;
        }
        return false;
    }
     */

    public boolean checkCritical(int attackRoll){
        if (attackRoll >= 91) return true;
        return false;
    }

    public boolean getAffinityMultiplier(){
        //TODO
        return true;
    }

    /* !
    public int applyDefense(int damage){

        int finalDamage = damage - getBaseDefenceValue();
        return finalDamage;
    }
     */

    /* !
    public void resolveAttack(int finalDamage){
        int currentHP = getCurrentHP();
        int newHpValue = currentHP -  finalDamage;
        checkStatus(newHpValue);
        setHp(newHpValue);
    }
     */

    public boolean checkStatus(int HP){
        if (HP <= 0) return false;
        return true;
    }

}
