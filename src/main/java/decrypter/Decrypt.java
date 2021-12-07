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
    public static BigInteger decrypt(byte[] data, BigInteger[] privateKey) {
        BigInteger message = new BigInteger(data);
        return message.modPow(privateKey[1], privateKey[0]);
        
    }
}
