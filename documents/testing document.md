# Testing document

## Coverage report for unit tests


## What was tested and how

Parts in the project that were omitted from unit testing: 
* Main.java
* IO.java
* UI.java
* RSAkit.java

These parts mainly comprised of trivial code like getters, setters and the user interface, so I deemed unit tests for these parts unnecessary for testing.

Parts that were tested, their tests and what was done:

`encrypt`

* `plainNumberEncryptedCorrectly()`

  - Test setup: testing input was simple predetermined public key with a plain number where the outcome of the encrypted number is already known.
Plain number was inputted with a public key and then asserted that the result is same with the known encrypted number.
  - Inputs:
    - Public key exponent e: 17
    - Modulus n: 3233
    - Input data: 65
  - Expected output:
    - Encrypted data: 2790

`decrypt`

* `encryptedDataIsDecryptedCorrectly()`
  - Test setup : testing inputs was a simple predetermined private keyy with a encrypted number where the outcome of decrypted number is already known.
Encrypted number was inputted with a private key and then asserted that the result is same with the known decrypted number.
  - Inputs:
    - Private key exponent d: 413
    - Modulus n: 3233
    - Input data: 2790
  - Expected output:
    - Decrypted data: 65

`keygenerator`
* `keyGeneratorCreatesKeysProbablePrimes`
  - Test setup: created prime is validated as a probable prime with BigInteger's built-in function IsProbablePrime(), with 99.9% certainty
  - Inputs:
    - Generated RSA keys
  - Expected output:
    - IsProbablePrime(10) return value: true
    
* `keyGeneratorCreatesKeysThatAreKeysToEachOther`
  - Test setup: tests that created keys convert input value with modulus arithmetic from and back to their original values
  - Inputs:
    - Generated RSA keys
    - Input value: 541
  - Expected output:
    - Input value is modular exponentiated with both exponents: 541

`textpadding`
* `textConvertsToCipherCorrectly`
  - Test setup: tests that function call converts String to a byte array correctly
  - Inputs:
    - Test string: "Test string that is to be converted to cipher"
  - Expected output:
    - Byte array of test string is asserted as the same

* `cipherConvertsToTextCorrectly`
  - Test setup: tests that function call converts BigInteger back to String
  - Inputs:
    - Test input: test string "Test string that is to be converted to cipher" that is converted to BigInteger
  - Expected output:
    - Function returns: "Test string that is to be converted to cipher"

## Sources

Keys for encryption and decryption tests: https://en.wikipedia.org/wiki/RSA_(cryptosystem)
