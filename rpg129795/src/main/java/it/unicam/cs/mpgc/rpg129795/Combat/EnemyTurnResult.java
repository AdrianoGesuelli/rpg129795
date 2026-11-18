package it.unicam.cs.mpgc.rpg129795.Combat;

import it.unicam.cs.mpgc.rpg129795.Combat.Resolvers.AttackResult;
import it.unicam.cs.mpgc.rpg129795.Enemies.ActionsAndBehavior.EnemyAction;

public record EnemyTurnResult(EnemyAction action, AttackResult result) {

}