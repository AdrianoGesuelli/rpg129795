package it.unicam.cs.mpgc.rpg129795.combat;

import it.unicam.cs.mpgc.rpg129795.combat.status.ActiveEffects;

public abstract class AbstractCombatant implements Combatant{

    private int currentHp;
    private Stance currentStance = Stance.NEUTRAL;
    private final ActiveEffects activeEffects = new ActiveEffects();

    public AbstractCombatant(int startingHp){
        if (startingHp <= 0){
            throw new IllegalArgumentException("Starting HP must be greater than 0");
        }
        this.currentHp = startingHp;
    }

    public int getCurrentHp() {
        return currentHp;
    }

    public boolean isAlive(){
        return currentHp > 0;
    }

    public void takeDamage(int amount){
        if (amount < 0){
            throw new IllegalArgumentException("Damage must be positive");
        }

        currentHp -= amount;

        if (currentHp <= 0){
            currentHp = 0;
        }
    }

    public void heal(int amount){
        if (amount < 0){
            throw new IllegalArgumentException("Heals must be positive");
        }

        currentHp += amount;
        int maxHp = getStats().maxHp();
        if (currentHp > maxHp){
            currentHp = maxHp;
        }
    }

    public Stance getStance(){
        return currentStance;
    }

    public void setStance(Stance newStance){
        if (newStance == null){
            throw new IllegalArgumentException("Current Stance cannot be null");
        }
        this.currentStance = newStance;
    }

    public ActiveEffects getActiveEffects() {
        return activeEffects;
    }



    //!----------------------------------------------------

    //? Usato solo per ripristinare eventuali salvataggi
    public void setCurrentHp(int hp) {
        if (hp <= 0 || hp > getStats().maxHp()) {
            throw new IllegalArgumentException("HP must be between 1 and " + getStats().maxHp());
        }
        currentHp = hp;
    }

}
