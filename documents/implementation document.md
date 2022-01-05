# Implementation document

## Overall structure

Overall structure of the packages in code is as follows in the graph below:

![Untitled](https://user-images.githubusercontent.com/48138976/147099306-6eef2421-d7f6-4260-9732-4b912dfb0534.png)

The project contains following packages, their core functionalities and how they're connected to eachother:
* `tiralabra`
  - Main package that contains classes `UI`, `Main` and `IO`
  - `UI`
    - Contains the user interface
    - Handles file saving/loading and calling methods from `RSAKit`
  - User inputs commands where RSA-keys are generated or loaded from files that are in the src folder
    - Keys can then be saved in .txt files
    - Keys are in plain text format in files named 'publicKey.txt' and 'privateKey.txt' respectively
  - User can input a command to input a plain text message
    - Message is padded, encrypted and then saved to src as 'message.txt'
  - Basic gist of the program is that another user can be sent and load 'publicKey.txt' and encrypt messages that can be sent back
* `textpadding`
  - Pads the inputted text to cipher
    - In actuality right now the class only converts the String to bytes
  - Converts cipher back to a String format
  - Is a separate package for future implementation of OAEP-algorithm (that was never realized)
* `encrypter`
  - Takes given padded data and public key and returns encrypted data
* `decrypter`
  - Takes given data and private key and returns decrypted data
* `keygenerator`
  - Core functionality is to create RSA public and private keys of given bit length (right now hard coded as 1024)
  - Generates a random BigIntegers between [n-1, n] bit length as prime number candidates 
  - Prime number candidates are tested with first 100 primes and with Miller-Rabin test 64 times so that they are probable primes
  - Two probable p and q are generated that will be used to make RSA keys
    - Public key exponent e is a fixed number 65,537 as it's the largest known prime with the form 2^k+1 and it's commonly used in RSA cryptosystems. 
      Generating a larger coprime adds little security for heavier computation demand
* `RSAKit`
  - Acts as an interface to connect between UI and other classes 

## Time and space complexities achieved

In here time complexities of the used algorithms are evaluated, of the programs classes, only `Encrypt`, `Decrypt` and `KeyGenerator` are the only ones that have functionalities that need to have their performance evaluated.

`Encrypt`

Decryption method is simple and uses BigInteger's built-in method modPow with public keys to decrypt the message. 

According to sources, modPow has a time complexity of O(M(n)*k), where M(n) is the time complexity of the multiplication algorithm, n is the length of the plaintext message and k bit length of the exponent. 

BigInteger uses adaptive multiplication between Schoolbook (O(n^2)), Karatsuba (O(n^1.585)) and 3-way Toom–Cook multiplication (O(n^(1.465)) so the time complexity varies between those.

`Decrypt`

Decryption method is simple and uses BigInteger's built-in method modPow with private keys to decrypt the message. Time complexity is the same as `Encrypt`.

`KeyGenerator`

Broken down in to steps:

1. Creating prime numbers p and q
    - Miller-Rabin for testing primality time complexity is O(k log^3 n)
2. Creating modulus n
    - Multiplication is at worst O(n^2) and at best O(n^(1.465)
3. Calculating Carmichael's totient O(n)
    - Multiplication is at worst O(n^2) and at best O(n^(1.465)
    - Division is at worst O(n^2) and at best O(M(n)*log n) where M(n) is the time complexity of multiplication algorithm
    - Time complexity of gcd is O(n)
4. Calculate private key using extended euclidean algorithm O(n)
    - According to sources, time complexity if O(log(m^2)) where m is the bit length of RSA modulus n


## Performance testing

Performance testing was run for key generation, encryption and decryption. All tests were run 100 times with bit lengths of {256, 512, 1024, 2048 and 4096}. 
In encryption and decryption tests, test input String of "Test input for performance" was used in their needed input forms (as bytes and BigInteger)
Every iteration was timed and the mean and standard deviation is calculated.


### Key generation performance test

| Key bit length | Mean          | Standard deviation          | 
|----------------|---------------|-----------------------------|
| 256            | 0.03609s      | 0.03319s                    |
| 512            | 0.14175s      | 0.09740s                    |
| 1024           | 0.82709s      | 0.58244s                    |
| 2048           | 7.10307s      | 4.90010s                    |
| 4096           | 140.35925s    | 95.49663s                   |

As shown, with longer bit lengths, more time is needed for computation. Standard deviations are almost the same magnitude as their means, which can be explained by how prime candidates are randomly generated. Sometimes the algorithm can get luckily and can quickly randomize a probable prime, sometimes it needs to generate many candidates before finding one.


| Key bit length | Mean (encryption) | Standard deviation (encryption)   | Mean (decryption) | Standard deviation   (decryption)| 
|----------------|-------------------|-----------------------------------|-------------------|----------------------------------|
| 256            | 0.00476ms         | 0.00562ms                         | 0.03028ms         | 0.01412ms                        |
| 512            | 0.01234ms         | 0.00618ms                         | 0.09043ms         | 0.00836ms                        |
| 1024           | 0.01126ms         | 0.00460ms                         | 0.57982ms         | 0.08495ms                        |
| 2048           | 0.05416ms         | 0.01120ms                         | 5.17869ms         | 0.39982ms                        |
| 4096           | 0.15574ms         | 0.03182ms                         | 36.3359ms         | 1.23354ms                        |

From the table we can see that decryption is taking longer than encryptions. This can be explained by that the public key is a fixed number that is relatively smaller than the private key, so less computational power is needed.

## Possible improvements to the code

The program was made for learning and demonstration purposes so it would not be viable for any real world applications, key generation is slow and in reality quite insecure. 
There is no padding done to the plain text: it is simply converted to bytes. Some kind of a text padding algorithm like OAEP would add security.

## Sources
https://en.wikipedia.org/wiki/Computational_complexity_of_mathematical_operations
https://en.wikipedia.org/wiki/Miller%E2%80%93Rabin_primality_test#Complexity
https://en.wikipedia.org/wiki/Modular_multiplicative_inverse
