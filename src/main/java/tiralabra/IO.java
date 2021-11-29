package tiralabra;

import java.util.Scanner;

/**
 *
 * @author samidinh
 */
public class IO {
    private Scanner scanner;
    /**
     * IO tool to read inputs
     * @param scanner to read inputs
     */

    IO(Scanner scanner) {
        this.scanner = scanner;
    }

    public String readInput(String line) {
        System.out.print(line);
        return scanner.nextLine();
    }
    
}
