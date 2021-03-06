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
    /**
     * Encrypts a padded BigInteger with a publicKey.
     * @param data BigInteger to be encrypted.
     * @param publicKey publicKey to encrypt with.
     * @return BigInteger.
     */
    public static BigInteger encrypt(BigInteger data, BigInteger[] publicKey) {
        return data.modPow(publicKey[1], publicKey[0]);
        
    }
}
