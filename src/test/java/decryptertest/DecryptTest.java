/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/UnitTests/JUnit4TestClass.java to edit this template
 */
package decryptertest;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;
import decrypter.Decrypt;
import java.math.BigInteger;

/**
 *
 * @author samidinh
 */
public class DecryptTest {
    
    public DecryptTest() {
    }
    private Decrypt decrypter = new Decrypt();
    @Test
    public void encryptedDataIsDecryptedCorrectly() {
        // Source for keys wikipedia article about RSA
        BigInteger[] privateKey = new BigInteger[2];
        privateKey[0] = new BigInteger("3233");
        privateKey[1] = new BigInteger("413");
        
        assertEquals(65, 
                    decrypter.decrypt(new BigInteger("2790").toByteArray(), privateKey).intValue());
    }
}
 