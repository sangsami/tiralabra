/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package keyGenerator;

/**
 *
 * @author samidinh
 */
import java.util.Random;
import java.math.BigInteger;
/**
 *
 * @author samidinh
 */
public class KeyGenerator {
    
    public static BigInteger generatePrime(){
        BigInteger candidate = new BigInteger(1024, new Random());
        candidate = candidate.setBit(0);
        while(!isPrime(candidate, 128)){

            candidate = new BigInteger(1024, new Random());
            candidate = candidate.setBit(0);
        }
        
        return candidate;
    }
    
    public static Boolean isPrime(BigInteger candidate, int n){
        if(!candidate.testBit(0)){
            return false;
        }
        BigInteger d = candidate.subtract(new BigInteger("1"));
        
        while(!d.testBit(0)){
            d = d.divide(new BigInteger("2"));
        }
        
        for (int i = 0; i < n; i++) {
            return millerRabin(candidate, d);
        }
        
        return true;
    }
    
    public static BigInteger power(BigInteger a, BigInteger d, BigInteger n){
        BigInteger ans = new BigInteger("1");
        while(d.compareTo(new BigInteger("0")) != 0){
            if(d.mod(new BigInteger("2")).equals(1)){
                ans = ((ans.mod(n)).multiply(a.mod(n))).mod(n);
            }
            a =((a.mod(n)).multiply(a.mod(n))).mod(n);
            d = d.shiftRight(1);
        }
        return ans;
    
    }
    
    public static Boolean millerRabin(BigInteger candidate, BigInteger d){
        BigInteger a;
        Random rand = new Random();
        do {
            a = new BigInteger(candidate.bitLength(), rand); 
        } while(a.compareTo(candidate) >= 0);
        
        BigInteger x = power(a,d,candidate);
        
        if(x.equals(new BigInteger("1")) || x.equals(candidate.subtract(new BigInteger("1")))){
            return true;
        } else{
            while(d.compareTo(candidate.subtract(new BigInteger("1"))) != 0){
                x=((x.mod(candidate)).multiply(x.mod(candidate))).mod(candidate);
            if(x.equals(new BigInteger("1"))){
                return false;
                }
            if(x.equals(candidate.subtract(new BigInteger("1")))){
                return true;
                }
            d = d.shiftLeft(1);
            }
        }
        return false;
    }
}
