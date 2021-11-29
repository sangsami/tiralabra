package decrypter;

import java.math.BigInteger;

/**
 *
 * @author samidinh
 */
public class Decrypt {
    
    public static BigInteger decrypt(BigInteger data, BigInteger[] privateKey) {
        return data.modPow(privateKey[1], privateKey[0]);
        
    }
}
