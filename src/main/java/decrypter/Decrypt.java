package decrypter;

import java.math.BigInteger;

/**
 *
 * @author samidinh
 */
public class Decrypt {
    
    private static BigInteger decrypt(final BigInteger data, final BigInteger[] privateKey) {
        return data.modPow(privateKey[1], privateKey[0]);
        
    }
}
