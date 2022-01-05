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
import java.nio.file.NoSuchFileException;
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
    /** 1024 bit length for prime numbers used RSA key generation. */
    private static final int BITLENGTH = 1024;
    
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
                    System.out.println("Saving succesfull!");
                } catch (IOException ioe) {
                    ioe.printStackTrace();
                } catch (NullPointerException npe) {
                    System.out.println(
                            "No keys have been generated or loaded!");
                }
            } else if (input.equals("5")) {
                try {
                    loadKey("publicKey");
                    System.out.println("Public key loaded!");
                } catch (IOException ioe) {
                    ioe.printStackTrace();
                } catch (IndexOutOfBoundsException iob) {
                    System.out.println("The file is empty!");
                }
            } else if (input.equals("6")) {
               try {
                    loadKey("privateKey");
                    System.out.println("Private key loaded!");
                }  catch (IOException ioe) {
                    ioe.printStackTrace();
                } catch (IndexOutOfBoundsException iob) {
                    System.out.println("The file is empty!");
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
        String fileName = "message.txt";

        Path path = Paths.get(fileName);
        Files.write(path, encrypted.toByteArray());
    }
    
    /**
     * Load encrypted message from a file.
     * @return byte array message from file to be decrypted.
     */
    private byte[] loadMessage() throws IOException, IndexOutOfBoundsException {
        String fileName = "message.txt";

        Path path = Paths.get(fileName);
        byte[] read = Files.readAllBytes(path);
        return read;
    }
    /**
     * Save key to a file.
     */
    private void saveKeys() throws 
            IOException,
            NullPointerException {
        String publicKeyFile = "publicKey.txt";
        String privateKeyFile = "privateKey.txt";
        
        Path publicKeyPath = Paths.get(publicKeyFile);
        Files.deleteIfExists(publicKeyPath);
        publicKeyPath = Paths.get(publicKeyFile);
        Files.createFile(publicKeyPath);
        
        for (BigInteger key: this.rsaKit.getPublicKey()) {
            Files.writeString(publicKeyPath,
                    key.toString() + System.lineSeparator(),
                    StandardOpenOption.APPEND);
        }
        
        Path privateKeyPath = Paths.get(privateKeyFile);
        Files.deleteIfExists(privateKeyPath);
        privateKeyPath = Paths.get(privateKeyFile);
        Files.createFile(privateKeyPath);
        
        for (BigInteger key: this.rsaKit.getPrivateKey()) {
            Files.writeString(privateKeyPath,
                    key.toString() + System.lineSeparator(),
                    StandardOpenOption.APPEND);
        }
    }
    
    /**
     * Load keys from a file.
     * @param filename Name of the file to be saved.
     */
    private void loadKey(String filename) throws 
            IOException, NoSuchFileException {
        String file = filename + ".txt";

        Path path = Paths.get(file);
        List<String> read = Files.readAllLines(path);
        if (filename.equals("publicKey")) {
            this.rsaKit.setPublicKey(read);
        } else {
            this.rsaKit.setPrivateKey(read);
        }
        
    }
    /**
     * Encrypts and prints encrypted message from given input.
     */
    private void encrypt() {
        String message = io.readInput("Give input: ");
        if (message.isEmpty()) {
            System.out.println("Message was empty, try again");
            return;
        }
        System.out.println("Encrypting...");        
        try {
            encrypted = this.rsaKit.encrypt(message);
            saveMessage();
        } catch (IOException ioe) {
            ioe.printStackTrace();
        } catch (NullPointerException npe) {
            System.out.println(
                    "No keys have been loaded or generated for encryption!");
        }
        System.out.println("Encrypted message saved to file named message.txt");
    }
    /**
     * Decrypts input that was given in previous command.
     */
    private void decrypt() {
        System.out.println("Decrypting...");
        try {
            String message = this.rsaKit.decrypt(loadMessage());
            System.out.println("Decrypted message: " + message);
        } catch (IOException ioe) {
            System.out.println("Something went wrong!");
        } catch (NullPointerException npe) {
            System.out.println(
                    "No keys have been loaded or generated for decryption!");
        }
    }
    /**
     * Generates public and private keys.
     */
    private void generateKeys() {
        System.out.println("Generating keys...");
        this.rsaKit.createKeys(BITLENGTH / 2);
        System.out.println("Keys ready");
    }
    /**
     * Prints all possible commands.
     */
    private void printCommands() {
        System.out.println("************** RSA-algorithm tool **************");
        System.out.println("Choose your command:");
        System.out.println("1: Generate public and private keys");
        System.out.println("2: Encrypt message");
        System.out.println("3: Decrypt message");
        System.out.println("4: Save keys");
        System.out.println("5: Load public key");
        System.out.println("6: Load private key");
        System.out.println("0: Exit");
    }


}

