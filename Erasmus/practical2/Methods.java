




/*******************************************************************************
 * Instance třídy {@code Methods} představují ...
 * The {@code Methods} class instances represent ...
 *
 * @author  author name
 * @version 0.00.0000 — 20yy-mm-dd
 */
import java.io.*;
import java.util.*;


public class Methods
{
    private ArrayList<String> arrayOfStrings = new ArrayList<String>();
    
    public void setArray(ArrayList<String> arrayOfStrings){
        this.arrayOfStrings = arrayOfStrings;
    }
    
    public ArrayList<String> getArray(){
        return this.arrayOfStrings;
    }
    
    public String longestString(){
        String longest = "";
        for(String str : arrayOfStrings){
            if (longest.length() < str.length()) longest = str;          
        }
        return longest;
    }
    
    public ArrayList<String> arrayWithoutRepetitions(ArrayList<String> array){
        ArrayList<String> uniqueStrings = new ArrayList<String>();
        
        outer1:
            for(String str1 : array){                            
                for(String str2 : uniqueStrings){
                    if (str1.equals(str2)) continue outer1;
                }
                uniqueStrings.add(str1);
            }
        
        return uniqueStrings;
    }
    
    public ArrayList<String> arrayWithoutRepetitions(){
        ArrayList<String> uniqueStrings = new ArrayList<String>();
        
        outer2:
            for(String str1 : arrayOfStrings){                            
                for(String str2 : uniqueStrings){
                    if (str1.equals(str2)) continue outer2;
                }
                uniqueStrings.add(str1);
            }
        
        return uniqueStrings;
    }
    
    public ArrayList<String> findRecurrentStrings(){
        ArrayList<String> uniqueStrings = new ArrayList<String>();
        ArrayList<String> recurrentStrings = new ArrayList<String>();       
        
        outer3:
            for(String str1 : arrayOfStrings){                            
                for(String str2 : uniqueStrings){
                    if (str1.equals(str2)) {
                        recurrentStrings.add(str1);
                        continue outer3;
                    }
                }
                uniqueStrings.add(str1);
            }
        
        return arrayWithoutRepetitions(recurrentStrings);
    }
    
    public int findNumberOfOccurences(String str){
        int numberOfOccurences = 0;
        
        for(String str1 : arrayOfStrings){                            
            if (str1.equals(str)) numberOfOccurences++;
        }
        
        return numberOfOccurences;
    }
}
