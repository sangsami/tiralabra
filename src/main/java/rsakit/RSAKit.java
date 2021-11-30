package rsakit;
import decrypter.Decrypt;
import java.math.BigInteger;
import keygenerator.KeyGenerator;
/**
 *
 * @author samidinh
 */
public class RSAKit {    
    /** KeyGenerator object */
    private KeyGenerator keyGenerator;
    /** Decrypt object */
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
