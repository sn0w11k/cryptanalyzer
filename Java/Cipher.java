public class Cipher {

    public String shift(String text, int shift) {
        if (text == null || text.isEmpty()) return "";

        shift = shift % 26;
        StringBuilder result = new StringBuilder();

        for (char c : text.toCharArray()) {
            if (c >= 'A' && c <= 'Z') {
                result.append((char) ((c - 'A' + shift) % 26 + 'A'));
            }
            else if ((c >= 'a') && (c <= 'z')) {
                result.append((char) ((c - 'a' + shift) % 26 + 'a'));
            }
            else {
                result.append(c);
            }
        }
        return result.toString();
    }

    public String encrypt(String text, int key) {
        return shift(text, key);
    }

    public String decrypt(String text, int key) {
        return shift(text, 26 - (key % 26));
    }


}
