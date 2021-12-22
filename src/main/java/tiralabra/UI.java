package tiralabra;

import java.math.BigInteger;
import keygenerator.KeyGenerator;
import decrypter.Decrypt;
import rsakit.RSAKit;
import textpadding.TextPadding;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.URI;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.List;
import java.util.Scanner;

/**
 *
 * @author samidinh
 */
public class UI {
    /** io. */
    private IO io;
    /** Tool to call other components. */
    private RSAKit rsaKit;
    /** Public key array. */
    private BigInteger[] publicKey = new BigInteger[2];
    /** Private key array. */
    private BigInteger[] privateKey = new BigInteger[2];
    /** Encrypted message. */
    private BigInteger encrypted;
    
    /**
     * UI tool to show and call commands.
     * @param io initializes scanner.
     */
    UI(IO io) {
        this.io = io;
        this.rsaKit = new RSAKit();
        
    }
    /**
     * Shows all commands and calls them according to input.
     */
    public final void run() {
        while (true) {
            printCommands();
            String input = this.io.readInput("Command: ");

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
                try {
                    saveKeys();
                } catch (IOException ioe) {
                    ioe.printStackTrace();
                }
            } else if (input.equals("5")) {
               try {
                    loadKeys();
                } catch (IOException ioe) {
                    ioe.printStackTrace();
                }
            } else {
                System.out.println("Invalid command");
            }
        }
    }
    /**
     * Save encrypted message to a file.
     */
    private void saveMessage() throws IOException {
        String fileName = "encrypted.txt";

        Path path = Paths.get(fileName);
        Files.write(path, encrypted.toByteArray());
        
    }
    
    /**
     * Load encrypted message from a file.
     * @return byte array message from file to be decrypted.
     */
    private byte[] loadMessage() throws IOException {
        String fileName = "encrypted.txt";

        Path path = Paths.get(fileName);
        byte[] read = Files.readAllBytes(path);
        return read;
    }
    /**
     * Save keys to a file.
     */
    private void saveKeys() throws IOException {
        String fileName = "keys.txt";
        
        Path path = Paths.get(fileName);
        Files.deleteIfExists(path);
        path = Paths.get(fileName);
        Files.createFile(path);
        for (BigInteger key: this.rsaKit.getPublicKey()) {
            Files.writeString(path,
                    key.toString() + System.lineSeparator(),
                    StandardOpenOption.APPEND);
        }
        Files.writeString(path, this.rsaKit.getPrivateKey()[1]
                .toString() + System.lineSeparator(),
                StandardOpenOption.APPEND);
    }
    
    /**
     * Load keys from a file.
     */
    private void loadKeys() throws IOException {
        String fileName = "keys.txt";

        Path path = Paths.get(fileName);
        List<String> read = Files.readAllLines(path);
        this.rsaKit.setKeys(read);
        
    }
    /**
     * Encrypts and prints encrypted message from given input.
     */
    private void encrypt() {
        String message = io.readInput("Give input: ");
        if (message.isEmpty()) {
            return;
        }
        System.out.println("Encrypting...");
        encrypted = this.rsaKit.encrypt(message);
        try {
            saveMessage();
        } catch (IOException ioe) {
            ioe.printStackTrace();
        }
        
        System.out.println("Encrypted message to file encrypted.txt");
    }
    /**
     * Decrypts input that was given in previous command.
     */
    private void decrypt() {
        System.out.println("Decrypting...");
        try {
            String message = this.rsaKit.decrypt(loadMessage());
            System.out.println("Decrypted message: " + message);
        } catch (Exception e) {
            System.out.println(
                    "Something went wrong!"
            );
        }
    }
    /**
     * Generates public and private keys.
     */
    private void generateKeys() {
        System.out.println("Generating keys...");
        this.rsaKit.createKeys(1024);
        System.out.println("Keys ready");
    }
    /**
     * Prints all possible commands.
     */
    private void printCommands() {
        System.out.println("************** RSA-algorithm tool **************");
        System.out.println("Choose your command:");
        System.out.println("1: Generate public and private keys");
        System.out.println("2: Encrypt message (must have generated keys)");
        System.out.println("3: Decrypt message (must have generated keys)");
        System.out.println("4: Save keys to a file (must have generated keys)");
        System.out.println(
                "5: Load keys from a file (must have generated keys)");
        System.out.println("0: Exit");
    }


}

