package keygenerator;

/**
 *
 * @author samidinh
 */
import java.util.Random;
import java.math.BigInteger;
import java.util.List;
/**
 *
 * @author samidinh
 */
public class KeyGenerator {
    /** Array for public key. */
    private BigInteger[] publicKey;
    /** Array for private key. */
    private BigInteger[] privateKey;
    /** Array size for returnable key array. */
    private static final int ARRSIZE = 3;
    /** Bit length of generated random numbers. */
    private static final int BITLENGTH = 1024;
    /** Times to loop Miller-Rabin primality test. */
    private static final int TESTTIMES = 128;
    
    public KeyGenerator() {
        this.publicKey = new BigInteger[2];
        this.privateKey = new BigInteger[2];
    }
    
    /**
     * Creates public and private keys for encryption from generated primes.
     */
    public void createKeys() {
        BigInteger p = generatePrime(BITLENGTH, true);
        BigInteger q = generatePrime(BITLENGTH, true);
        
        // RSA modulus
        BigInteger n = p.multiply(q);
        System.out.println("Modulus n created");
        BigInteger pMinus = p.subtract(BigInteger.ONE);
        BigInteger qMinus = q.subtract(BigInteger.ONE);
        
        // Carmichael's totient
        BigInteger totient = pMinus
                .divide(pMinus.gcd(qMinus))
                .multiply(qMinus)
                .abs(); //lcm(n)
                
        BigInteger e = BigInteger.TWO;
        while (!e.gcd(totient).equals(BigInteger.ONE)) {
            do {
                e = generatePrimeCandidate(totient.bitLength(), false); 
            } 
            while (e.compareTo(totient) >= 0);
        }
        System.out.println("Public exponent e created");
        BigInteger d = modMultipInv(e, totient);
        System.out.println("Private exponent d created");

        this.publicKey[0] = n;
        this.publicKey[1] = e;
        this.privateKey[0] = n;
        this.privateKey[1] = d;
    }
    /**
     * Returns public key.
     * @return BigInteger[]
     */
    public BigInteger[] getPublicKey() {
        return this.publicKey;
    }
    /**
     * Returns private key.
     * @return BigInteger[]
     */
    public BigInteger[] getPrivateKey() {
        return this.privateKey;
    }
    
    public void setKeys(List<String> data) {
        this.publicKey[0] = new BigInteger(data.get(0)
                .replace("\n", "")
                .replace("\r", ""));
        this.publicKey[1] = new BigInteger(data.get(1)
                .replace("\n", "")
                .replace("\r", ""));
        this.privateKey[0] = new BigInteger(data.get(0)
                .replace("\n", "")
                .replace("\r", ""));
        this.privateKey[1] = new BigInteger(data.get(2)
                .replace("\n", "")
                .replace("\r", ""));
    }
    /**
     * Extended euclidean algorithm.
     * @param e publicKey.
     * @param n RSA modulus.
     * @return modular multiplicative inverse in BigInteger.
     */
    private BigInteger modMultipInv(BigInteger e, BigInteger n) {
        if (e.compareTo(n) > 0) {
            BigInteger temp = n;
            n = e;
            e = temp;
        } 
        if (e.compareTo(BigInteger.ONE) == 0) {
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
    private BigInteger generatePrimeCandidate(int n, boolean shift) {
        BigInteger candidate = new BigInteger(n, new Random());
        if (shift) {
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
    private BigInteger generatePrime(int n, boolean shift) {
        BigInteger prime = BigInteger.TWO;
        
        while (!isPrime(prime, TESTTIMES)) {
            prime = generatePrimeCandidate(n, true);
        } return prime;
    }
    /**
     * Test if candidate is divisible by first 100 primes.
     * @param candidate prime number candidate.
     * @return Boolean value if given number passes all tests.
     */
    private Boolean smallPrimesTest(BigInteger candidate) {
        String[] primes
                = {"2", "3", "5", "7", "11", "13", "17", "19", "23", "29", "31",
                    "37", "41", "43", "47", "53", "59", "61", "67", "71", "73",
                    "79", "83", "89", "97", "101", "103", "107", "109", "113",
                    "127", "131", "137", "139", "149", "151", "157", "163",
                    "167", "173", "179", "181", "191", "193", "197", "199",
                    "211", "223", "227", "229", "233", "239", "241", "251",
                    "257", "263", "269", "271", "277", "281", "283", "293",
                    "307", "311", "313", "317", "331", "337", "347", "349",
                    "353", "359", "367", "373", "379", "383", "389", "397",
                    "401", "409", "419", "421", "431", "433", "439", "443",
                    "449", "457", "461", "463", "467", "479", "487", "491",
                    "499", "503", "509", "521", "523", "541"
                };

        for (String prime: primes) {
            if (candidate.mod(new BigInteger(prime))
                    .compareTo(BigInteger.ZERO) == 0) {
                return false;
            }
        }
        return true;
    }
    /**
     * Repeats Miller-Rabin test n times.
     * @param candidate prime number candidate to be tested.
     * @param n times to repeat test.
     * @return Boolean value if given number passes all tests.
     */
    private Boolean millerRabinRepeater(BigInteger candidate, int n) {
        // Miller-Rabin primality test, will be moved as own method soon
        BigInteger d = candidate.subtract(BigInteger.ONE);
        
        while (d.mod(BigInteger.TWO).equals(BigInteger.ZERO)) {
            d = d.shiftRight(1);
        }
        for (int i = 0; i < n; i++) {
            if (!millerRabinTest(d, candidate)) {
                return false;
            }
        }
        return true;
    }
    /**
     * Miller-Rabin primality test.
     * @param d exponent for modular arithmetics.
     * @param candidate prime candidate and modulus for modular arithmetics.
     * @return Boolean value if given number passes all tests.
     */
    private Boolean millerRabinTest(BigInteger d, BigInteger candidate) {
        BigInteger a;
        Random rand = new Random();
        do {
            a = new BigInteger(candidate.bitLength(), rand); 
            } while (a.compareTo(candidate.subtract(BigInteger.TWO)) >= 0);
            
        BigInteger x = a.modPow(d, candidate);
            
        while (d.compareTo(candidate.subtract(BigInteger.ONE)) != 0) {
            x = (x.multiply(x)).mod(candidate);
            d = d.shiftLeft(1);
            if (x.compareTo(BigInteger.ONE) == 0) {
                return false;
            }
            if (x.compareTo(candidate.subtract(BigInteger.ONE)) == 0) {
                return true;
            }
        }
        return false;
    }
    /**
     * Checks if given random BigInteger is a probable prime number.
     * @param candidate random BigInteger that will be tested.
     * @param n amount of times Miller-Rabin test to be run.
     * @return Boolean value is given BigInteger could be prime.
     */
    private Boolean isPrime(BigInteger candidate, int n) {
        // Not a prime is divisible by 2
        if (candidate.mod(BigInteger.TWO).equals(BigInteger.ZERO)) {
            return false;
        }
        
        if (!smallPrimesTest(candidate)) {
            return false;
        }
        
        if (!millerRabinRepeater(candidate, n)) {
            return false;
        }
        return true;
    }
}
