package it.unicam.cs.mpgc.rpg129795.saves;

public record SaveData(String playerName, String classId, String subclassId, String relicId, int currentHp, String entranceId, int floor) {

    public SaveData {
        if (playerName == null || classId == null || subclassId == null || relicId == null || entranceId == null) {
            throw new IllegalArgumentException("Invalid save data");
        }
    }
}
