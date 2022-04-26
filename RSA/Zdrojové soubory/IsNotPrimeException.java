package com.company;

//výjimka, která se zavolá pokud vstup není prvočíslem
public class IsNotPrimeException extends Exception{
    public IsNotPrimeException(String message) {
        super(message);
    }
}
