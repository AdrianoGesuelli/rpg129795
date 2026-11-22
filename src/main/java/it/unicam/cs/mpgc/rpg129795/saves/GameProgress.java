package it.unicam.cs.mpgc.rpg129795.saves;

import it.unicam.cs.mpgc.rpg129795.game.CastleEntrance;
import it.unicam.cs.mpgc.rpg129795.character.PlayerCharacter;

public record GameProgress(PlayerCharacter player, CastleEntrance entrance, int floor) {

    public GameProgress {
        if (player == null || entrance == null) {
            throw new IllegalArgumentException("Player and entrance are required");
        }

        if (floor < 0) {
            throw new IllegalArgumentException("Floor cannot be negative");
        }
    }
}
