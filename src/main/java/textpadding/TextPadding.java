package textpadding;

import java.math.BigInteger;

/**
 *
 * @author samidinh
 * Text padding package that would have implemented some kind of OAEP padding
 * if there would have been more time.
 */
public class TextPadding {
     /**
     * Turns plain text to cipher.
     * @param m string to be turned to cipher.
     * @return cipher text in BigInteger form.
     */
    public static BigInteger textToCipher(String m) {
        BigInteger cipher = new BigInteger(1, m.getBytes());
        return cipher;
    }
     /**
     * Turns cipher to plain text.
     * @param data cipher to be turned back to plain text.
     * @param privateKey to cipher the data.
     * @return ciphered text.
     */
    public static String cipherToText(BigInteger data) {
        byte[] s = data.toByteArray();
        return new String(s);
    }
}
