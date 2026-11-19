package it.unicam.cs.mpgc.rpg129795.Saves;

public record SaveData(String playerName, String classId, String subclassId, int currentHp, String entranceId, int floor) {

    public SaveData {
        if (playerName == null || classId == null || subclassId == null
                || entranceId == null || currentHp <= 0 || floor < 0) {
            throw new IllegalArgumentException("Invalid save data");
        }
    }
}
