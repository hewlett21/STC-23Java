package ru.enikhov.lesson12.task2;

public class SomeClassImpl implements SomeInterface {
    @Override
    public void someMethod(String s) {
        System.out.println("что то делаю..." + s);
    }
}
