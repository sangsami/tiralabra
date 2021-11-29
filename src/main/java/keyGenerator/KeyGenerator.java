package keygenerator;

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
    
    private static final int ARRSIZE = 3;
    private static final int BITLENGTH = 1024;
    private static final int TESTTIMES = 128;
    /**
     * Creates public and private keys for encryption from generated primes
     * @return BigInteger-array containing public and private keys
     */
    public static BigInteger[] createKeys() {
        BigInteger p = generatePrime(BITLENGTH, true);
        BigInteger q = generatePrime(BITLENGTH, true);
        
        // RSA modulus
        BigInteger n = p.multiply(q);
        System.out.println("Key n created");
        BigInteger pMinus = p.subtract(BigInteger.ONE);
        BigInteger qMinus = q.subtract(BigInteger.ONE);
        
        // Carmichael's totient
        BigInteger totient = pMinus
                .divide(pMinus.gcd(qMinus))
                .multiply(qMinus)
                .abs(); //lcm(n)
                
        BigInteger e = BigInteger.TWO;
        Random rand = new Random();
        while (!e.gcd(totient).equals(BigInteger.ONE)) {
            do {
                e = generatePrimeCandidate(totient.bitLength(), false); 
            } 
            while (e.compareTo(totient) >= 0);
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
    /**
     * Extended euclidean algorithm.
     * @param e publicKey.
     * @param n RSA modulus.
     * @return modular multiplicative inverse in BigInteger.
     */
    static BigInteger modMultipInv(BigInteger e, BigInteger n) {
        if (e.compareTo(n) > 0) {
            BigInteger temp = n;
            n = e;
            e = temp;
        } if (e.compareTo(BigInteger.ONE) == 0) {
            return BigInteger.ONE;
        } 
        BigInteger d = BigInteger.ONE
                .add(n.multiply(e.subtract(modMultipInv(n.mod(e), e))))
                .divide(e);
        return d;
    }
    /**
     * Generates a random BigInteger of n bit length.
     * @param n bit length of the generated random BigInteger.
     * @param shift shifts the lower bit length bound to n-1 if true.
     * @return BigInteger of n bit length.
     */
    static BigInteger generatePrimeCandidate(int n, boolean shift) {
        BigInteger candidate = new BigInteger(n, new Random());
        if(shift) {
            candidate = candidate.setBit(0);
        }
        return candidate;
    }
    /**
     * Generates a prime number.
     * @param n bit length of the generated random BigInteger.
     * @param shift shifts the lower bit length bound to n-1 if true.
     * @return BigInteger prime number of n bit length.
     */
    static BigInteger generatePrime(int n, boolean shift) {
        BigInteger prime = BigInteger.TWO;
        
        while(!isPrime(prime, TESTTIMES)) {
            prime = generatePrimeCandidate(n, true);
        } return prime;
    }
    /**
     * Miller-Rabin test, yet to moved to it's own method.
     */
    static Boolean millerRabin(int repeat) {
        return false;
    }
    /**
     * Checks if given random BigInteger is a probable prime number.
     * @param candidate random BigInteger that will be tested.
     * @param n amount of times Miller-Rabin test to be run.
     * return Boolean value is given BigInteger could be prime.
     */
    static Boolean isPrime(BigInteger candidate, int n) {
        // Not a prime is divisible by 2
        if(candidate.mod(BigInteger.TWO).equals(BigInteger.ZERO)) {
            return false;
        }
        
        // Miller-Rabin primality test, will be moved as own method soon
        BigInteger s = BigInteger.ZERO;
        BigInteger[] r = new BigInteger[2];
        r[0] = candidate.subtract(BigInteger.ONE);
        r[1] = BigInteger.ZERO;
        
        while(r[0].mod(BigInteger.TWO).equals(BigInteger.ZERO)) {
            s = s.add(BigInteger.ONE);
            r = r[0].divideAndRemainder(BigInteger.TWO);
        }
        
        for(int i = 0; i < n; i++) {
            BigInteger a;
            Random rand = new Random();
            do {
                a = new BigInteger(candidate.bitLength(), rand); 
                } while(a.compareTo(candidate.subtract(BigInteger.TWO)) >= 0);
            
            BigInteger x = a.modPow(r[0], candidate);
            
            if (!x.equals(BigInteger.ONE)
                    || !x.equals(candidate.subtract(BigInteger.ONE))) {
               BigInteger j = new BigInteger("1");
               while (j.compareTo(s) < 0
                       && !x.equals(candidate.subtract(BigInteger.ONE))) {
                   x = x.modPow(BigInteger.TWO, candidate);
                   
                   if(x.equals(BigInteger.ONE)) {
                       return false;
                    }
                   j = j.add(BigInteger.ONE);
               }
               if(x.compareTo(candidate.subtract(BigInteger.ONE)) != 0) {
                   return false;
               }
            }
        } return true;
    }
}
