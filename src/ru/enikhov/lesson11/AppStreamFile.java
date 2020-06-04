package ru.enikhov.lesson11;

import java.io.*;
import java.nio.charset.Charset;
import java.util.*;
import java.util.stream.Collectors;

public class AppStreamFile {
    static List<String> list = new ArrayList<>();
    static List<String> result = new ArrayList<>();

    /**
     * читаем текстовый файл note.txt
     *
     * @param args
     */
    public static void main(String[] args) {
        String fileName = "note.txt";
        try (FileInputStream fis = new FileInputStream(fileName);
             InputStreamReader isr = new InputStreamReader(fis, Charset.forName("CP1251"));
             BufferedReader reader = new BufferedReader(isr);
        ) {
            String line;
            System.out.println("=== До сортировки:");
            while ((line = reader.readLine()) != null) {
                String[] arr = line.toLowerCase().trim().split(" ");
                System.out.println(line);
                addList(arr);
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
        System.out.println();
        sortList(list);
        System.out.println("=== После сортировки:");
        result.forEach(System.out::println);

        writeStep1(result);
    }

    /**
     * метод сохраняет слова из считанного текста в промежуточный list
     *
     * @param arr
     */
    public static void addList(String[] arr) {
        for (String s : arr) {
            list.add(s);
        }
    }

    /**
     * метод сортирует список list с помощью stream, в результирующий список result
     *
     * @param lst
     */
    public static void sortList(List<String> lst) {
        result = lst
                .stream()
                .sorted((o1, o2) -> o1.compareToIgnoreCase(o2))
                .distinct()
                .collect(Collectors.toList());

    }


    /**
     * метод записывает отсортированную коллекцию слов в файл sorted.txt
     *
     * @param list
     */
    private static void writeStep1(List<String> list) {
        String output = "sorted.txt";
        try (FileOutputStream fos = new FileOutputStream(output);
             OutputStreamWriter osw = new OutputStreamWriter(fos, Charset.forName("CP1251"));
             BufferedWriter writer = new BufferedWriter(osw);
        ) {
            list.forEach((str) -> writeToFile(str, writer));
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }

    private static void writeToFile(String s, BufferedWriter writer) {
        try {
            writer.write(s + "\n");
        } catch (
                IOException e) {
            System.out.println(e.getMessage());
        }
    }
}
