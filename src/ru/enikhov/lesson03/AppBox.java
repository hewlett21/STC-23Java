package ru.enikhov.lesson03;

/**
 * Класс AppBox.
 *
 * @author Енихов Владислав
 * @version 1.0
 */

public class AppBox {
    /**
     * Создаем экземпляр ObjectBox
     * метод экземпляра addObject - добавляет объект в коллекцию
     * метод экземпляра dump - выводит на экран содержимое коллекции
     * метод экземпляра deleteObject - удаляет указааный объект из коллекци
     * <p>
     * Создаем и заполяем массив с типом Number[]
     * Создаем экземпляр MathBox, передавая в конструктор массив Numbers[]
     * метод экземпляра summator - суммирует элементы коллекции
     * метод экземпляра splitter - делит каждый элемент коллекции на указанный при вызове метода
     * делитель.
     */

    public static void main(String[] args) {
        ObjectBox<Object> objectBox = new ObjectBox<>();
        objectBox.addObject(new ObjectBox<>());
        objectBox.addObject(10.10);
        objectBox.addObject(2f);
        objectBox.addObject(5d);
        objectBox.addObject(107.77);
        objectBox.addObject(0.78);

        objectBox.dump();
        System.out.print("Deleted: ");
        System.out.println(objectBox.deleteObject(10.10));
        objectBox.dump();
        System.out.println("==============");

        Number[] numbers = new Number[10];
        numbers[0] = 10;
        numbers[1] = 2f;
        numbers[2] = 200.05;
        numbers[3] = 777;
        numbers[4] = 1.1;
        numbers[5] = 0.05;
        numbers[6] = 15.75;
        numbers[7] = 70;
        numbers[8] = 701L;
        numbers[9] = 27d;

        MathBox<Number> mathBox = new MathBox<>(numbers);
        System.out.println(mathBox.toString());
        System.out.println(mathBox.summator(mathBox.set));

        System.out.println("====================");
        mathBox.splitter(mathBox.set, 3);
        System.out.println(mathBox.toString());
    }
}
