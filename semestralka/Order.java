/* UTF-8 codepage: Příliš žluťoučký kůň úpěl ďábelské ódy. ÷ × ¤
 * «Stereotype», Section mark-§, Copyright-©, Alpha-α, Beta-β, Smile-☺
 */




/*******************************************************************************
 * Instance třídy {@code Order} představují ...
 * The {@code Order} class instances represent ...
 *
 * @author  author name
 * @version 0.00.0000 — 20yy-mm-dd
 */
public class Order
{
    private Position position;
    private Service service;
    private String company;
    private String customer;
    
    public Order(Position position, Service service, String company, String customer){
        this.position = position;
        this.service = service;
        this.company = company;
        this.customer = customer;
    }
    
    public Position getPosition(){
        return this.position;
    }
    
    public Service getService(){
        return this.service;
    }
    
    public String getCompany(){
        return this.company;
    }
    
    public String getCustomer(){
        return this.customer;
    }
}
