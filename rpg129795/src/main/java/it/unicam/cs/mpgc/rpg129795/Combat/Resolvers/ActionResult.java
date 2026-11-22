package it.unicam.cs.mpgc.rpg129795.Combat.Resolvers;

public record ActionResult (String actionName, AttackResult attackResult, String insight){

    public ActionResult(String actionName, AttackResult attackResult) {
        this(actionName, attackResult, null);
    }

    public static ActionResult withoutAttack(String actionName) {
        return new ActionResult(actionName, null);
    }

    public boolean isAttack() {
        return attackResult != null;
    }

    //? Un'azione che non attacca ma rivela qualcosa (Observe)
    public static ActionResult insight(String actionName, String insight) {
        return new ActionResult(actionName, null, insight);
    }

    public boolean hasInsight() {
        return insight != null;
    }

}
