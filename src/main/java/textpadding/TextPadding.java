package textpadding;

import java.math.BigInteger;

/**
 *
 * @author samidinh
 */
public class TextPadding {

     /**
     * Turns plain text to cipher.
     * @param m string to be turned to cipher.
     * @param publicKey keys to make plain text to cipher.
     * @return cipher text in BigInteger form.
     */
    public static BigInteger textToCipher(String m, BigInteger[] publicKey) {
        BigInteger cipher = new BigInteger(1, m.getBytes());
        return cipher.modPow(publicKey[1], publicKey[0]);
    }
     /**
     * Turns cipher to plain text.
     * @param data cipher to be turned back to plain text.
     * @param privateKey to cipher the data.
     * @return ciphered text.
     */
    public static String cipherToText(BigInteger data, 
                                        BigInteger[] privateKey) {
        BigInteger message = data.modPow(privateKey[1], privateKey[0]);
        byte[] s = message.toByteArray();
        return new String(s);
    }
}
