package ru.enikhov.lesson03;

import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

public class ObjectBox<T> {
    Set<T> setHash = new HashSet<>();

    /**
     * Метод addObject - добавляет элемент в коллекцию
     *
     * @param o - элемент типа Object
     */
    public void addObject(T o) {
        setHash.add(o);
    }

    /**
     * Метод deleteObject - удаляет элемент из коллекции.
     * В случае наличия объекта в коллекции возвращает удаленный объект
     *
     * @param o - элемент типа Object
     * @return - возвращаем объект
     */
    public Object deleteObject(T o) {
        if (setHash.contains(o)) {
            setHash.remove(o);
            return o;
        } else {
            return (Object) "не найдено";
        }
    }

    /**
     * Метод dump - выводит содержимое коллекции.
     */
    public void dump() {
        Iterator<T> iterator = setHash.iterator();
        System.out.print("ObjectBox{set=[");
        while (iterator.hasNext()) {
            System.out.print(iterator.next().toString());
            if (iterator.hasNext()) {
                System.out.print(", ");
            }
        }
        System.out.println("]}");
    }
}
