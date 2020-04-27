package ru.enikhov.lesson02.task3;

/**
 * Перечисление FirstName одержит список возможных имен и пола субъекта.
 *
 * @version 1.0
 * @author Енихов Владислав
 */

public enum FirstName {
    Анна("Women"), Алла("Women"), Елизавета("Women"), Екатерина("Women"), Елена("Women"),
    Людмила("Women"), Наталья("Women"), Оксана("Women"), Татьяна("Women"), Юлия("Women"),
    Агафон("Man"), Алексей("Man"), Борис("Man"), Георгий("Man"), Дмитрий("Man"), Иван("Man"),
    Олег("Man"), Пантелеймон("Man"), Сергей("Man"), Яков("Man");

    private String sex;

    FirstName(String sex) {
        this.sex = sex;
    }

    public String getSex() {
        return sex;
    }

    public String getName(){
        return name();
    }
}
