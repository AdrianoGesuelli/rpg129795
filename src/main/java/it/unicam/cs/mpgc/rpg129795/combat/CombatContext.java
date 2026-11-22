package it.unicam.cs.mpgc.rpg129795.combat;

import it.unicam.cs.mpgc.rpg129795.combat.resolvers.AttackResolver;

public record CombatContext(Combatant source, Combatant target, AttackResolver attackResolver) {

    public CombatContext {
        if (source == null || target == null || attackResolver == null) {
            throw new IllegalArgumentException("Combat context cannot have null values");
        }
    }
}
