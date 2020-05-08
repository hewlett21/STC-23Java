package ru.enikhov.lesson05;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class AppPets {
    static PetsCardFile<UUID, Pet> petStore = new PetsCardFile<>();

    public static void main(String[] args) throws IOException {
        List<Pet> pet = new ArrayList<>();

// заполняем ArrayList исходными данными
        pet.add(new Cat(100, "Барсик", personList().get(0), 5));
        pet.add(new Cat(101, "Пушок", personList().get(1), 4)); //дубль
        pet.add(new Cat(102, "Том", personList().get(2), 7));
        pet.add(new Cat(101, "Пушок", personList().get(2), 4)); //дубль
        pet.add(new Cat(103, "Васька", personList().get(2), 4));
        pet.add(new Cat(104, "Барсик", personList().get(0), 3));

        pet.add(new Dog(200, "Тузик", personList().get(3), 15));
        pet.add(new Dog(201, "Полкан", personList().get(4), 20));
        pet.add(new Dog(202, "Мухтар", personList().get(5), 30)); //дубль
        pet.add(new Dog(202, "Мухтар", personList().get(4), 30)); //дубль

        System.out.println("Начальный список: ");
        System.out.println(pet);
        System.out.println();
        System.out.println("Заполняем картотеку из ArrayList");
        for (Pet p : pet) {
            try {
                petStore.putAnimal(p);
            } catch (NotUniqueException ex) {
                System.out.println(ex.getMessage() + ": id = " +
                        ex.getIdNotUnique() + ", Кличка = " +
                        ex.getNicknameNotUnique() + ", Вес = " +
                        ex.getWeightNotUnique());
            }
        }

        System.out.println("Каталог:");

        System.out.println(petStore.toString());
        System.out.println("===================");
        seekNiknamePet();
        changeParamPet();
        System.out.println("Отсортированная картотека:");
        petStore.displaySort(petStore.cardFileMap); // Без передачи в параметре мапы, не работает почему то.
    }

    /**
     * В методе указываем кличку животного по UUID в каталоге cardFileMap
     */
    private static void seekNiknamePet() throws IOException {
        String input;
        while (true) {
            System.out.println("Укажите кличку для поиска:");
            input = getString();
            if (input.equals("")) {
                break;
            }
            Pet result = petStore.searchPet(input);
            System.out.println(result == null ? "Животное с такой кличкой не найдено." : result);
        }
    }


    /**
     * В методе указываем UUID и новую кличку животного, для замены по UUID в каталоге cardFileMap
     */
    private static void changeParamPet() throws IOException {
        String input, newNikname;
        while (true) {
            System.out.println("Укажите UUID для замены:");
            input = getString();
            if (input.equals("")) {
                break;
            }
            System.out.println("Укажите новую кличку:");
            newNikname = getString();
            if (newNikname.equals("")) {
                break;
            }
            petStore.changePet(UUID.fromString(input), newNikname);
            System.out.println("Измененный каталог:");
            System.out.println(petStore.toString());
        }
    }

    /**
     * В методе считываем введенные с клаиатуры данные
     */
    private static String getString() throws IOException {
        InputStreamReader isr = new InputStreamReader(System.in);
        BufferedReader br = new BufferedReader(isr);
        String strIn = br.readLine();
        return strIn;
    }

    /**
     * В методе формируем зозяина животного
     */
    static List<Person> personList() {
        List<Person> person = new ArrayList<>();
        person.add(new Person("Андрей", 30, "М"));
        person.add(new Person("Елена", 20, "Ж"));
        person.add(new Person("Анна", 45, "Ж"));
        person.add(new Person("Александр", 25, "М"));
        person.add(new Person("Татьяна", 20, "Ж"));
        person.add(new Person("Павел", 40, "М"));
        return person;
    }
}
