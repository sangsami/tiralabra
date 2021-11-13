/**
 *
 * @author samidinh
 */
import java.util.Scanner;

public class Main {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
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
            }
        }
    }
    
}
