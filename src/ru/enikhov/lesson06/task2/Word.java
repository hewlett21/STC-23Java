package ru.enikhov.lesson06.task2;

import java.util.Random;

/**
 * Класс Word формирует слова
 *
 * @author Енихов Влад
 * @version 1.0
 */

public class Word {
    private int min;
    private int max;

    public void setMin(int min) {
        this.min = min;
    }

    public void setMax(int max) {
        this.max = max;
    }

    /** Метод формирует слова на основе латинских букв по таблице ascii
     *
      * @return - сформированное слово
     */
    public String genString() {
        int minAscii = 97;
        int maxAscii = 122;
        int newLetter;
        String newString = "";
        int cntLetters = rndSize(min, max);
        for (int i = 0; i < cntLetters; i++) {
            newLetter = rndSize(minAscii, maxAscii);
            newString += (char) newLetter;
        }
        return (newString);
    }

    /** Метод возвращает случайный код ascii
     *
      * @param min -- минимальное кол-во букв в слове
     * @param max  -- максимальное кол-во букв в слове
     * @return -- код ascii
     */
    public int rndSize(int min, int max) {
        Random random = new Random();
        return random.nextInt((max - min) + 1) + min;
    }
}
