package ru.enikhov.lesson08;

import java.io.*;
import java.lang.reflect.Field;
import java.lang.reflect.Modifier;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class AppReflection {
    public static void main(String[] args) throws ClassNotFoundException, InstantiationException, IllegalAccessException {
        String file = "data.bin";
        List<String> list = Arrays.asList("яблоко", "груша", "апельсин");
        Person tom = new Person("Tom", 35, 1.65, true, list);
        System.out.print("Исходный объект:");
        System.out.println(tom);
        serialize(tom, file);
        System.out.println("Create new object:");
        deSerialize(file);
    }

    /** Метод заполняет поля нового объекта из файла
     *
     * @param newObj
     */
    private static void fieldSet(Object newObj, String file) {
        try (FileInputStream fis = new FileInputStream(file);
             DataInputStream dis = new DataInputStream(fis);
        ) {
            Field[] fieldType = newObj.getClass().getDeclaredFields();
            for (Field f : fieldType) {
                f.setAccessible(true);
                switch (f.getName()) {
                    case ("name"):
                        f.set(newObj, dis.readUTF());
                        break;
                    case ("age"):
                        f.set(newObj, dis.readInt());
                        break;
                    case ("height"):
                        f.set(newObj, dis.readDouble());
                        break;
                    case ("married"):
                        f.set(newObj, dis.readBoolean());
                        break;
                    case ("list"):
                        int sizeList = dis.readInt();
                        List<String> actualList = new ArrayList<>();
                        for (int i = 0; i < sizeList; i++) {
                            actualList.add(dis.readUTF());
                        }
                        f.set(newObj, actualList);
                }
            }
        } catch (FileNotFoundException fileNotFoundException) {
            fileNotFoundException.printStackTrace();
        } catch (IOException ioException) {
            ioException.printStackTrace();
        } catch (IllegalAccessException illegalAccessException) {
            illegalAccessException.printStackTrace();
        }
    }

    /**Создаем новый объект newTom с пустым значением полей
     * и вызываем метод для считывания значений полей
     *
     * @throws ClassNotFoundException
     * @throws IllegalAccessException
     * @throws InstantiationException
     */
    private static void deSerialize(String file) throws ClassNotFoundException, IllegalAccessException, InstantiationException {
        Class c = Class.forName("ru.enikhov.lesson08.Person");
        Person newTom = (Person) c.newInstance();
        System.out.println("Пустой объект: " + newTom);
        fieldSet(newTom, file);
        System.out.println("Новый объект: " + newTom);
    }

    /** метод serialize записывает в файл поля класс Person
     * для поля типа List, сначала записывается размер списка (будет использоваться
     * при создании нового объекта),
     * потом значения.
      * @param obj
     */
    private static void serialize(Person obj, String file) {
        Field[] fields = obj.getClass().getDeclaredFields();
        try (
                FileOutputStream fos = new FileOutputStream(file);
                DataOutputStream dos = new DataOutputStream(fos)
        ) {
            for (Field field : fields) {
                field.setAccessible(true);
                Object fValue = field.get(obj);
                switch (field.getName()) {
                    case ("name"):
                        dos.writeUTF(fValue.toString());
                        break;
                    case ("age"):
                        dos.writeInt((int) fValue);
                        break;
                    case ("height"):
                        dos.writeDouble((double) fValue);
                        break;
                    case ("married"):
                        dos.writeBoolean((boolean) fValue);
                        break;
                    case ("list"):
                        dos.writeInt(obj.getList().size());
                        for (String s : obj.getList()) {
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
