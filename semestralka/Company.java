/* UTF-8 codepage: Příliš žluťoučký kůň úpěl ďábelské ódy. ÷ × ¤
 * «Stereotype», Section mark-§, Copyright-©, Alpha-α, Beta-β, Smile-☺
 */

import java.util.*;
import java.time.LocalDate;

/*******************************************************************************
 * Instance třídy {@code Company} představují ...
 * The {@code Company} class instances represent ...
 *
 * @author  author name
 * @version 0.00.0000 — 20yy-mm-dd
 */
public class Company extends Actor
{
    private Service service;
    private ArrayList<Order> history;

    public Company()
    {
        super();
        service = null;
        history = new ArrayList<>();
    }
    
    public Company(String email, String name, String password, String address, LocalDate birthDate, Service service)
    {
        super(email, name, password, address, birthDate);
        this.service = service;
        this.history = new ArrayList<>();
    }
    
    public Company(Company company)
    {
        super(company);
        this.service = company.getService();
        this.history = company.getHistory();
    }
    
    public Service getService(){
        return service;
    }
    
    public void setService(Service service){
        this.service = service;
    }
    
    public ArrayList getHistory(){
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
            System.out.println("Ordered by: " + ord.getCustomer() + "\n");
        }
    }
   

}
