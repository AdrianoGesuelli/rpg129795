package it.unicam.cs.mpgc.rpg129795.Combat.Resolvers;

public record ActionResult (String actionName, AttackResult attackResult){

    public static ActionResult withoutAttack(String actionName) {
        return new ActionResult(actionName, null);
    }

    public boolean isAttack() {
        return attackResult != null;
    }

}
