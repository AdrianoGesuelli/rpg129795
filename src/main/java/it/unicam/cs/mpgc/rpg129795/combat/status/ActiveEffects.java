package it.unicam.cs.mpgc.rpg129795.combat.status;

import it.unicam.cs.mpgc.rpg129795.combat.Combatant;

import java.util.List;
import java.util.ArrayList;

public class ActiveEffects {

    private final List<StatusEffect> active = new ArrayList<>();

    //? aggiunge/riapplica lo status
    public void applyEffect(StatusType type) {
        for (int i = 0; i < active.size(); i++) {
            if (active.get(i).getStatusType() == type) {
                active.get(i).reapply();
                return;
            }
        }
        active.add(type.create());
    }

    public int getDamageTakenPercent() {
        int result = 100;
        for (int i = 0; i < active.size(); i++) {
            result = result * active.get(i).getDamageTakenPercent() / 100;
        }
        return result;
    }

    public int getDamageDealtPercent() {
        int result = 100;
        for (int i = 0; i < active.size(); i++) {
            result = result * active.get(i).getDamageDealtPercent() / 100;
        }
        return result;
    }

    //? inizio turno di chi ha uno status
    public void onTurnStart(Combatant statusOwner) {
        for (int i = 0; i < active.size(); i++) {
            active.get(i).onTurnStart(statusOwner);
        }
        active.removeIf(StatusEffect::hasEnded);
    }

    //? fine turno di chi ha uno status
    public void onTurnEnd(Combatant statusOwner) {
        for (int i = 0; i < active.size(); i++) {
            active.get(i).onTurnEnd(statusOwner);
        }
        active.removeIf(StatusEffect::hasEnded);
    }

    public boolean has(StatusType type) {
        for (int i = 0; i < active.size(); i++) {
            if (active.get(i).getStatusType() == type) {
                return true;
            }
        }
        return false;
    }

    public List<StatusEffect> getActive() {
        return List.copyOf(active);
    }

    //? rimuove tutti gli effetti di stato (fine combat)
    public void clear() {
        active.clear();
    }

}
