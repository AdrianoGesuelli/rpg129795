package it.unicam.cs.mpgc.rpg129795.combat.resolvers;

public record AttackResult(HitResult result, int damage, int heal) {

    public static AttackResult missed(){
        return new AttackResult(HitResult.MISS,0,0);
    }

    public static AttackResult damaged(HitResult result, int damage){
        return new AttackResult(result, damage,0);
    }

    public static AttackResult absorbed(HitResult result, int heal){
        return new AttackResult(result, 0, heal);
    }

}
