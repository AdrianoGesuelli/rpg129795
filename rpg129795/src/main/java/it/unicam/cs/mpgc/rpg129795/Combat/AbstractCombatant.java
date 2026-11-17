package it.unicam.cs.mpgc.rpg129795.Combat;

public abstract class AbstractCombatant implements Combatant{

    private int currentHp;
    private Stance currentStance = Stance.NEUTRAL;

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



    //!----------------------------------------------------

    //? Usato solo per ripristinare eventuali salvataggi
    public void restoreHp(int hp){
        if (hp <= 0 || hp > getStats().maxHp()){
            throw new IllegalArgumentException("HP value error");
        }
        currentHp = hp;
    }

}
