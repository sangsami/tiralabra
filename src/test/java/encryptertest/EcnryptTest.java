/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/UnitTests/JUnit4TestClass.java to edit this template
 */
package encryptertest;

import decrypter.Decrypt;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;
import java.math.BigInteger;
import encrypter.Encrypt;

/**
 *
 * @author samidinh
 */
public class EcnryptTest {
    
    public EcnryptTest() {
    }
    private Encrypt encrypter = new Encrypt();
    @Test
    public void encrypterWorksWithReadyMadeKey() {
        // Source for keys wikipedia article about RSA
        BigInteger[] publicKey = new BigInteger[2];
        publicKey[0] = new BigInteger("3233");
        publicKey[1] = new BigInteger("17");
        
        assertEquals(2790, 
                    encrypter.encrypt(new BigInteger("65"), publicKey).intValue());
    }
    
}
