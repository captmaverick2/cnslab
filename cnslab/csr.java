class CaesarCipher {
    // Method to encrypt text using Caesar Cipher
    public static String encrypt(String text, int key) {
        return processText(text, key);
    }

    // Method to decrypt text using Caesar Cipher
    public static String decrypt(String text, int key) {
        return processText(text, -key);
    }

    // Method to process text for both encryption and decryption
    private static String processText(String text, int key) {
        StringBuilder result = new StringBuilder();
        for (char ch : text.toCharArray()) {
            if (Character.isLetter(ch)) {
                char base = Character.isUpperCase(ch) ? 'A' : 'a';
                char shifted = (char) (base + (ch - base + key + 26) % 26);
                result.append(shifted);
            } else {
                result.append(ch);
            }
        }
        return result.toString();
    }
}

public class csr {
    public static void main(String[] args) {
        String text = "ABCDZ";
        int key = 3;

        // Encrypt the text
        String encryptedText = CaesarCipher.encrypt(text, key);
        System.out.println("Encrypted text: " + encryptedText);

        // Decrypt the text
        String decryptedText = CaesarCipher.decrypt(encryptedText, key);
        System.out.println("Decrypted text: " + decryptedText);
    }
}
