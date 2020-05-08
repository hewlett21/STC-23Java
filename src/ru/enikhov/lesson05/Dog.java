package ru.enikhov.lesson05;

/**
 * Класс Dog наследует класс Pet
 * @author Енихов Влад
 * @version 1.0
 */

public class Dog extends Pet {
    public Dog(int id, String nikname, Person owner, int weight) {
        super(id, nikname, owner, weight);
    }
}
