# Testing document

## Coverage report for unit tests


## What was tested and how

Parts in the project that were omitted from unit testing: 
* Main.java
* IO.java
* UI.java
* RSAkit.java

These parts mainly comprised of trivial code like getters, setters and the user interface, so I deemed unit tests for these parts unnecessary.

### Decrypt.java

One test was made: **encryptedDataIsDecryptedCorrectly()**

Testing inputs were simple predetermined private keyy with a encrypted number where the outcome of decrypted number is already known.
Encrypted number was inputted with a private key and then asserted that the result is same with the known decrypted number.

### Ecrypt.java

One test was made: **plainNumberEncryptedCorrectly()**

Testing inputs were simple predetermined public key with a plain number where the outcome of the encrypted number is already known.
Plain number was inputted with a public key and then asserted that the result is same with the known encrypted number.

### KeyGenerator,java

### TextPadding.java


## Sources

Keys for encryption and decryption tests: https://en.wikipedia.org/wiki/RSA_(cryptosystem)
