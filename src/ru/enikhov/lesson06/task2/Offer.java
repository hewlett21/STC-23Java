package ru.enikhov.lesson06.task2;

import java.util.Random;

/**
 * Класс Offer (предложение) наследует класс Word
 * формирует предложения
 *
 * @author Енихов Влад
 * @version 1.0
 */

public class Offer extends Word {
    private int min;
    private int max;

    private Word w;

    public Offer(Word w) {
        this.w = w;
    }

    @Override
    public void setMin(int min) {
        this.min = min;
    }

    @Override
    public void setMax(int max) {
        this.max = max;
    }

    public String getWordFromArray() {
        return AppFileGen.checkProbability();
    }

    /**
     * Метод формирует предложение
     * слова берутся либо из класса Word, либо из массива слов
     * Первое слово в предложении в верхнем регистре
     * запятая проставляется после слов, в случайном порядке
     * в конце предложения в случайном порядке ставятся "!", "?", "."
      * @return -- сформированное предложение
     */
    @Override
    public String genString() {
        AppFileGen afg = new AppFileGen();
        String out = "";
        String newString = "";
        String srcWord;
        int oldLength = 0;
        int cntSimbol = 0;
        int cntWords = rndSize(min, max);
        for (int i = 0; i < cntWords; i++) {
            srcWord = getWordFromArray();
            if (srcWord == "RANDOM") {
                newString += w.genString();
            } else {
                newString += srcWord;
            }
            if (i == 0) {
                newString = firstUpper(newString);
            }
            int maxComma = cntWords == 1 ? cntWords - 1 : cntWords - 2;
            newString += (i == rndSize(0, maxComma) ? ", " : " ");
        }

        out = newString.replaceFirst("\\s++$", "") + getSign() + " ";
        return out;
    }

    public String firstUpper(String word) {
        return word.substring(0, 1).toUpperCase() + word.substring(1);
    }

    public static String getSign() {
        String[] arr = {"!", "?", "."};
        int min = 0;
        int max = 2;
        Random random = new Random();
        int idxArray;
        idxArray = random.nextInt((max - min) + 1) + min;
        return arr[idxArray];
    }
}
