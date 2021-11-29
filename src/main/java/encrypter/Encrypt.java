package encrypter;

/**
 *
 * @author samidinh
 */
import java.math.BigInteger;

/**
 *
 * @author samidinh
 */
public class Encrypt {
    
    public static BigInteger encrypt(final BigInteger data, final BigInteger[] publicKey) {
        return data.modPow(publicKey[1], publicKey[0]);
        
    }
}
