package ru.enikhov.lesson06.task2;

import java.io.*;

/**
 * class AppFileGen формирует файлы с произвольным тестом
 * размер файла задается в поле sizeFile
 * кол-во файлов в переменной cntFiles, метода main
 * массив arr заполняется из текста source
 * путь в файловой системе, где создаются файлы, задается в переменной path.
 */
public class AppFileGen {
    private static int sizeFile = 1000;
    private static String[] arr;
    private static int probability = 2;

    public static void main(String[] args) {
        String source = "Tell me, Uncle, it was not casually " +
                "That Moscow, burned by the fires, " +
                "Was given up to the French?\n" +
                "For there were battles indeed,\n" +
                "Oh, yes, people say they were really something!\n" +
                "Not in vain will all Russia remember\n" +
                "The day of Borodino!\n" +
                "Yes, there were folks in our time,\n" +
                "Not like the chaps we see today:\n" +
                "They were heroes, not like you!\n" +
                "Their luck turned rotten:\n" +
                "Very few returned from the field\n" +
                "And if it had not been God's will,\n" +
                "We would not have given up Moscow!\n" +
                "We withdrew for a while in silence,\n" +
                "Anxiously awaiting the battle,\n" +
                "And the old guard grumbled:\n" +
                "What? Us go to our wintertime lodgings?\n" +
                "Will our commanders not dare\n" +
                "To rip up the foreign uniforms\n" +
                "With their Russian bayonets?\"\n" +
                "And then we found a wide field,\n" +
                "Where one could walk as far as he pleased!\n" +
                "We built a redoubt there.\n" +
                "We'll keep our ears open!\n" +
                "No sooner had the dawn shone light on the cannons\n" +
                "And the blue tops of the trees,\n" +
                "The French stood before us!\n" +
                "I loaded the gun tightly\n" +
                "And thought, I'll play host to our friends!\n" +
                "Just wait, brother-Messieurs!\n" +
                "Why try to dodge it? Let's have a battle;\n" +
                "We’re ready to hit them like a wall,\n" +
                "We’re going to lay down our lives\n" +
                "For our motherland!\n" +
                "We were in a crossfire for two days.\n" +
                "What’s the sense in that?\n" +
                "We awaited the third day.\n" +
                "We started hearing people yell from everywhere:\n" +
                "“It’s time to get the canister!”\n" +
                "And then upon the field of the ferocious battle\n" +
                "Night’s shadow fell.\n" +
                "I lay down to sleep next to the mast,\n" +
                "And we could hear up until dawn\n" +
                "How gaily the French carried on.\n" +
                "But our open camp was quiet:\n" +
                "One cleaned his battered bearskin cap,\n" +
                "Another sharpened their bayonets, muttering angrily\n" +
                "And biting his long whiskers.\n" +
                "And then the heavens finally brightened,\n" +
                "Everyone suddenly began to stir noisily,\n" +
                "Rows and rows of soldiers went by.\n" +
                "Our colonel was born a hero:\n" +
                "He was a servant to the Tsar, a father to the soldiers\n" +
                "But, what a pity: he was slain with a sword.\n" +
                "He sleeps in the damp ground now.\n" +
                "And he said, eyes flashing,\n" +
                "Men! isn’t Moscow behind us?\n" +
                "Let’s meet our deaths near Moscow,\n" +
                "As did our brothers!”\n" +
                "And we resolved to die,\n" +
                "And we kept our oath\n" +
                "Throughout the battle of Borodino.\n" +
                "Well it was quite a day! Through the flying smoke\n" +
                "The Frenchmen moved, like clouds,\n" +
                "All upon our very own redoubt!\n" +
                "Lancers with their shining medals,\n" +
                "Dragoons with horsetails on their hats,\n" +
                "Everyone flashed before us,\n" +
                "Everyone was there.\n" +
                "You’ll never see battles like that!\n" +
                "Banners floated like shadows\n" +
                "And fire flickered amid the smoke;\n" +
                "The swords rang, the buckshot howled,\n" +
                "The arms of the fighters grew tired,\n" +
                "And the mountain of bodies was so wide\n" +
                "That the cannons could not shoot through it.\n" +
                "The enemy learned a fair amount that day\n" +
                "About what real Russian combat means,\n" +
                "Our hand-to-hand combat!\n" +
                "The earth shook, just like our chests;\n" +
                "Horses and people mixed together in a bunch,\n" +
                "And thousands of volleys from the guns\n" +
                "Merged into a drawn-out howl \n" +
                "Then dusk came. Everyone was ready\n" +
                "To start the battle again in the morning\n" +
                "And fight until the bitter end\n" +
                "Then the drums sounded\n" +
                "And the fiends retreated.\n" +
                "Then we counted the wounded,\n" +
                "We counted our comrades.\n" +
                "Yes, there were people in our time\n" +
                "Who were powerful and spirited:\n" +
                "They were heroes, not like you!\n" +
                "Their luck turned rotten:\n" +
                "Very few returned from the field\n" +
                "And if it had not been God's will,\n" +
                "We would not have given up Moscow!";

        arr = source.replaceAll("-|!|,|:|\\.|;|\\?", "").trim().toLowerCase().split(" ");

        int cntFiles = 3;
        String path = "C:\\1";
        getFiles(path, cntFiles, sizeFile, arr, probability);
    }

