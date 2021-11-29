/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
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
