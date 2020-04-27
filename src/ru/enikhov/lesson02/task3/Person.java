package ru.enikhov.lesson02.task3;

/**
 * Класс Person со свойствами <b>name(Имя)</b>, <b>sex(Пол)</b> и <b>age(Возраст)</b>.
 *
 * @version 1.0
 * @author Енихов Владислав
 */
public class Person implements Comparable<Person> {
    /**
     * Поле имя
     */
    private String name;

    /**
     * Поле возраст
     */
    private int age;

    /**
     * Поле пол
     */
    private String sex;

    public String getName() {
        return name;
    }

    public int getAge() {
        return age;
    }

    public String getSex() {
        return sex;
    }

    /**
     * Конструктор - создание нового объекта с определенными значениями
     * @param name - имя субъекта
     * @param age - возраст субъекта
     * @param sex - пол субъекта
     */
    public Person(String name, int age, String sex) {
        this.name = name;
        this.age = age;
        this.sex = sex;
    }

    /**
     * Процедура вывода массива на экран
     */
    public void displayPerson() {
        System.out.print("Name: " + name);
        System.out.print(", Age: " + age);
        System.out.println(", Sex: " + sex);
    }

    /**
     * Переопределенный метов compareTo
     * порядок сортировки: пол, имя, возраст
     */
    @Override
    public int compareTo(Person p) {
        int result;
        result = sex.compareTo(p.sex);
        if (result != 0) {
            return result;
        }
        result = name.compareTo(p.name);
        if (result != 0) {
            return result;
        }
        result = name.compareTo(p.name);
        if (result != 0) {
            return result;
        }
        result = age - p.getAge();
        return result;
    }
}
