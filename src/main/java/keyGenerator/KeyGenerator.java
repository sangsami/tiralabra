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
    /** Times to loop Miller-Rabin primality test. */
    private static final int TESTTIMES = 64;
    
    public KeyGenerator() {
        this.publicKey = new BigInteger[2];
        this.privateKey = new BigInteger[2];
    }
    
    /**
     * Creates public and private keys for encryption from generated primes.
     * @param bitLength the bit length of prime numbers generated for RSA keys
     */
    public void createKeys(int bitLength) {
        BigInteger p = generatePrime(bitLength);
        BigInteger q = generatePrime(bitLength);
        
        // RSA modulus
        BigInteger n = p.multiply(q);
        System.out.println("Modulus n created...");
        
        // Carmichael's totient
        BigInteger totient = carmichaelsTotient(p, q);
         
        /* 
        Using 65537 as the public exponent as it's the largest known prime
        with the form 2^k+1 and it's commonly used in RSA cryptosystems. 
        Generating a larger coprime gains little added security for more
        computation
        */
        BigInteger e = new BigInteger("65537");
        System.out.println("Public key e created...");
        BigInteger d = modularMultiplicativeInverse(e, totient);
        System.out.println("Private key d created...");

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
    /**
     * Sets public key read from a file.
     * @param data List public key modulus and exponent read from a 
     * file in String format.
     */
    public void setPublicKey(List<String> data) {
        this.publicKey[0] = new BigInteger(data.get(0)
                .replace("\n", "")
                .replace("\r", ""));
        this.publicKey[1] = new BigInteger(data.get(1)
                .replace("\n", "")
                .replace("\r", ""));
    }
    /**
     * Sets private key read from a file.
     * @param data List private key modulus and exponent read from a 
     * file in String format.
     */
    public void setPrivateKey(List<String> data) {
        this.privateKey[0] = new BigInteger(data.get(0)
                .replace("\n", "")
                .replace("\r", ""));
        this.privateKey[1] = new BigInteger(data.get(1)
                .replace("\n", "")
                .replace("\r", ""));
    }
    /**
     * Calculates the Carmichael's totient.
     * @param p prime number.
     * @param q prime number.
     * @return Carmichael's totient.
     */
    private BigInteger carmichaelsTotient(BigInteger p, BigInteger q) {
        return p.subtract(BigInteger.ONE)
                .divide(p.subtract(BigInteger.ONE)
                        .gcd(q.subtract(BigInteger.ONE)))
                .multiply(q.subtract(BigInteger.ONE))
                .abs(); //lcm(n)
    }
    /**
     * Calculates the modular multiplicative inverse.
     * @param e publicKey.
     * @param n RSA modulus.
     * @return modular multiplicative inverse in BigInteger.
     */
    private BigInteger modularMultiplicativeInverse(BigInteger e, BigInteger n)
    {
        if (e.compareTo(BigInteger.ONE) == 0) {
            return BigInteger.ONE;
        } 
        BigInteger d = BigInteger.ONE
                .add(n.multiply(
                        e.subtract(
                                modularMultiplicativeInverse(n.mod(e), e))))
                .divide(e);
        return d;
    }
    /**
     * Generates a random BigInteger of n bit length.
     * @param bitLength bit length of the generated random BigInteger.
     * @return BigInteger of n bit length.
     */
    private BigInteger generatePrimeCandidate(int bitLength) {
        BigInteger candidate = new BigInteger(bitLength, new Random());
        // shifts the generated integer lower bound to bitLength-1.
        candidate = candidate.setBit(0);
        return candidate;
    }
    /**
     * Generates a prime number.
     * @param bitLength bit length of the generated random BigInteger.
     * @return BigInteger prime number of n bit length.
     */
    public BigInteger generatePrime(int bitLength) {
        BigInteger prime = BigInteger.TWO;
        
        while (!isPrime(prime, TESTTIMES)) {
            prime = generatePrimeCandidate(bitLength);
        } 
        return prime;
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
    private Boolean millerRabinTest(BigInteger candidate, int n) {
        // Miller-Rabin primality test, will be moved as own method soon
        BigInteger d = candidate.subtract(BigInteger.ONE);
        
        while (d.mod(BigInteger.TWO).equals(BigInteger.ZERO)) {
            d = d.shiftRight(1);
        }
        for (int i = 0; i < n; i++) {
            if (!millerRabin(d, candidate)) {
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
    private Boolean millerRabin(BigInteger d, BigInteger candidate) {
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
        
        if (!millerRabinTest(candidate, n)) {
            return false;
        }
        return true;
    }
}
