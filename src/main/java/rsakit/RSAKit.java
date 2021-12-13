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

    public RSAKit() {
        this.keyGenerator = new KeyGenerator();

    }
    
    public BigInteger[] getPublicKey() {
        return this.keyGenerator.getPublicKey();
    }
    
    public BigInteger[] getPrivateKey() {
        return this.keyGenerator.getPrivateKey();
    }
    
    private BigInteger padToCipher(String message) {
        return textpadding.TextPadding.textToCipher(
                message, this.getPublicKey());
    }
    
    private String padToText(BigInteger data) {
        return textpadding.TextPadding.cipherToText(
                data, this.getPrivateKey());
    }
    
    public BigInteger encrypt(String message) {
        return encrypter.Encrypt.encrypt(
                this.padToCipher(message), this.getPublicKey());
    }
    
    public String decrypt(byte[] data) {
        return this.padToText(
                decrypter.Decrypt.decrypt(data, this.getPrivateKey()));
    }
    
    public void createKeys() {
        this.keyGenerator.createKeys();
    }
    
    public void setKeys(List<String> data) {
        this.keyGenerator.setKeys(data);
    }
}
