package it.unicam.cs.mpgc.rpg129795.saves;

import it.unicam.cs.mpgc.rpg129795.game.CastleEntrance;
import it.unicam.cs.mpgc.rpg129795.character.CharacterBuilder;
import it.unicam.cs.mpgc.rpg129795.character.PlayerCharacter;
import it.unicam.cs.mpgc.rpg129795.character.classes.CharacterClass;
import it.unicam.cs.mpgc.rpg129795.character.classes.ClassCatalog;
import it.unicam.cs.mpgc.rpg129795.character.classes.Subclass;
import it.unicam.cs.mpgc.rpg129795.items.relics.RelicCatalog;

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
                player.getRelic().id(), player.getCurrentHp(), entrance.id(), nextFloor));
    }

    public GameProgress loadProgress() {
        SaveData data = storage.load();

        CharacterClass characterClass = ClassCatalog.classById(data.classId());
        Subclass subclass = ClassCatalog.subclassById(characterClass, data.subclassId());

        PlayerCharacter player = new CharacterBuilder()
                .withName(data.playerName())
                .withClass(characterClass)
                .withSubclass(subclass)
                .withRelic(RelicCatalog.byId(data.relicId()))
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
