package it.unicam.cs.mpgc.rpg129795.Saves;

import com.google.gson.Gson;

import java.io.*;

public class JsonSaveStorage implements SaveStorage {

    private final File file;
    private final Gson gson = new Gson();

    public JsonSaveStorage(File file) {
        if (file == null) {
            throw new IllegalArgumentException("Save file cannot be null");
        }
        this.file = file;
    }

    public static JsonSaveStorage inUserHome() {
        return new JsonSaveStorage(new File(System.getProperty("user.home"), ".rpg129795/save.json"));
    }

    public void save(SaveData data) {
        file.getParentFile().mkdirs();   // crea la cartella se non esiste
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(file))) {
            gson.toJson(data, writer);
        } catch (IOException e) {
            throw new IllegalStateException("Could not save the game", e);
        }
    }

    public SaveData load() {
        if (!exists()) {
            throw new IllegalStateException("There is no saved game");
        }
        try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
            return gson.fromJson(reader, SaveData.class);
        } catch (IOException e) {
            throw new IllegalStateException("Could not load the game", e);
        }
    }

    public boolean exists() {
        return file.exists();
    }
}
