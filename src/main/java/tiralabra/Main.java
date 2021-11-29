package tiralabra;

import java.util.Scanner;

/**
 *
 * @author samidinh
 */

public class Main {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        IO io = new IO(scanner);
        UI ui = new UI(io);
        ui.run();
    }
    
}
