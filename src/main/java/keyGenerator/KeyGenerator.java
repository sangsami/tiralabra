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
public class KeyGenerator{
    
    private static final int ARRSIZE = 3;
    
    public static BigInteger[] createKeys(){
        BigInteger p = generatePrime(1024, true);
        BigInteger q = generatePrime(1024, true);
        
        // RSA modulus
        BigInteger n = p.multiply(q);
        System.out.println("Key n created");
        BigInteger pMinus = p.subtract(BigInteger.ONE);
        BigInteger qMinus = q.subtract(BigInteger.ONE);
        
        // Carmichael's totient
        BigInteger totient = pMinus.divide(pMinus.gcd(qMinus)).multiply(qMinus).abs(); //lcm(n)
                
        BigInteger e = BigInteger.TWO;
        Random rand = new Random();
        while(!e.gcd(totient).equals(BigInteger.ONE)){
            do { 
                e = generatePrimeCandidate(totient.bitLength(), false); 
            } while(e.compareTo(totient) >= 0);
        } 
        System.out.println("Key e created");
        BigInteger d = modMultipInv(e, totient); 
        System.out.println("Key d created");
        BigInteger[] arr = new BigInteger[ARRSIZE];
        arr[0] = n;
        arr[1] = e;
        arr[2] = d;
        return arr;
    }
    
    // Extended euclidean algorithm
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
    
    // Create [n-1, n-bits] range random BigInteger
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
    static Boolean millerRabin(int repeat){
        
        return false;
    }
    static Boolean isPrime(BigInteger candidate, int n){
        // Not a prime is divisible by 2
        if(candidate.mod(BigInteger.TWO).equals(BigInteger.ZERO)){
            return false;
        }
        
        // Miller-Rabin primality test, will be moved as own method soon
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
