/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package rsakit;
import decrypter.Decrypt;
import java.math.BigInteger;
import keygenerator.KeyGenerator;
/**
 *
 * @author samidinh
 */
public class RSAKit {    

    private KeyGenerator keyGenerator;
    private Decrypt decrypter;

    public RSAKit() {
        this.keyGenerator = new KeyGenerator();
        this.decrypter = new Decrypt();
    }
    
    public BigInteger[] getPublicKey() {
        return this.keyGenerator.getPublicKey();
    }
    
    public BigInteger[] getPrivateKey() {
        return this.keyGenerator.getPrivateKey();
    }
    
    public void createKeys() {
        this.keyGenerator.createKeys();
    }
}
