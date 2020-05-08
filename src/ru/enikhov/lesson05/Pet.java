package ru.enikhov.lesson05;

import java.util.Objects;

/**
 * Класс Pet со свойствами <b>nikname(Кличка)</b>, <b>id(Идентификатор)</b>, <b>weight(Вес)</b>
 * и <b>Person({Хозяин})</b>
 * @author Енихов Влад
 * @version 1.0
 */

public class Pet implements Comparable<Pet> {
    private int id;
    private String nikname;
    private Person owner;
    private int weight;

    public int getWeight() {
        return weight;
    }

    public Person getOwner() {
        return owner;
    }

    public void setOwner(Person owner) {
        this.owner = owner;
    }

    public void setWeight(int weight) {
        this.weight = weight;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getId() {
        return id;
    }

    public String getNikname() {
        return nikname;
    }

    public void setNikname(String nikname) {
        this.nikname = nikname;
    }

    /**
     * Конструктор - создание нового объекта с определенными значениями
     *
     * @param id - id животного
     * @param nikname  - кличка
     * @param weight  - вес
     * @param owner  - хозяин
     */

    public Pet(int id, String nikname, Person owner, int weight) {
        this.id = id;
        this.nikname = nikname;
        this.owner = owner;
        this.weight = weight;
    }

    /**
     * Переопределяем метод equals, для определения уникальности
     * животного по id, кличке и весу
     * @param o - объект животное
     */
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Pet pet = (Pet) o;
        return getId() == pet.getId() &&
                weight == pet.weight &&
                getNikname().equals(pet.getNikname());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId(), getNikname(), weight);
    }

    /**
     * Переопределяем метод compareTo, для сравнения
     * животного по хозяину, кличке и весу
     * @param o - объект животное
     */

    //    @Override
    public int compareTo(Pet o) {
        int result;
        result = owner.compareTo(o.owner);
        if (result != 0) {
            return result;
        }
        result = nikname.compareTo(o.nikname);
        if (result != 0) {
            return result;
        }
        result = weight - o.getWeight();
        return result;
    }

    @Override
    public String toString() {
        return getClass().getSimpleName() + "{" +
                "id=" + id +
                "; Кличка='" + nikname + '\'' +
                "; Вес=" + weight +
                " кг.; Хозяин=" + owner +
                "}\n";
    }
}

