package com.company;

import java.math.BigInteger;
import java.util.Random;

public class RSA {

    //metoda zašifrování
    public static Object[] cipher(String msg, BigInteger prime1, BigInteger prime2) throws IsNotPrimeException{
        //získání bitové délky
        int BIT_LENGTH = prime1.bitLength() + prime2.bitLength();

        //kontrola jestli není zpráva příliš velká na zašifrování s danými prvočísly
        if (BIT_LENGTH < 16*msg.length()) {
            return new Object[] {};
        }

        //kontrola zda vstupní čísla jsou prvočísly
        if (!prime1.isProbablePrime(1) || !prime2.isProbablePrime(1)) throw new IsNotPrimeException("One of the numbers is not prime number");

        Random rand = new Random();

        BigInteger n = prime1.multiply(prime2);
        BigInteger phi = prime1.subtract(BigInteger.valueOf(1)).multiply(prime2.subtract(BigInteger.valueOf(1)));

        //vytvoření klíčů
        BigInteger publicKey;
        BigInteger privateKey;
        do {
            publicKey = new BigInteger(phi.bitLength(), rand);
        } while (publicKey.compareTo(BigInteger.valueOf(1)) <= 0 || publicKey.compareTo(phi) >= 0 || !publicKey.gcd(phi).equals(BigInteger.valueOf(1)));
        privateKey = publicKey.modInverse(phi);

        BigInteger message = new BigInteger(msg.getBytes());
        //zašifrování zprávy
        BigInteger cipherText = message.modPow(publicKey, n);

        //přidání prvočísel do samotné zašifrované zprávy
        char c = 'l';
        String cT = String.valueOf(prime1) + c + String.valueOf(prime2) + c +String.valueOf(cipherText);

        return new Object[]{ publicKey, privateKey, cT };
    }

    //metoda dešifrování
    public static String decipher( String pK, String ciphered ){
        //získání prvočísel ze zašifrovaného textu
        String parts[] = ciphered.split("l");
        BigInteger prime1 = new BigInteger(parts[0]);
        BigInteger prime2 = new BigInteger(parts[1]);

        BigInteger n = prime1.multiply(prime2);
        BigInteger phi = prime1.subtract(BigInteger.valueOf(1)).multiply(prime2.subtract(BigInteger.valueOf(1)));

        BigInteger publicKey = new BigInteger(pK);
        //vypočítání privátního klíče z veřejného
        BigInteger privateKey;
        privateKey = publicKey.modInverse(phi);

        //dešifrování samotné zprávy
        BigInteger cipherText = new BigInteger(parts[2]);
        BigInteger decryptedText = cipherText.modPow(privateKey, n);
        String decrypted = new String(decryptedText.toByteArray());

        return decrypted;
    }
}
