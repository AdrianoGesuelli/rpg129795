package it.unicam.cs.mpgc.rpg129795.Combat.Status;

public record StatusApplication(StatusType type, int chance) {

    public StatusApplication {
        if (type == null || chance <= 0 || chance > 100) {
            throw new IllegalArgumentException("Invalid status");
        }
    }
}
