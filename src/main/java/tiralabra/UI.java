package tiralabra;

import java.math.BigInteger;
import keyGenerator.KeyGenerator;
import encrypter.Encrypt;
import decrypter.Decrypt;
import paddingTool.padder;

/**
 *
 * @author samidinh
 */
public class UI {

    private final IO io;
    private BigInteger[] publicKey = new BigInteger[2];
    private BigInteger[] privateKey = new BigInteger[2];
    private BigInteger encrypted;
    
    public UI(IO io) {
        this.io = io;
    }

    public final void run() {
        System.out.println("************** RSA-algorithm tool **************");
        while (true) {
            printCommands();
            String input = io.readInput("Command: ");

            if (input.equals("0")) {
                System.out.println("Thank you, bye!");
                break;
            } else if (input.equals("1")) {
                generateKeys();
            } else if (input.equals("2")) {
                encrypt();
            } else if (input.equals("3")) {
                decrypt();
            } else if (input.equals("4")) {
                System.out.println("Not yet implemented, try somehing else");
            } else if (input.equals("5")) {
                System.out.println("Not yet implemented, try somehing else");
            } else {
                System.out.println("Invalid command");
            }
        }
    }


    private void load() {
        
    }

    private void save() {
        
    }

    private void encrypt() {
        String message = io.readPlainText();
        if (message.isEmpty()) {
            return;
        }
        System.out.println("Encrypting...");
        encrypted = encrypter.Encrypt.encrypt(
                paddingTool.padder.textToCipher(message, publicKey), 
                publicKey);
        System.out.println("Encrypted message: " + encrypted.toString());
    }

    private void decrypt() {
        System.out.println("Decrypting...");
        try {
            BigInteger data = decrypter.Decrypt.decrypt(encrypted, privateKey);
            String message = paddingTool.padder.cipherToText(data, privateKey);
            System.out.println("Decrypted message: " + message);
        } catch (Exception e) {
            System.out.println(
                    "Something went wrong!"
            );
        }
    }

    private void generateKeys() {
        System.out.println("Generating keys...");
        BigInteger[] generator = KeyGenerator.createKeys();
        
        publicKey[0] = generator[0];
        publicKey[1] = generator[1];
        privateKey[0] = generator[0];
        privateKey[1] = generator[2];
        System.out.println("Keys ready");
    }

    private void printCommands() {
        System.out.println("Choose your options:");
        System.out.println("1: Generate public and private keys");
        System.out.println("2: Encrypt message (must have generated keys)");
        System.out.println("3: Decrypt message (must have generated keys)");
        System.out.println("4: Save keys to a file (must have generated keys)");
        System.out.println(
                "5: Load keys from a file (must have generated keys)");
        System.out.println("0: Exit");
    }


}

