package keygeneratortest;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;
import java.math.BigInteger;
import java.util.ArrayList;
import javax.crypto.Cipher;
import keygenerator.KeyGenerator;
import java.util.List;


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
        keyGenerator.createKeys(1024);
        privateKey = keyGenerator.getPrivateKey();
        publicKey = keyGenerator.getPublicKey();
    }
    
    @Test
    public void keyGeneratorCreatesKeysProbablePrimes() {
        BigInteger primeCandidate = keyGenerator.generatePrime(1024, true);
        assertTrue(primeCandidate.isProbablePrime(10));
    }
    
    @Test
    public void keyGeneratorCreatesKeysThatAreKeysToEachOther() {
        BigInteger test = new BigInteger("541");
        BigInteger eModN = test.modPow(publicKey[1], publicKey[0]);
        BigInteger dModN = eModN.modPow(privateKey[1], privateKey[0]);
        assertEquals(test, dModN);
    }
    
    @Test
    public void keyGeneratorSetsPublicKeyCorrectly() {
        List<String> data = new ArrayList<String>();
        data.add("3233\n\r");
        data.add("17\n\r");
        keyGenerator.setPublicKey(data);
        BigInteger[] newPublicKey = keyGenerator.getPublicKey();
        assertEquals("3233", newPublicKey[0].toString());
        assertEquals("17", newPublicKey[1].toString());
        
    }
    
    @Test
    public void keyGeneratorSetsPrivateKeyCorrectly() {
        List<String> data = new ArrayList<String>();
        data.add("3233\n\r");
        data.add("413\n\r");
        keyGenerator.setPrivateKey(data);
        BigInteger[] newPrivateKey = keyGenerator.getPrivateKey();
        assertEquals("3233", newPrivateKey[0].toString());
        assertEquals("413", newPrivateKey[1].toString());
        
    }
}
