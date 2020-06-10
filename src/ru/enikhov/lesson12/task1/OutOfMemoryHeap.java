package ru.enikhov.lesson12.task1;

import ru.enikhov.lesson12.task2.SomeClassImpl;
import ru.enikhov.lesson12.task2.SomeClassInvoke;
import ru.enikhov.lesson12.task2.SomeInterface;

import java.lang.reflect.Proxy;
import java.net.URL;
import java.net.URLClassLoader;
import java.util.*;

/**
 * -XX:+UseSerialGC -Xmx100m
 * генерируем ошибку OutOfMemoryError: Java heap space
 */
public class OutOfMemoryHeap {
    public static void main(String[] args) throws InterruptedException {
        List<String> list = new ArrayList<>();
        int i = 0;
        Random random = new Random();
        while (true) {
            i++;
            System.out.println(i + ". Memory: " + Runtime.getRuntime().freeMemory());
            String str = "" + random.nextInt();
            list.add(str);
            if (i % 5000 == 0) {
                Thread.sleep(1);
                list.remove(0);
            }
        }
    }
}
