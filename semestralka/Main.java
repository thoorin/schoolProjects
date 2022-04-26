/* UTF-8 codepage: Příliš žluťoučký kůň úpěl ďábelské ódy. ÷ × ¤
 * «Stereotype», Section mark-§, Copyright-©, Alpha-α, Beta-β, Smile-☺
 */

import java.util.*;
import java.util.Scanner;

/*******************************************************************************
 * Třída {@code Main} je hlavní třídou projektu,
 * který ...
 *
 * @author  author name
 * @version 0.00.0000 — 20yy-mm-dd
 */
public class Main
{
    /***************************************************************************
     * Metoda, prostřednictvím níž se spouští celá aplikace.
     *
     * @param args Parametry příkazového řádku
     */
    public static void main(String[] args)
    {    
        ArrayList<Customer> customers = new ArrayList<>();
        Position cp1 = new Position(60,1);
        Position cp2 = new Position(42,17);
        Position cp3 = new Position(93,28);
        Customer cus1 = new Customer("marek.skreko@gmail.com","Marek","poleno","budovatelska",null,cp1);
        Customer cus2 = new Customer("petra.husarova@educanet.cz","petra","karamel","Poruba",null,cp2);
        Customer cus3 = new Customer("dinusek@seznam.cz","alena","frankenberg","Frankenberg 432",null,cp3);
        customers.add(cus1);
        customers.add(cus2);
        customers.add(cus3);
        
        Position p1 = new Position(4,10);
        Position p2 = new Position(45,45);
        Position p3 = new Position(49,0);
        Bus b1 = new Bus(1,10,p1);
        Bus b2 = new Bus(1000000,10,p2);
        Bus b3 = new Bus(100000000,10,p3);
        Company c1 = new Company("matonoha@kurde.cz", "uber", "jidlo", "adresa", null, b1);
        Company c2 = new Company("pazgryvec@cyp.ova", "idos", "pika", "ostrava", null, b2);
        Company c3 = new Company("pizza@gmail.com", "kenrat", "pazuzu", "New York", null, b3);
        
        Companies c = new Companies();
        c.addCompany(c1);
        c.addCompany(c2);
        c.addCompany(c3);
        
        UserInterface u = new UserInterface(cus1, c, customers);
        
        u.mainPage();
        
        
    }
}
