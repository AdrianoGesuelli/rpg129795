package it.unicam.cs.mpgc.rpg129795.Game;

import it.unicam.cs.mpgc.rpg129795.Combat.CombatOutcome;
import it.unicam.cs.mpgc.rpg129795.Combat.EnemyTurnResult;
import it.unicam.cs.mpgc.rpg129795.Combat.Resolvers.ActionResult;

public record TurnReport(ActionResult playerResult, EnemyTurnResult enemyResult, CombatOutcome outcome) { }