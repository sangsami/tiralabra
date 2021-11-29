/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
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
