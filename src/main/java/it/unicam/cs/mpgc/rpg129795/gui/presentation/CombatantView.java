package it.unicam.cs.mpgc.rpg129795.gui.presentation;

import it.unicam.cs.mpgc.rpg129795.combat.Combatant;
import it.unicam.cs.mpgc.rpg129795.combat.status.StatusEffect;

import java.util.List;

public record CombatantView(String name, int currentHp, int maxHp, String statuses) {

    public static CombatantView of(Combatant combatant) {
        return new CombatantView(combatant.getName(), combatant.getCurrentHp(), combatant.getStats().maxHp(), describeStatuses(combatant));
    }

    //? Da 0.0 (morto) a 1.0 (vita piena), per la barra degli HP
    public double hpRatio() {
        return (double) currentHp / maxHp;
    }

    private static String describeStatuses(Combatant combatant) {
        List<StatusEffect> effects = combatant.getActiveEffects().getActive();
        String text = "";
        for (int i = 0; i < effects.size(); i++) {
            StatusEffect effect = effects.get(i);
            if (i > 0) {
                text += ", ";
            }
            text += effect.getStatusType() + " " + effect.remainingTurns() + " turns";
            if (effect.getStacks() > 1) {
                text += " x" + effect.getStacks();
            }
        }
        return text;
    }
}
