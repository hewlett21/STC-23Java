package ru.enikhov.lesson02.task2;

public class RandomApp {
    public static void main(String[] args) {
        int min = -10;
        int max = 100;
        for (int i = 0; i <= 100; i++) {
            int r = rnd(min, max);
            try {
                if (r < 0) throw new ArithmeticException(" Отрицательное число");
                int square = (int) Math.sqrt(r) * (int) Math.sqrt(r);
                if (r == square) {
                    System.out.print(r);
                    System.out.println(" " + Math.sqrt(r) + " " + square);
                }
            } catch (ArithmeticException e) {
                System.out.println(r + e.getMessage());
            }
        }
    }

    private static int rnd(int min, int max) {
        max -= min;
        return (int) (Math.random() * ++max) + min;
    }
}
