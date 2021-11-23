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
    
    public static BigInteger[] createKeys(){
        BigInteger p = generatePrime(1024, true);
        System.out.println("Prime number p created");
        BigInteger q = generatePrime(1024, true);
        System.out.println("Prime number q created");
        BigInteger n = p.multiply(q);
        
        BigInteger pMinus = p.subtract(BigInteger.ONE);
        BigInteger qMinus = q.subtract(BigInteger.ONE);
        
        BigInteger cmTotient = pMinus.divide(pMinus.gcd(qMinus)).multiply(qMinus).abs(); //lcm(n)
                
        BigInteger e = BigInteger.TWO;
        Random rand = new Random();
        while(!e.gcd(cmTotient).equals(BigInteger.ONE)){
            do { 
                e = generatePrimeCandidate(cmTotient.bitLength(), false); 
            } while(e.compareTo(cmTotient) >= 0);
        } 
        System.out.println(e.gcd(cmTotient));
        
        BigInteger d = modMultipInv(e, cmTotient); 
        
        BigInteger[] arr = new BigInteger[4];
        arr[0] = n;
        arr[1] = e;
        arr[2] = n;
        arr[3] = d;
        return arr;
    }
    
    static BigInteger modMultipInv(BigInteger e, BigInteger n) {
        if (e.compareTo(n) > 0) {
            BigInteger temp = n;
            n = e;
            e = temp;
        }
        if (e.compareTo(BigInteger.ONE) == 0) {
            return BigInteger.ONE;
        }
        BigInteger d = BigInteger.ONE.add(n.multiply(e.subtract(modMultipInv(n.mod(e), e)))).divide(e);
        return d;
    }
    
    static BigInteger generatePrimeCandidate(int bits, boolean shift){
        BigInteger candidate = new BigInteger(bits, new Random());
        if(shift){
            candidate = candidate.setBit(0);
        }
        
        return candidate;
    }
    
    static BigInteger generatePrime(int bits, boolean shift){
        BigInteger prime = BigInteger.TWO;
        
        while(!isPrime(prime, 128)){
            prime = generatePrimeCandidate(bits, true);
        }
        
        return prime;
    }
    
    static Boolean isPrime(BigInteger candidate, int n){
        if(candidate.mod(BigInteger.TWO).equals(BigInteger.ZERO)){
            return false;
        }
        
        BigInteger s = BigInteger.ZERO;
        BigInteger[] r = new BigInteger[2];
        r[0] = candidate.subtract(BigInteger.ONE);
        r[1] = BigInteger.ZERO;
        
        while(r[0].mod(BigInteger.TWO).equals(BigInteger.ZERO)){
            s = s.add(BigInteger.ONE);
            r = r[0].divideAndRemainder(BigInteger.TWO);
        }
        
        for(int i = 0; i < n; i++){
            BigInteger a;
            Random rand = new Random();
            do {
                a = new BigInteger(candidate.bitLength(), rand); 
                } while(a.compareTo(candidate.subtract(BigInteger.TWO)) >= 0);
            
            BigInteger x = a.modPow(r[0], candidate);
            
            if(!x.equals(BigInteger.ONE) || !x.equals(candidate.subtract(BigInteger.ONE))) {
               BigInteger j = new BigInteger("1");
               while(j.compareTo(s) < 0 && !x.equals(candidate.subtract(BigInteger.ONE))){
                   x = x.modPow(BigInteger.TWO, candidate);
                   
                   if(x.equals(BigInteger.ONE)){
                       return false;
                   }
                   
                   j = j.add(BigInteger.ONE);
               }
               if(x.compareTo(candidate.subtract(BigInteger.ONE)) != 0){
                   return false;
               }
            }
        }
        return true;
    }
    

}
