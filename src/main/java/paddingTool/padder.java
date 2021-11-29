/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package paddingTool;

import java.math.BigInteger;

/**
 *
 * @author samidinh
 */
public class padder {
    
    public static BigInteger textToCipher(String message, BigInteger[] publicKey){
        BigInteger cipher = new BigInteger(1, message.getBytes());
        return cipher.modPow(publicKey[1], publicKey[0]);
    }
    
    public static String cipherToText(BigInteger data, BigInteger[] privateKey){
        BigInteger message = data.modPow(privateKey[1], privateKey[0]);
        byte[] s = message.toByteArray();
        return new String(s);
    }
    
}
