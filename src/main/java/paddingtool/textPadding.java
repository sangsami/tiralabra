package paddingtool;

import java.math.BigInteger;

/**
 *
 * @author samidinh
 */
public class textPadding {
    
    public static BigInteger textToCipher(String m, BigInteger[] publicKey) {
        BigInteger cipher = new BigInteger(1, m.getBytes());
        return cipher.modPow(publicKey[1], publicKey[0]);
    }
    
    public static String cipherToText(BigInteger data, 
                                        BigInteger[] privateKey) {
        BigInteger message = data.modPow(privateKey[1], privateKey[0]);
        byte[] s = message.toByteArray();
        return new String(s);
    }
}
