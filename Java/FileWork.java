import javax.swing.text.StringContent;
import java.io.File;
import java.nio.charset.CharsetEncoder;
import java.util.Scanner;

public class FileWork {

    public static Scanner scanner = new Scanner(System.in);
    public static Cipher cipher = new Cipher();
    public static Hander hander = new Hander();
    public static HelpsMethods helpsMethods = new HelpsMethods();



    public static void encryptFile() {
        System.out.println("\n---ШИФРОВАНИЕ ФАЙЛА---");
        System.out.print("Введите путь к исходному файлу: ");
        String path = scanner.nextLine();
        System.out.print("\nВведите целый ключ(1-25): ");
        try {
            int key = Integer.parseInt(scanner.nextLine());

            if (key >= 1 && key <= 25) {
                try {
                    String content = hander.read(path);
                    String encrypted = cipher.encrypt(content, key);

                    String outputPath = helpsMethods.generateOutputPath(path, "_encrypted");
                    hander.write(outputPath, encrypted);

                    helpsMethods.displayFileResults(path, outputPath, key);

                } catch (Exception e) {
                    System.err.println("\nОшибка при шифровании файла: " + e.getMessage());
                }
            } else {
                System.err.println("Ключ может быть только в указанном диапозоне!");
            }
        }
        catch (NumberFormatException e) {
            System.err.println("Ключ должен быть целым числом!");
        }
    }

    public static void decryptFile() {
        System.out.println("\n---РАСШИФРОВКА ФАЙЛА---");
        System.out.print("Введите путь к файлу: ");
        String path = scanner.nextLine();
        System.out.println("\nВведите ключ: ");
        try {
            int key = Integer.parseInt(scanner.nextLine());
            if (key <= 1 && key >= 25) {
                try {
                    String content = hander.read(path);
                    String decrypter = cipher.decrypt(content, key);

                    String outputPath = helpsMethods.generateOutputPath(path, "_encrypted");
                    hander.write(outputPath, decrypter);

                    helpsMethods.displayFileResults(path, outputPath, key);
                } catch (Exception e) {
                    System.err.println("\nОшибка при расшифровке файла" + e.getMessage());
                }
            } else {
                System.err.println("Ключ может быть только в указанном диапозоне!");
            }
        }
        catch (NumberFormatException e) {
                System.err.println("Ключ должен быть целым числом!");
            }
    }

    public static void enumerationFile(){
        System.out.println("\n---РАСШИФРОВКА ФАЙЛА ПУТЕМ ПЕРЕБОРА---");
        System.out.print("Введите путь к файлу: ");
        String path = scanner.nextLine();
        try {
            String contend = hander.read(path);
            System.out.println("-".repeat(60));

            int bkey = 0;
            int mwords = 0;

            for (int key = 1; key <= 25; key++) {
                String decrypted = cipher.decrypt(contend, key);
                String preview = decrypted.length() > 50 ? decrypted.substring(0, 47) + "..." : decrypted;
                System.out.printf("%4d | %s%n", key, preview);

                String[] words = {"the", "and", "to", "of", "a", "in", "for", "is", "on", "that",
                        "this", "with", "you", "are", "have", "from", "they", "know"};
                String lowerText = decrypted.toLowerCase();
                int count = 0;
                for(String word : words) {
                    if (lowerText.contains(word)) {
                        count++;
                    }
                }
                int wordCount = count;
                if (wordCount > mwords) {
                    mwords = wordCount;
                    bkey = key;
                }
            }
            System.out.println("-".repeat(60));

            System.out.println("\nНайденный ключ: " + bkey);

            System.out.println("\nЖелаете ли расшефровать файл с ключем " + bkey + "?(YES/NO)");
            String a = scanner.nextLine();
            if (a.equalsIgnoreCase("yes")){
                String decrypted = cipher.decrypt(contend, bkey);
                String outputPath = HelpsMethods.generateOutputPath(path, "_bruteforce");
                hander.write(outputPath, decrypted);
                System.out.println("Файл расшифрован и сохранен в: " + outputPath);
            }
        }
        catch (Exception e) {
            System.err.println("\nОшибка при анализе файла: " + e.getMessage());
        }
    }

}
