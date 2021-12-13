package tiralabra;

import java.util.Scanner;

/**
 *
 * @author samidinh
 */
public class IO {
    /**
     * Reads inputs.
     */
    private Scanner scanner;
    /**
     * IO tool to read inputs.
     * @param scanner to read inputs.
     */

    IO(Scanner scanner) {
        this.scanner = scanner;
    }
    
    /**
     * Prints given parameter and reads given input.
     * @param line to prompt user.
     * @return returns user input.
     */
    public String readInput(String line) {
        System.out.print(line);
        return scanner.nextLine();
    }
    
}
