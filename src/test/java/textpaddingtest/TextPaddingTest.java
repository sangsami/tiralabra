/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/UnitTests/JUnit4TestClass.java to edit this template
 */
package textpaddingtest;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;
import textpadding.TextPadding;
import java.math.BigInteger;

/**
 *
 * @author samidinh
 */
public class TextPaddingTest {
    
    public TextPaddingTest() {
    }
    private BigInteger[] publicKey = new BigInteger[2];
    private BigInteger[] privateKey = new BigInteger[2];
    private String testString;
    private BigInteger testCipher;
    
    @Before
    public void setUp() {
        publicKey[0] = new BigInteger("3233");
        publicKey[1] = new BigInteger("17");
        privateKey[0] = new BigInteger("3233");
        privateKey[1] = new BigInteger("413");
        testString = "Test string that is to be converted to cipher";
        testCipher = new BigInteger(1, testString.getBytes());
    }
    private TextPadding textPadding = new TextPadding();
    
    @Test
    public void textConvertsToCipherCorrectly() {
        assertEquals(testCipher, 
                    textPadding.textToCipher(testString));
    }
    
    @Test
    public void cipherConvertsToTextCorrectly() {
        assertEquals(testString, 
                    textPadding.cipherToText(testCipher));
    }
    
}
