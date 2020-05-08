package ru.enikhov.lesson05;

public class NotUniqueException extends Exception {
    private int idNotUnique;
    private String nicknameNotUnique;
    private int weightNotUnique;

    public int getIdNotUnique() {
        return idNotUnique;
    }

    public String getNicknameNotUnique() {
        return nicknameNotUnique;
    }

    public int getWeightNotUnique() {
        return weightNotUnique;
    }

    public NotUniqueException(String message, int idNotUnique, String nicknameNotUnique, int weightNotUnique) {
        super(message);
        this.idNotUnique = idNotUnique;
        this.nicknameNotUnique = nicknameNotUnique;
        this.weightNotUnique = weightNotUnique;
    }
}
