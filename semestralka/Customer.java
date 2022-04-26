/* UTF-8 codepage: Příliš žluťoučký kůň úpěl ďábelské ódy. ÷ × ¤
 * «Stereotype», Section mark-§, Copyright-©, Alpha-α, Beta-β, Smile-☺
 */

import java.util.*;
import java.time.LocalDate;


/*******************************************************************************
 * Instance třídy {@code Customer} představují ...
 * The {@code Customer} class instances represent ...
 *
 * @author  author name
 * @version 0.00.0000 — 20yy-mm-dd
 */
public class Customer extends Actor
{
    private Position position;
    private ArrayList<Order> history;

    public Customer()
    {
        super();
        position = null;
        history = new ArrayList<>();
    }
    
    public Customer(String email, String name, String password, String address, LocalDate birthDate, Position position)
    {
        super(email, name, password, address, birthDate);
        this.position = position;
        this.history = new ArrayList<>();
    }
    
    public Customer(Customer customer)
    {
        super(customer);
        this.position = customer.getPosition();
        this.history = customer.getHistory();
    }
    
    public Position getPosition(){
        return position;
    }
    
    public void setPosition(Position position){
        this.position = position;
    }
    
    public ArrayList<Order> getHistory(){
        return history;
    }
    
    public void setHistory(ArrayList history){
        this.history = history;
    }
    
    public void addHistory(Order order){
        history.add(order);
    }
    
    public void printHistory(){
        for (Order ord : history){
            System.out.println("Delivered to: latitude of " + ord.getPosition().getLatitude() + ", longitude of " + ord.getPosition().getLongitude());
            System.out.println("Service type used: " + ord.getService().getClass());
            System.out.println("Provided by: " + ord.getCompany() + "\n");
        }
    }
    

    
}
