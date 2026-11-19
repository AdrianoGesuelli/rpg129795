package it.unicam.cs.mpgc.rpg129795.Saves;

import it.unicam.cs.mpgc.rpg129795.CastleMap.CastleEntrance;
import it.unicam.cs.mpgc.rpg129795.Character.PlayerCharacter;

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
