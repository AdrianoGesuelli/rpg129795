package it.unicam.cs.mpgc.rpg129795.Saves;

import it.unicam.cs.mpgc.rpg129795.CastleMap.CastleEntrance;
import it.unicam.cs.mpgc.rpg129795.Character.CharacterBuilder;
import it.unicam.cs.mpgc.rpg129795.Character.PlayerCharacter;
import it.unicam.cs.mpgc.rpg129795.Classes.CharacterClass;
import it.unicam.cs.mpgc.rpg129795.Classes.ClassCatalog;
import it.unicam.cs.mpgc.rpg129795.Classes.Subclass;

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

    public GameProgress loadProgress() {
        SaveData data = storage.load();

        CharacterClass characterClass = ClassCatalog.classById(data.classId());
        Subclass subclass = ClassCatalog.subclassById(characterClass, data.subclassId());

        PlayerCharacter player = new CharacterBuilder()
                .withName(data.playerName())
                .withClass(characterClass)
                .withSubclass(subclass)
                .build();
        player.setCurrentHp(data.currentHp());

        CastleEntrance entrance = CastleEntrance.byId(data.entranceId());
        return new GameProgress(player, entrance, data.floor());
    }

    public boolean hasSave() {
        return storage.exists();
    }

    public void deleteSave() {
        storage.delete();
    }
}
