package decrypter;

import java.math.BigInteger;

/**
 *
 * @author samidinh
 */
public class Decrypt {
    /**
     * Decrypts an encrypted BigInteger with a privateKey.
     * @param data BigInteger to be decrypted.
     * @param privateKey privateKey to decrypt with.
     * @return decrypted BigInteger.
     */
    public static BigInteger decrypt(BigInteger data, BigInteger[] privateKey) {
        return data.modPow(privateKey[1], privateKey[0]);
        
    }
}
