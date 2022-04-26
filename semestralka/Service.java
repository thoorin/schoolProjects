/* UTF-8 codepage: Příliš žluťoučký kůň úpěl ďábelské ódy. ÷ × ¤
 * «Stereotype», Section mark-§, Copyright-©, Alpha-α, Beta-β, Smile-☺
 */




/*******************************************************************************
 * Instance třídy {@code Service} představují ...
 * The {@code Service} class instances represent ...
 *
 * @author  author name
 * @version 0.00.0000 — 20yy-mm-dd
 */
public abstract class Service
{
    private double priceKm;
    private double timeKm;
    private Position position;

    public Service(double priceKm, double timeKm, Position position){
        this.priceKm = priceKm;
        this.timeKm = timeKm;
        this.position = position;
    }
    
    public Service(){
        this.priceKm = 0;
        this.timeKm = 0;
    }
    
    public double getPriceKm(){
        return priceKm;
    }
    
    public double getTimeKm(){
        return timeKm;
    }
    
    public void setPriceKm(double price){
        priceKm = price;
    }
    
    public void setTimeKm(double time){
        timeKm = time;
    }
    
    public Position getPosition(){
        return position;
    }
    
    public void setPosition(Position position){
        this.position = position;
    }
}
