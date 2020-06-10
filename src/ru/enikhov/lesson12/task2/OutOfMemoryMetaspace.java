package ru.enikhov.lesson12.task2;

import java.lang.reflect.Proxy;
import java.net.URL;
import java.net.URLClassLoader;
import java.util.*;

/**
 * -XX:MaxMetaspaceSize=100m
 * генерируем ошибку OutOfMemoryError: Metaspace
 * в цикле создаем proxy и сохраняем в static list
 */
public class OutOfMemoryMetaspace {
    private static List<SomeInterface> list = new ArrayList<>();

    public static void main(String[] args) throws InterruptedException {
        int iterations = 0;
        try {
            while (true) {
                String classLoaderJar = "file:" + iterations + ".jar";
                URL[] urls = new URL[]{new URL(classLoaderJar)};
                URLClassLoader urlClassLoader = new URLClassLoader(urls);
                SomeInterface classImpl = new SomeClassImpl();
                SomeInterface proxy = (SomeInterface) Proxy.newProxyInstance(urlClassLoader,
                        SomeClassImpl.class.getInterfaces(),
                        new SomeClassInvoke(classImpl));
                proxy.someMethod(classLoaderJar);
                iterations++;
                list.add(proxy);
                Thread.sleep(1);
            }
        } catch (Throwable e) {
            e.printStackTrace();
        }
    }
}
