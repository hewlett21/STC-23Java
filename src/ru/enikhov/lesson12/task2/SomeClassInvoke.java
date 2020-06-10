package ru.enikhov.lesson12.task2;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;

public class SomeClassInvoke implements InvocationHandler {
    private Object obj;

    public SomeClassInvoke(Object o1) {
        obj = o1;
    }

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        System.out.print("Снова и снова ");
        return method.invoke(obj, args);
    }
}
