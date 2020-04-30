package ru.enikhov.lesson02.task3;

/**
 * Класс ArrayPerson со свойствами <b>person(ассив объектов)</b>, <b>umElems(размерность массива)</b>.
 *
 * @author Енихов Владислав
 * @version 1.0
 */

class ArrayPerson {
    private Person[] person;
    private int numElems;

    public ArrayPerson(int maxElems) {
        this.person = new Person[maxElems];
        this.numElems = 0;
    }

    /**
     * Процедура заполнения массива
     *
     * @param name - имя субъекта
     * @param age  -  возраст субъекта
     * @param sex  -  пол субъекта
     */
    public void insert(String name, int age, String sex) {
        person[numElems] = new Person(name, age, sex);
        numElems++;
    }

    public void display() {
        for (int i = 0; i < numElems; i++) {
            person[i].displayPerson();
        }
    }

    /**
     * Процедура сортировки вставками
     */
    public void insertSort() {
        int in, out;
        for (out = 1; out < numElems; out++) {
            Person temp = person[out];
            in = out;
            while (in > 0 &&
                    person[in - 1].compareTo(temp) > 0) {
                person[in] = person[in - 1];
                --in;
            }
            person[in] = temp;
        }
    }

    /**
     * Процедура быстрая сортировка
     *
     * @param leftBorder
     * @param rightBorder
     */
    public void quickSort(int leftBorder, int rightBorder) {
        int l = leftBorder;
        int r = rightBorder;
        Person pivot = person[(r + l) / 2];

        do {
            while (person[l].compareTo(pivot) < 0) {
                l++;
            }
            while (person[r].compareTo(pivot) > 0) {
                r--;
            }

            if (l <= r) {
                Person tmp = person[l];
                person[l] = person[r];
                person[r] = tmp;
                l++;
                r--;
            }
        } while (l <= r);

        if (leftBorder < r) {
            quickSort(leftBorder, r);
        }

        if (l < rightBorder) {
            quickSort(l, rightBorder);
        }
    }
}

public class SortApp {
    public static void main(String[] args) {
        int maxAge = 100;   // макимальный возраст
        int maxName = FirstName.values().length - 1;   // размерность enum FirstName
        int numMax = 10000; // размер массивов
        ArrayPerson personArray = new ArrayPerson(numMax); // массив для сортировки вставками
        ArrayPerson personArraySecond = new ArrayPerson(numMax); // массив для быстрой сортировки
        //заполняем два массива
        for (int i = 0; i < numMax; i++) {
            int rAge = rndPerson(maxAge);
            int rName = rndPerson(maxName);
            personArray.insert(getNameAttr(rName), rAge, getSexAttr(rName));
            personArraySecond.insert(getNameAttr(rName), rAge, getSexAttr(rName));
        }
        System.out.println("Сортировка вставками");
        System.out.println("== before sorting:");
        personArray.display();
        long startTime = System.currentTimeMillis();
        personArray.insertSort();
        long estimatedTime = System.currentTimeMillis() - startTime;
        System.out.println("== after sorting (сортировка вставками):");
        personArray.display();
        System.out.println("Время сортировки (сортировка вставками): " + estimatedTime + " мс.");

        System.out.println();
        System.out.println("Быстрая сортировка");
        System.out.println("== before sorting:");
        personArraySecond.display();
        long startTimeQiuck = System.currentTimeMillis();
        personArraySecond.quickSort(0, numMax - 1);
        long estimatedTimeQuick = System.currentTimeMillis() - startTime;
        System.out.println("== after sorting (быстрая сортировка):");
        personArraySecond.display();
        System.out.println("Время сортировки (быстрая сортировка): " + estimatedTimeQuick + " мс.");
    }

    /**
     * Процедура генерирует либо случайный возраст либо случайное имя (из перечисления FirstName),
     * в зависимости от параметра max.
     *
     * @param max - максимальный возраст или размерность перечисления
     * @return возвращает случайный возраст или порядковый номер константы перечисления
     */
    private static int rndPerson(int max) {
        return (int) (Math.random() * ++max);
    }

    /**
     * Процедура возвращает имя из перечисления FirstName по индексу.
     *
     * @param r - порядковый номер константы перечисления
     * @return возвращает имя субъекта
     */
    private static String getNameAttr(int r) {
        return FirstName.values()[r].getName();
    }

    /**
     * Процедура возвращает имя из перечисления FirstName по индексу.
     *
     * @param r - порядковый номер константы перечисления
     * @return возвращает пол субъекта
     */
    private static String getSexAttr(int r) {
        return FirstName.values()[r].getSex();
    }
}
