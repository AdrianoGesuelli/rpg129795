package it.unicam.cs.mpgc.rpg129795.Saves;

public interface SaveStorage {

    void save(SaveData data);

    SaveData load();

    boolean exists();

    void delete();

}
