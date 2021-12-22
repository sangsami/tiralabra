# Implementation document

## Overall structure

Overall structure of the packages in code is as follows in the graph below:

![Untitled](https://user-images.githubusercontent.com/48138976/147099306-6eef2421-d7f6-4260-9732-4b912dfb0534.png)

### Decrypt

### Encrypt

### KeyGenerator

### RSAKit

### TextPadding

### Main

## Time and space complexities achieved

### Decrypt

Decryption method is simple and uses BigInteger's built-in method modPow with private keys to decrypt the message. ModPow has a time complexity of O(log (N)^2), where N is the length of the encrypted message.

### Encrypt

Decryption method is simple and uses BigInteger's built-in method modPow with public keys to decrypt the message. ModPow has a time complexity of O(log (N)^2), where N is the length of the plaintext message.

### KeyGenerator

Broken down in to steps:

1. Creating prime numbers p and q
2. Creating modulus p*q O(n)
3. Calculating Carmichael's totient O(n)
4. Finding the coprime of the totient - ??
5. Calculate private key using extended euclidean algorithm O(n)

### RSAKit

### TextPadding

### Main

## Possible improvements to the code

Plain text could be padded with a real algorithm like OAEP

## Sources
