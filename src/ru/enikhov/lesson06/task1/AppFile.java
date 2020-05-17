package ru.enikhov.lesson06.task1;

import java.io.*;
import java.nio.charset.Charset;
import java.util.*;

public class AppFile {
    static Set<String> set = new TreeSet<>(String.CASE_INSENSITIVE_ORDER);

    /** читаем текстовый файл note.txt
     *
      * @param args
     */
    public static void main(String[] args) {
        String fileName = "note.txt";
        List<String> list = new ArrayList<>();
        try (FileInputStream fis = new FileInputStream(fileName);
             InputStreamReader isr = new InputStreamReader(fis, Charset.forName("CP1251"));
             BufferedReader reader = new BufferedReader(isr);
        ) {
            String line;
            System.out.println("=== До сортировки:");
            while ((line = reader.readLine()) != null) {
                String[] arr = line.trim().split(" ");
                list = Arrays.asList(arr);
                addSet(arr);
                System.out.println(line);
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
        System.out.println();
        System.out.println("=== После сортировки:");
        for (String lst : set) {
            System.out.println(lst);
        }

        writeToFile(set);
    }

    /** метод сохраняет слова из считанного текста в TreeSet
     * т.о. сортируем их
     *
      * @param arr
     */
    public static void addSet(String[] arr) {
        for (String s : arr) {
            set.add(s);
        }
    }

    /** метод записывает отсортированную коллекцию слов в файл sorted.txt
     *
      * @param set
     */
    private static void writeToFile(Set<String> set) {
        String output = "sorted.txt";
        try (FileOutputStream fos = new FileOutputStream(output);
             OutputStreamWriter osw = new OutputStreamWriter(fos, Charset.forName("CP1251"));
             BufferedWriter writer = new BufferedWriter(osw);
        ) {
            String line;
            for (String s : set) {
                writer.write(s + "\n");
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }
}
