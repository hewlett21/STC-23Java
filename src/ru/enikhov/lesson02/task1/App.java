package ru.enikhov.lesson02.task1;

class MyException extends Exception {
}

public class App {

    public static void main(String[] args) {
        String[] stringArray = new String[3];
        stringArray[0] = "Hello, World!";
        stringArray[2] = "<Hello, World>";
        for (String arr : stringArray) {
            try {
                hello(arr);
            } catch (NullPointerException npe) {
                System.out.println("2. NPE: Объект пустой.");
            } catch (MyException me) {
                System.out.println("3. MyException: Это не фраза Hello, World!");
            }
        }
    }

    static void hello(String s) throws MyException {
        char[] chars = s.toCharArray();
        try {
            if (!s.equals("Hello, World!")) {
                throw new MyException();
            }
            System.out.println(s);
            System.out.println(chars[chars.length]);
        } catch (ArrayIndexOutOfBoundsException are) {
            System.out.println("1. ArrayIndexOutOfBounds: Вышли за границу массива");
        }
    }
}
