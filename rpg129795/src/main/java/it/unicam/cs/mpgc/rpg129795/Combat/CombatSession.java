package it.unicam.cs.mpgc.rpg129795.Combat;

import it.unicam.cs.mpgc.rpg129795.Character.PlayerCharacter;
import it.unicam.cs.mpgc.rpg129795.Combat.Resolvers.ActionResult;
import it.unicam.cs.mpgc.rpg129795.Combat.Resolvers.AttackResolve;
import it.unicam.cs.mpgc.rpg129795.Combat.Resolvers.AttackResult;
import it.unicam.cs.mpgc.rpg129795.Enemies.ActionsAndBehavior.EnemyAction;
import it.unicam.cs.mpgc.rpg129795.Enemies.Enemy;

public final class CombatSession {

    private final PlayerCharacter player;
    private final Enemy enemy;
    private final AttackResolve attackResolve;

    public CombatSession(PlayerCharacter player, Enemy enemy, AttackResolve attackResolve) {
        if (player == null || enemy == null || attackResolve == null) {
            throw new IllegalArgumentException("Combat Session cannot have null values");
        }

        this.player = player;
        this.enemy = enemy;
        this.attackResolve = attackResolve;
    }

    //? Turno del giocatore:
    public ActionResult playerTurn(CombatActions action) {
        startTurn(player);

        if (!player.isAlive()) {
            return null;
        }

        ActionResult result = action.execute(new CombatContext(player, enemy, attackResolve));
        endTurn(player);
        return result;
    }

    //? Turno del nemico:
    public EnemyTurnResult enemyTurn() {
        startTurn(enemy);

        if (!enemy.isAlive()) {
            return null;
        }

        EnemyAction action = enemy.chooseAction();
        AttackResult result = attackResolve.resolve(enemy, player, action.damage(), action.damageType(), action.statuses());
        endTurn(enemy);
        return new EnemyTurnResult(action, result);
    }

    //? Inizo turno: scade la Stance, poi si attivano gli status di inizio turno
    private void startTurn(Combatant combatant) {
        combatant.setStance(Stance.NEUTRAL);
        combatant.getActiveEffects().onTurnStart(combatant);
    }

    //? Fine turno: si attivano gli status di fine turno
    private void endTurn(Combatant combatant) {
        combatant.getActiveEffects().onTurnEnd(combatant);
    }

    public CombatOutcome getOutcome() {
        if (!player.isAlive()) {
            return CombatOutcome.DEFEAT;
        }
        if (!enemy.isAlive()) {
            return CombatOutcome.VICTORY;
        }
        return CombatOutcome.ONGOING;
    }

    public PlayerCharacter getPlayer() {
        return player;
    }

    public Enemy getEnemy() {
        return enemy;
    }

}
