import java.util.Scanner;

public class HelpsMethods {

    public static Scanner scanner = new Scanner(System.in);

    public static String generateOutputPath(String inputPath, String suffix) {
        int dotIndex = inputPath.lastIndexOf('.');
        if (dotIndex > 0) {
            return inputPath.substring(0, dotIndex) + suffix + inputPath.substring(dotIndex);
        } else {
            return inputPath + suffix + ".txt";
        }
    }

    public static void displayFileResults(String inputPath, String outputPath, int key) {
        java.io.File inputFile = new java.io.File(inputPath);
        java.io.File outputFile = new java.io.File(outputPath);

        System.out.println("\nФАЙЛ УСПЕШНО ЗАШИФРОВАН!");
        System.out.println("Исходный файл: " + inputFile.getName());
        System.out.println("Размер: " + inputFile.length() + " байт");
        System.out.println("Результат: " + outputFile.getName());
        System.out.println("Ключ: " + key);
    }

    public static void printHead() {
        System.out.println("=".repeat(60));
        System.out.println("         ПРОГРАММА, РАБОТАЮЩАЯ С ШИФРОМ ЦЕЗАРЯ");
        System.out.println("=".repeat(60));
    }

    public static void printMenu(){
        System.out.println("\nОСНОВНОЕ МЕНЮ:");
        System.out.println("   1. Зашифровать текст");
        System.out.println("   2. Расшифровать текст (с ключом)");
        System.out.println("   3. Расшифровать текст (Brute Force)");
        System.out.println("\nРАБОТА С ФАЙЛАМИ:");
        System.out.println("   4. Зашифровать файл");
        System.out.println("   5. Расшифровать файл (с ключом)");
        System.out.println("   6. Расшифровать файл (Brute Force)");
        System.out.println("\nВЫХОД:");
        System.out.println("   7. Выход");
        System.out.println("-".repeat(60));
    }

    public static void runConsoleMenu(){
        while (true){
            printHead();
            printMenu();

            System.out.println("Выберите опцию: ");
            int choice = scanner.nextInt();

            switch (choice) {
                case 1 -> TextWork.encryptText();
                case 2 -> TextWork.decryptText();
                case 3 -> TextWork.enumerationText();
                case 4 -> FileWork.encryptFile();
                case 5 -> FileWork.decryptFile();
                case 6 -> FileWork.enumerationFile();
                case 7 -> {
                    System.out.println("\nДо свидани и удачи вам!");
                    System.exit(0);
                }
                default -> System.out.println("\nНеверный выбор! Попробуйте снова");
            }
        }
    }

}
