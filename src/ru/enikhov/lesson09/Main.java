package ru.enikhov.lesson09;

import javax.tools.*;
import java.io.*;
import java.util.Arrays;

/**
 * в консоли нужно ввести:
 * package ru.enikhov.lesson09; public class SomeClass implements Worker { public void doWork() { System.out.print("Hello world");}}
 * Enter - прекращение ввода
 * из package ru.enikhov.lesson09 не работает, не находит файлы
 * толком не понял где и как пути прописывать, чтобы работало из пакета
 * Работает, если все перенести в src и тогда в тексте надо убрать фразу "package ru.enikhov.lesson09; "
 */

public class Main {
    private static String nameClass = "SomeClass";


    public static void main(String[] args) {
        File sourceFile = new File(nameClass + ".java");
        try (FileWriter writer = new FileWriter(sourceFile);
        ) {
            String code;
            while (true) {
                code = getCode();
                if (code.equals("")) {
                    break;
                }
                System.out.println(code);
                writer.write(code);
            }
            writer.close();
            compileSource(sourceFile);
            useCustomClassLoader();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            System.out.println(e.getMessage());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * метод вызывает кастомный загрузчик MyClassLoader
     *
     * @throws Exception
     */
    private static void useCustomClassLoader() throws Exception {
        ClassLoader cl = new MyClassLoader();
        Class<?> someClass = cl.loadClass(nameClass);
        Worker w = (Worker) someClass.newInstance();
        w.doWork();
    }

    /**
     * Метод компилирует код, сохраненный в файл SomeClass.java
     *
     * @param sourceFile
     * @throws IOException
     */
    private static void compileSource(File sourceFile) throws IOException {
        JavaCompiler compiler = ToolProvider.getSystemJavaCompiler();
        StandardJavaFileManager fileManager = compiler.getStandardFileManager(null, null, null);
        Iterable<? extends JavaFileObject> compilationUnits = fileManager.getJavaFileObjectsFromFiles(Arrays.asList(sourceFile));
        compiler.getTask(null, fileManager, null, null, null, compilationUnits).call();
        fileManager.close();
    }

    /**
     * метод считывает с клавиатуры текст
     *
     * @return
     * @throws IOException
     */
    private static String getCode() throws IOException {
        InputStreamReader isr = new InputStreamReader(System.in);
        BufferedReader br = new BufferedReader(isr);
        String res = br.readLine();
        return res;
    }
}
