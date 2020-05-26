package ru.enikhov.lesson08;

import java.io.*;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.util.Arrays;
import java.util.List;

public class AppReflection {
    public static void main(String[] args) throws ClassNotFoundException, InstantiationException, IllegalAccessException {
        List<String> list = Arrays.asList("яблоко","груша","апельсин");
        Person tom = new Person("Tom", 35, 1.65, true, list);
        System.out.println(tom);
        saveObj(tom);
        System.out.println("Create new object:");
        createObj();
    }

    private static void createObj() throws ClassNotFoundException, IllegalAccessException, InstantiationException {
        Class c = Class.forName("ru.enikhov.lesson08.Person");
        Object newTom = c.newInstance();
        System.out.println("Пустой объект: " + newTom);
        try (FileInputStream fis = new FileInputStream("data.bin");
             DataInputStream dis = new DataInputStream(fis);
        ) {
            Field name = newTom.getClass().getDeclaredField("name");
            name.setAccessible(true);
            name.set(newTom, dis.readUTF());
            Field age = newTom.getClass().getDeclaredField("age");
            age.setAccessible(true);
            age.setInt(newTom, dis.readInt());
            Field height = newTom.getClass().getDeclaredField("height");
            height.setAccessible(true);
            height.setDouble(newTom, dis.readDouble());
            Field married = newTom.getClass().getDeclaredField("married");
            married.setAccessible(true);
            married.setBoolean(newTom, dis.readBoolean());
            Field list = newTom.getClass().getDeclaredField("list");
            list.setAccessible(true);
//????
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (NoSuchFieldException e) {
            e.printStackTrace();
        }
        System.out.println("Новый объект: " + newTom);
    }

    private static void saveObj(Person obj) {
        Field[] fields = obj.getClass().getDeclaredFields();
        System.out.println("----------------------");
        try (
                FileOutputStream fos = new FileOutputStream("data.bin");
                DataOutputStream dos = new DataOutputStream(fos)
        ) {

            for (Field field : fields) {
                System.out.print(
                        Modifier.toString(field.getModifiers()) + " " +
                                field.getType().getSimpleName() + " " +
                                field.getName() + ": ");
                field.setAccessible(true); // доступ к приватному полю
                System.out.println(field.get(obj));
                System.out.println("========================");
                Object fValue = field.get(obj);
                switch (field.getType().getSimpleName()) {
                    case ("String"):
                        dos.writeUTF(fValue.toString());
                        System.out.println("Поле " + fValue +" записано");
                        break;
                    case ("int"):
                        dos.writeInt((int) fValue);
                        System.out.println("Поле " + fValue +" записано");
                        break;
                    case ("double"):
                        dos.writeDouble((double) fValue);
                        System.out.println("Поле " + fValue +" записано");
                        break;
                    case ("boolean"):
                        dos.writeBoolean((boolean) fValue);
                        System.out.println("Поле " + fValue +" записано");
                        break;
                }
                String type = field.getType().getSimpleName();
                if (type.equals("List")) {
                    System.out.println(field.get(obj));
                    for (String s: obj.getList()) {
                            dos.writeUTF(s);
                    }
                }
            }
        } catch (IOException |
                IllegalAccessException e) {
            System.out.println(e.getMessage());
        }
    }
}
