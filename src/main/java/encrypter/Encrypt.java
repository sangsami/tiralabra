/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package encrypter;

/**
 *
 * @author samidinh
 */
import java.math.BigInteger;

/**
 *
 * @author samidinh
 */
public class Encrypt {
    
    public static BigInteger encrypt(BigInteger data, BigInteger[] publicKey) {
        return data.modPow(publicKey[1], publicKey[0]);
        
    }
}
