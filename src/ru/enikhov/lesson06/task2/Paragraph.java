package ru.enikhov.lesson06.task2;

/**
 * Класс Paragraph (абзац) наследует класс Offer
 * формирует предложения
 *
 * @author Енихов Влад
 * @version 1.0
 */

public class Paragraph extends Offer {
    private int min;
    private int max;
    private Offer o;

    public Paragraph(Word w, Offer o) {
        super(w);
        this.o = o;
    }

    @Override
    public void setMin(int min) {
        this.min = min;
    }

    @Override
    public void setMax(int max) {
        this.max = max;
    }

    /**
     * Метод генерирует абзацы из предложений полученных из класса Offer
     *
     * @return
     */
    @Override
    public String genString() {
        String out;
        String newString = "";
        int cntOffers = rndSize(min, max);
        for (int i = 0; i < cntOffers; i++) {
            newString += o.genString();
        }
        out = newString.replaceFirst("\\s++$", "") + "\n\r";
        return out;
    }
}
