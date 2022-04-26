/* UTF-8 codepage: Příliš žluťoučký kůň úpěl ďábelské ódy. ÷ × ¤
 * «Stereotype», Section mark-§, Copyright-©, Alpha-α, Beta-β, Smile-☺
 */




/*******************************************************************************
 * Třída {@code Main} je hlavní třídou projektu,
 * který ...
 *
 * @author  author name
 * @version 0.00.0000 — 20yy-mm-dd
 */
import java.util.Scanner;
import java.util.*;
import java.io.*;

public class Main
{
    /***************************************************************************
     * Metoda, prostřednictvím níž se spouští celá aplikace.
     *
     * @param args Parametry příkazového řádku
     */
    public static void main(String[] args)
    {
        ArrayList<String> ar = new ArrayList<>();
        
        Scanner scanner = new Scanner(System.in);
        System.out.println("How many strings do you want to put to an array?");
        int n = scanner.nextInt();
        System.out.println("Write your strings:");
        for(int i = 0;i<n;i++){
            String str = scanner.next();
            ar.add(str);
        }
        
        Methods m = new Methods();
        m.setArray(ar);
        
        System.out.println("The longest string in you array is: " + m.longestString());
        
        
        System.out.println("This is your array without repetions: " + m.arrayWithoutRepetitions());
        System.out.println("These strings appear in the array more than once: " + m.findRecurrentStrings());
        
        System.out.println("Type which string you want to find:");
        String stringToFind = scanner.next();
        System.out.println("The string " + stringToFind + " appears in the array " + m.findNumberOfOccurences(stringToFind) + " times");
        scanner.close();
    }
}
