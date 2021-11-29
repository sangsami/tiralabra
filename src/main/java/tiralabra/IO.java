package tiralabra;

import java.util.Scanner;

/**
 *
 * @author samidinh
 */
public class IO {
    private Scanner scanner;

    public IO(Scanner scanner) {
        this.scanner = scanner;
    }

    public String readInput(String line) {
        System.out.print(line);
        return scanner.nextLine();
    }

    public String readPlainText() {
        String plaintext = readInput("Enter input: ");
        if (plaintext.length() > 256) {
            System.out.println("Input must be 256 characters or less.");
            return "";
        } else if (plaintext.length() == 0) {
            System.out.println("Input can not be empty");
            return "";
        }
        return plaintext;
    }
}
