package it.unicam.cs.mpgc.rpg129795.Combat;

import it.unicam.cs.mpgc.rpg129795.Combat.Resolvers.AttackResolve;

public record CombatContext(Combatant source, Combatant target, AttackResolve attackResolve) {

    public CombatContext {
        if (source == null || target == null || attackResolve == null) {
            throw new IllegalArgumentException("Combat context cannot have null values");
        }
    }
}
