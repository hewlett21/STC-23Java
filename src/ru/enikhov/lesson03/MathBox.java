package ru.enikhov.lesson03;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

public class MathBox<T> extends ObjectBox {

    /**
     * Конструктор - создание нового объекта с определенными значениями
     *
     * @param arrNum - массив объектов типа Number
     */
    public MathBox(Number[] arrNum) {
        set = new HashSet<>(Arrays.asList(arrNum));
    }

    /**
     * Метод summator - сумирует значения элементов коллекции
     *
     * @param numSet - коллекция HashSet<T extends Number>
     * @return - возвращает сумму элментов
     */
    public <T extends Number> Double summator(Set<T> numSet) {
        Double result = 0.0;
        for (Number num : numSet) {
            result += num.doubleValue();
        }
        return result;
    }

    /**
     * Метод splitter - деление всех хранящихся в объекте элементов на делитель
     *
     * @param numSet - коллекция HashSet<T extends Number>
     * @param del    - делитель
     */

    public <T extends Number> void splitter(Set<T> numSet, int del) {
        Set<T> copySet = new HashSet<>(set);
        for (Iterator<T> it = copySet.iterator(); it.hasNext(); ) {
            T current = it.next();
            Double newValue = current.doubleValue() / del;
            System.out.println("Current value: [" + current + "] : New value: [" + newValue + "]");
            numSet.remove(current);
            numSet.add((T) newValue);
        }
    }

    @Override
    public String toString() {
        return "MathBox{" +
                "set=" + set +
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
