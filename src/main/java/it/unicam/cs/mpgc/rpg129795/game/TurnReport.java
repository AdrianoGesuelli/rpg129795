package it.unicam.cs.mpgc.rpg129795.game;

import it.unicam.cs.mpgc.rpg129795.combat.CombatOutcome;
import it.unicam.cs.mpgc.rpg129795.combat.EnemyTurnResult;
import it.unicam.cs.mpgc.rpg129795.combat.resolvers.ActionResult;

public record TurnReport(ActionResult playerResult, EnemyTurnResult enemyResult, CombatOutcome outcome) { }