    /**
     * метод getFullText возвращает сформированный текст
     * текст формируется, до тех пор, пока его размер не превысит
     * значение параметра sizeFile
     *
     * @return result - сформированный текст
     */
    public static String getFullText(Word p) {
//        StringBuilder result = new StringBuilder();
        String result = "";
        while (result.length() <= sizeFile) {
            result += p.genString();
        }
        return result.substring(0, sizeFile);
    }

    /**
     * в методе checkProbability определяется брать слово из массива или формировать случайнам образом
     * если значение 1/probability совпадает с случайным числом, то берем из массива,
     * иначе формируем слово сами
     *
     * @return srcWord - получившееся слово
     */
    public static String checkProbability() {
        double dProbability = Math.rint(100.0 * (1 / (probability * 1.0))) / 100.0;
        double dRandom = Math.rint(100.0 * Math.random()) / 100.0;
        String srcWord;
        Word w = new Word();
        if (dProbability == dRandom) {
            srcWord = arr[w.rndSize(1, arr.length)];
        } else {
            srcWord = "RANDOM";
        }
        return srcWord;
    }

    /**
     * Записываем полученный текс в файлы
     * кол-во букв в слове задается методами setMin и setMax класса Word
     * кол-во слов в предложении задается методами setMin и setMax класса Offer
     * кол-во предложений в абзаце задается методами setMin и setMax класса Paragraph
     *
     * @param path        -- путь куда сохранять файлы
     * @param n           -- количество файлов
     * @param size        -- размер файла
     * @param words       -- массив слов
     * @param probability -- вероятность нахождения нужного слова в массиве
     */
    public static void getFiles(String path, int n, int size, String[] words, int probability) {
        Word w = new Word();
        w.setMin(1);
        w.setMax(15);
        Offer o = new Offer(w);
        o.setMin(1);
        o.setMax(15);
        Paragraph p = new Paragraph(w, o);
        p.setMin(1);
        p.setMax(20);

        File folder = new File(path);
        folder.mkdir();
        for (int i = 1; i <= n; i++) {
            String fileName = path + "\\" + i + ".txt ";
            String fullText = getFullText(p);
            try (FileOutputStream fos = new FileOutputStream(fileName);
                 OutputStreamWriter osw = new OutputStreamWriter(fos);
                 BufferedWriter writer = new BufferedWriter(osw)
            ) {
                writer.write(fullText);

            } catch (FileNotFoundException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        System.out.println("Файлы сформированы");
    }
}
