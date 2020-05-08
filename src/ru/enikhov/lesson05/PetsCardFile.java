package ru.enikhov.lesson05;

import java.util.*;
import java.util.Map.Entry;

/**
 * Класс PetsCardFile -- каталог животных
 * <K> -- ключ каталога
 * <V> -- объект домашнее животное
 * Структура cardFileMap хранит каталог
 * Структура mapSearch используется для ускорения поиска по кличке
 */
public class PetsCardFile<K, V> {
    Map<K, V> cardFileMap = new HashMap<>();
    Map<String, V> mapSearch = new HashMap<>();


    /**
     * Метод используется для заполнения каталога(cardFileMap) и мапы для поиска (mapSearch)
     * если животное не уникально (по id, кличке и весу) то в мапы не заносим
     * выбрасываем исключение NotUniqueException
     *
     * @param pets - на входе ArrayList заполненный животными
     */
    public void putAnimal(Pet pets) throws NotUniqueException {
        UUID idLocal = UUID.randomUUID();
        int cnt = Collections.frequency(cardFileMap.values(), pets);
        if (cnt != 0) {
            throw new NotUniqueException("Не уникальное животное: ", pets.getId(), pets.getNikname(), pets.getWeight());
        }
        cardFileMap.put((K) idLocal, (V) pets);
        mapSearch.put(pets.getNikname(), (V) pets);
    }

    /**
     * Метод используется для поиска животного по кличке в стуктуре mapSearch
     *
     * @param  -- nickname - кличка
     * @return -- возвращает найденный объект класса животного
     */
    public V searchPet(String nickname) {
        return (V) mapSearch.get(nickname);
    }

    /**
     * Метод используется для изменения клички животного по UUID в каталоге cardFileMap
     *
     * @param -- uuid - UUID для поиска в каталоге
     * @param  -- newNickname - новая кличка
     * @return -- возвращает найденный объект класса животного
     */
    public void changePet(UUID uuid, String newNikname) {
        Pet value = (Pet) cardFileMap.get(uuid);
        value.setNikname(newNikname);
        mapSearch.put(newNikname, (V) value);
        cardFileMap.put((K) uuid, (V) value);
    }

    /**
     * Метод используется для вывода на экран отсортированного каталога cardFileMap
     * порядок сортировки: хозяин (который в свою очередь сортируется по: пол, имя, возраст), кличка животного, вес.
     * @param -- map - каталог cardFileMap
     */

// я знаю почему именно так :
// public <K, V extends Comparable<? super V>> void displaySort(Map<K, V> map)
// нагуглил. Без передачи в параметре мапы, не работает почему то.
    public <K, V extends Comparable<? super V>> void displaySort(Map<K, V> map) {
        List<Map.Entry<K, V>> list = new ArrayList<>(map.entrySet());
        list.sort(Entry.comparingByValue());
        for (Entry<K, V> entry : list) {
            System.out.println("UUID каталога: " + entry.getKey() + ": " + entry.getValue().toString().replace("\n", ""));
        }
    }

    @Override
    public String toString() {
        String result = "";
        for (Entry<K, V> entry : cardFileMap.entrySet()) {
            result += ("UUID каталога: " + entry.getKey().toString() + ": " + entry.getValue().toString());
        }
        return result;
    }
}
