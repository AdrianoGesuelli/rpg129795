package it.unicam.cs.mpgc.rpg129795.combat;

import it.unicam.cs.mpgc.rpg129795.character.PlayerCharacter;
import it.unicam.cs.mpgc.rpg129795.combat.resolvers.ActionResult;
import it.unicam.cs.mpgc.rpg129795.combat.resolvers.AttackModifiers;
import it.unicam.cs.mpgc.rpg129795.combat.resolvers.AttackResolver;
import it.unicam.cs.mpgc.rpg129795.combat.resolvers.AttackResult;
import it.unicam.cs.mpgc.rpg129795.enemies.behavior.CommonEnemyActions;
import it.unicam.cs.mpgc.rpg129795.enemies.behavior.EnemyAction;
import it.unicam.cs.mpgc.rpg129795.enemies.behavior.EnemyActionType;
import it.unicam.cs.mpgc.rpg129795.enemies.Enemy;

public final class CombatSession {

    private final PlayerCharacter player;
    private final Enemy enemy;
    private final AttackResolver attackResolver;
    private boolean cursePending;
    private boolean analyzed;

    public CombatSession(PlayerCharacter player, Enemy enemy, AttackResolver attackResolver) {
        if (player == null || enemy == null || attackResolver == null) {
            throw new IllegalArgumentException("Combat Session cannot have null values");
        }

        this.player = player;
        this.enemy = enemy;
        this.attackResolver = attackResolver;
    }

    //? Turno del giocatore:
    public ActionResult playerTurn(CombatAction action) {
        if (!action.isReady()) {
            throw new IllegalArgumentException(action.getName() + " is still recharging");
        }

        startTurn(player);
        player.tickCooldowns();

        if (!player.isAlive()) {
            return null;
        }

        ActionResult result = action.execute(new CombatContext(player, enemy, attackResolver));
        endTurn(player);
        return result;
    }

    //? Turno del nemico:
    public EnemyTurnResult enemyTurn() {
        startTurn(enemy);
        if (!enemy.isAlive()) {
            return null;
        }
        EnemyTurnResult result = actEnemy();
        endTurn(enemy);
        return result;
    }

    //? Prima si completa ciò che era stato preparato, poi si sceglie una nuova mossa
    private EnemyTurnResult actEnemy() {
        if (cursePending) {
            cursePending = false;
            return new EnemyTurnResult(CommonEnemyActions.curse, attackResolver.resolveCurse(player));
        }
        if (analyzed) {
            analyzed = false;
            return enemyAttack(enemy.chooseAttack(), AttackModifiers.analyzed);
        }

        EnemyAction action = enemy.chooseAction();
        if (action.type() == EnemyActionType.CURSE) {
            cursePending = true;
            EnemyTurnResult attack = enemyAttack(enemy.chooseAttack(), AttackModifiers.none);
            return new EnemyTurnResult(attack.action(), attack.result(), true);
        }
        if (action.type() == EnemyActionType.ANALYZE) {
            analyzed = true;
            return EnemyTurnResult.preparing(action);
        }
        return enemyAttack(action, AttackModifiers.none);
    }

    private EnemyTurnResult enemyAttack(EnemyAction action, AttackModifiers modifiers) {
        AttackResult result = attackResolver.resolve(enemy, player, action.damage(),
                action.damageType(), action.statuses(), modifiers);
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
