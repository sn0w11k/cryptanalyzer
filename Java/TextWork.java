import java.util.Scanner;


public class TextWork {

    private static final Scanner scanner = new Scanner(System.in);
    private static final Cipher cipher = new Cipher();
    private static final Hander fileHandler = new Hander();

    public static void encryptText(){
        System.out.println("\n--- ШИФРОВАНИЕ ТЕКСТА ---");
        System.out.print("Введите текст для шифрования(на английском): ");
        String text = scanner.nextLine();
        System.out.println("\nВведите целый ключ сдвига(1-25): ");
        try {
            int key = Integer.parseInt(scanner.nextLine());

            if (key >= 1 && key <= 25) {
                String encrypted = cipher.encrypt(text, key);

                System.out.println("\nРЕЗУЛЬТАТ: ");
                System.out.print(encrypted);
                System.out.println("\nИспользованный ключ: " + key);
            } else {
                System.err.println("Ключ может быть только в указанном диапозоне!");
            }
        }
        catch (NumberFormatException e) {
            System.err.println("Ключ должен быть целым числом!");
        }
    }

    public static void decryptText() {
        System.out.println("\n---РАСШИФРОВКА ТЕКТСА С КЛЮЧОМ---");
        System.out.print("Введите зашифрованный текст(на английском): ");
        String text = scanner.nextLine();
        System.out.println("\nВведите ключ: ");
        try {
            int key = Integer.parseInt(scanner.nextLine());


            if (key >= 1 && key <= 25) {

                String decrypted = cipher.decrypt(text, key);

                System.out.print("\nРЕЗУЛЬТАТ: ");
                System.out.println(decrypted);
                System.out.println("Использованный ключ: " + key);
            } else {
                System.err.println("Ключ может быть только в указанном диапозоне!");
            }
        }
        catch (NumberFormatException e) {
            System.err.println("Ключ должен быть целым числом!");
        }
    }

    public static void enumerationText() {
        System.out.println("\n---РАСШИФРОВКА ТЕКСТА ПУТЕМ ПЕРЕБОРА---");
        System.out.print("Введите зашифрованный текст(на английском): ");
        String text = scanner.nextLine();
        System.out.println("\nРЕЗУЛЬТАТ ПЕРЕБОРА КЛЮЧЕЙ:");
        for (int key = 1; key <= 25; key++) {
            String decrypted = cipher.decrypt(text, key);
            String displayText = decrypted.length() > 50 ? decrypted.substring(0, 47) + "..." : decrypted;
            System.out.printf("%4d | %s%n", key, displayText);
        }
        System.out.println("-".repeat(60));


    }
}
