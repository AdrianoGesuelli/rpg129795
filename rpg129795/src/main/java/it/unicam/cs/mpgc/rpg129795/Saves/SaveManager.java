package it.unicam.cs.mpgc.rpg129795.Saves;

import it.unicam.cs.mpgc.rpg129795.CastleMap.CastleEntrance;
import it.unicam.cs.mpgc.rpg129795.Character.PlayerCharacter;

public final class SaveManager {

    private final SaveStorage storage;

    public SaveManager(SaveStorage storage) {
        if (storage == null) {
            throw new IllegalArgumentException("Save storage cannot be null");
        }

        this.storage = storage;
    }

    public void saveProgress(PlayerCharacter player, CastleEntrance entrance, int nextFloor) {
        storage.save(new SaveData(player.getName(), player.getCharacterClass().id(), player.getSubclass().id(),
                player.getCurrentHp(), entrance.id(), nextFloor));
    }

    public boolean hasSave() {
        return storage.exists();
    }
}
