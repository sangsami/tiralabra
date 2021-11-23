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
    
    public static BigInteger encrypt(int toEncrypt, BigInteger publicKeyN, BigInteger publicKeyE) {
        BigInteger m = new BigInteger(String.valueOf(toEncrypt));
        return m.modPow(publicKeyE, publicKeyN);
        
    }
}
