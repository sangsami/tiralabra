package keygeneratortest;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;
import java.math.BigInteger;
import javax.crypto.Cipher;
import keygenerator.KeyGenerator;


/**
 *
 * @author samidinh
 */
public class KeyGeneratorTest {
    
    public KeyGeneratorTest() {
    }
    private KeyGenerator keyGenerator;
    private BigInteger[] publicKey;
    private BigInteger[] privateKey;
    
    @Before
    public void setUp() {
        keyGenerator = new KeyGenerator();
        keyGenerator.createKeys();
        privateKey = keyGenerator.getPrivateKey();
        publicKey = keyGenerator.getPublicKey();
        
    }

    @Test
    public void keyGeneratorIsCreated() {
        assertNotNull(keyGenerator);
    }

    @Test
    public void keyGeneratorProducesPublicAndPrivateKeys() {
        assertNotNull(privateKey);
        assertNotNull(publicKey);
    }
    
    @Test
    public void keyGeneratorCreatesKeysThatAreKeysToEachOther() {
        BigInteger test = new BigInteger("541");
        BigInteger eModN = test.modPow(publicKey[1], publicKey[0]);
        BigInteger dModN = eModN.modPow(privateKey[1], privateKey[0]);
        assertEquals(test, dModN);
    }
}
