import java.util.*;

public class PlayfairCipher {
  private static char[][] generateMatrix(String key) {
    StringBuilder keyBuilder = new StringBuilder(key.replaceAll("[^a-zA-Z]", "").toUpperCase().replace("J", "I"));
    Set<Character> uniqueChars = new LinkedHashSet<>();
    for (char c : keyBuilder.toString().toCharArray()) {
      uniqueChars.add(c);
    }
    for (char c = 'A'; c <= 'Z'; c++) {
      if (c != 'J' && !uniqueChars.contains(c)) {
        uniqueChars.add(c);
      }
    }

    char[][] matrix = new char[5][5];
    Iterator<Character> iterator = uniqueChars.iterator();
    for (int i = 0; i < 5; i++) {
      for (int j = 0; j < 5; j++) {
        matrix[i][j] = iterator.next();
      }
    }
    return matrix;
  }

  private static String preparePlainText(String plainText) {
    return plainText.replaceAll("[^a-zA-Z]", "").toUpperCase().replace("J", "I");
  }
  
  private static int[] findPosition(char[][] matrix, char ch) {
    int[] position = new int[2];
    for (int i = 0; i < 5; i++) {
      for (int j = 0; j < 5; j++) {
        if (matrix[i][j] == ch) {
          position[0] = i;
          position[1] = j;
          return position;
        }
      }
    }
    return position;
  }

  public static String encrypt(String plainText, String key) {
    char[][] matrix = generateMatrix(key);
    plainText = preparePlainText(plainText);

    StringBuilder encryptedText = new StringBuilder();
    for (int i = 0; i < plainText.length(); i += 2) {
      char first = plainText.charAt(i);
      char second = (i + 1 < plainText.length()) ? plainText.charAt(i + 1) : 'X';

      int[] posFirst = findPosition(matrix, first);
      int[] posSecond = findPosition(matrix, second);

      if (posFirst[0] == posSecond[0]) { // Same row
        encryptedText.append(matrix[posFirst[0]][(posFirst[1] + 1) % 5]);
        encryptedText.append(matrix[posSecond[0]][(posSecond[1] + 1) % 5]);
      } else if (posFirst[1] == posSecond[1]) { // Same column
        encryptedText.append(matrix[(posFirst[0] + 1) % 5][posFirst[1]]);
        encryptedText.append(matrix[(posSecond[0] + 1) % 5][posSecond[1]]);
      } else { // Forming a rectangle
        encryptedText.append(matrix[posFirst[0]][posSecond[1]]);
        encryptedText.append(matrix[posSecond[0]][posFirst[1]]);
      }
    }

    return encryptedText.toString();
  }

  public static String decrypt(String encryptedText, String key) {
    char[][] matrix = generateMatrix(key);
    StringBuilder decryptedText = new StringBuilder();
    for (int i = 0; i < encryptedText.length(); i += 2) {
      char first = encryptedText.charAt(i);
      char second = (i + 1 < encryptedText.length()) ? encryptedText.charAt(i + 1) : 'X';
      if (first == ' ') {
        decryptedText.append(' ');
        continue;
      }
      if (second == ' ') {
        decryptedText.append(' ');
        continue;
      }
      int[] posFirst = findPosition(matrix, first);
      int[] posSecond = findPosition(matrix, second);
      if (posFirst[0] == posSecond[0]) { // Same row
        decryptedText.append(matrix[posFirst[0]][(posFirst[1] + 4) % 5]);
        decryptedText.append(matrix[posSecond[0]][(posSecond[1] + 4) % 5]);
      } else if (posFirst[1] == posSecond[1]) { // Same column
        decryptedText.append(matrix[(posFirst[0] + 4) % 5][posFirst[1]]);
        decryptedText.append(matrix[(posSecond[0] + 4) % 5][posSecond[1]]);
      } else { // Forming a rectangle
        decryptedText.append(matrix[posFirst[0]][posSecond[1]]);
        decryptedText.append(matrix[posSecond[0]][posFirst[1]]);
      }
    }
    return decryptedText.toString();
  }

  public static void main(String[] args) {
    String key = "MONARCHY";
    String plainText = "KINGDOM";

    System.out.println("Plain text: " + plainText);

    String encryptedText = encrypt(plainText, key);
    System.out.println("Encrypted text: " + encryptedText);

    String decryptedText = decrypt(encryptedText, key);
    System.out.println("Decrypted text: " + decryptedText);
  }
}
