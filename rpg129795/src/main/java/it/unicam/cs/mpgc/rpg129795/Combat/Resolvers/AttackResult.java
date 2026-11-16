package it.unicam.cs.mpgc.rpg129795.Combat.Resolvers;

public record AttackResult(HitResult result, int damage, int heal) {

    public static AttackResult miss(){
        return new AttackResult(HitResult.MISS,0,0);
    }

    public static AttackResult damage(HitResult result, int damage){
        return new AttackResult(result, damage,0);
    }

    public static AttackResult heal(HitResult result, int heal){
        return new AttackResult(result, 0, heal);
    }

}
