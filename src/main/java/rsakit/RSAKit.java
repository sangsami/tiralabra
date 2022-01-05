package rsakit;
import decrypter.Decrypt;
import encrypter.Encrypt;
import textpadding.TextPadding;
import java.math.BigInteger;
import java.util.List;
import keygenerator.KeyGenerator;
/**
 *
 * @author samidinh
 */
public class RSAKit {    
    /** KeyGenerator object. */
    private KeyGenerator keyGenerator;
    /** Decrypt object. */
    private Decrypt decrypter;
    /** Encrypt object. */
    private Encrypt encrypter;
    /** TextPadding object. */
    private TextPadding textpadder;

    public RSAKit() {
        this.keyGenerator = new KeyGenerator();
        this.decrypter = new Decrypt();
        this.encrypter = new Encrypt();
        this.textpadder = new TextPadding();

    }
    
    public BigInteger[] getPublicKey() {
        return this.keyGenerator.getPublicKey();
    }
    
    public BigInteger[] getPrivateKey() {
        return this.keyGenerator.getPrivateKey();
    }
    
    private BigInteger padToCipher(String message) {
        return textpadder.textToCipher(
                message);
    }
    
    private String padToText(BigInteger data) {
        return textpadder.cipherToText(
                data);
    }
    
    public BigInteger encrypt(String message) {
        return encrypter.encrypt(
                this.padToCipher(message),
                this.getPublicKey());
    }
    
    public String decrypt(byte[] data) {
        return this.padToText(
                decrypter.decrypt(
                        data,
                        this.getPrivateKey()));
    }
    
    public void createKeys(int bitLength) {
        this.keyGenerator.createKeys(bitLength);
    }
    
    public void setPublicKey(List<String> data) {
        this.keyGenerator.setPublicKey(data);
    }
    
    public void setPrivateKey(List<String> data) {
        this.keyGenerator.setPrivateKey(data);
    }
    
}
