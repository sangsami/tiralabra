# Specification document

## Study program: Computer science/tietojenkäsittelytieteen kandidaatti
## Language to be used in the project: English

## What programming language will you use? Also tell us what other languages can you write to the extent that you can peer review projects done in them if necessary.

I will be using Java in this projects. Other programming languages I can code in/peer review are C and Python.

## What algorithms and data structures will you be implementing in your work?

I will be implementing the RSA (Rivest-Shamir-Adleman)- algorithm.

## What problem will you be solving and why did you choose this/these particular algorithm(s)/data structure(s)?

The problem is how to encrypt information so that only those that _should_ be able to access the information will be able to access it. 
I picked this problem because I think cyber security is an interesting subject.

RSA-algorithm can be broken into three parts:

1. Key generation
2. Encryption
3. Decryption

Basically: there are two keys generated in RSA-algorithm: public and private key. Public is - as its name implies - is public, and known between users. 
The public key is used to encrypt a message and with the private key - that is only known between the sender and recipients - is used to decrypt the message.

## What inputs will the program receive and how will they be used?

The program has two parts: encryption and decryption. For both, inputs will simply be strings. 

Encryption part: the user will input their message in plain text where after the program will encrypt it. The program will return an encrypted message, 
a public key and a private key.

Decryption part: the user will input plain text-form encrypted message and private key where after the program will decrypt the message using the private key
and return a decrypted message.

## Target time and space complexities

Space complexity will be in O(1) as the input will not affect the needed variable sizes.

Time complexity is different in different parts of the algorithm, is dependent on the complexity of the mathematical operations in the program
and there are many different implementations of the algorithm, but standard RSA-algorithms time complexity will hover between O(log(N^2)) and O(log(N^3)). 
Which will be the target.
Usually decryption and private key generation is much slower than encryption and public key generation.

## Sources

- https://crypto.stackexchange.com/questions/6164/how-do-i-derive-the-time-complexity-of-encryption-and-decryption-based-on-modula/6194#6194
- https://en.wikipedia.org/wiki/RSA_(cryptosystem)
- https://www.youtube.com/watch?v=JD72Ry60eP4
- https://www.youtube.com/watch?v=wXB-V_Keiu8
