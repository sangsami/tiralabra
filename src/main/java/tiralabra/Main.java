/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package tiralabra;

/**
 *
 * @author samidinh
 */
import java.util.Scanner;
import keyGenerator.KeyGenerator;
import encrypter.Encrypt;
import decrypter.Decrypt;
import java.math.BigInteger;

public class Main {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        BigInteger[] generator = KeyGenerator.createKeys();
        while(true){
            System.out.println("************** RSA-algorithm tool **************");
            System.out.println("Choose your options:");
            System.out.println("1: Create public and private keys");
            System.out.println("2: Encrypt message (must have public key made)");
            System.out.println("3: Decrypt message (must have private key made)");
            System.out.println("0: Exit");
            String input = scanner.nextLine();
            

            if(input.equals("0")){
                break;
            } else if(input.equals("1")){
                generator = KeyGenerator.createKeys();
                for(BigInteger key: generator){
                    System.out.println(key);
                }
            } else if(input.equals("2")){
                BigInteger encrypted = Encrypt.encrypt(12345, generator[0], generator[1]);
                System.out.println(encrypted);
                BigInteger decrypted = Decrypt.decrypt(encrypted, generator[3], generator[0]);
                System.out.println(decrypted);
            }
        }
    }
    
}
