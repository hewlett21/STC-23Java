package ru.enikhov.lesson03;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

public class MathBox extends ObjectBox<Number> {

    /**
     * Конструктор - создание нового объекта с определенными значениями
     *
     * @param arrNum - массив объектов типа Number
     */
    public MathBox(Number[] arrNum) {
        setHash = new HashSet<Number>(Arrays.asList(arrNum));
    }

    /**
     * Метод summator - сумирует значения элементов коллекции
     *
     * @return - возвращает сумму элментов
     */
    public Double summator() {
        Double result = 0.0;
        for (Number num : setHash) {
            result += num.doubleValue();
        }
        return result;
    }

    /**
     * Метод splitter - деление всех хранящихся в объекте элементов на делитель
     *
     * @param del - делитель
     */
    public void splitter(int del) {
        Set<Number> copySet = new HashSet<>();
        for (Iterator<Number> it = setHash.iterator(); it.hasNext(); ) {
            Number current = it.next();
            Double newValue = current.doubleValue() / del;
            System.out.println("Current value: [" + current + "] : New value: [" + newValue + "]");
            copySet.add((Number) newValue);
        }
        setHash.clear();
        setHash.addAll(copySet);
    }


    @Override
    public String toString() {
        return "MathBox{" +
                "set=" + setHash +
                '}';
    }

    @Override
    public int hashCode() {
        return super.hashCode();
    }

    @Override
    public boolean equals(Object obj) {
        return super.equals(obj);
    }
}
