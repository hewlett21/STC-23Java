package ru.enikhov.lesson03;

import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

public class ObjectBox<Object> {
    Set<Object> set = new HashSet<>();

    /**
     * Метод addObject - добавляет элемент в коллекцию
     *
     * @param o - элемент типа Object
     */
    public void addObject(Object o) {
        set.add(o);
    }

    /**
     * Метод deleteObject - удаляет элемент из коллекции.
     * В случае наличия объекта в коллекции возвращает удаленный объект
     *
     * @param o - элемент типа Object
     * @return - возвращаем объект
     */
    public Object deleteObject(Object o) {
        if (set.contains(o)) {
            set.remove(o);
            return o;
        } else {
            return (Object) "не найдено";
        }
    }

    /**
     * Метод dump - выводит содержимое коллекции.
     */
    public void dump() {
        Iterator<Object> iterator = set.iterator();
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
