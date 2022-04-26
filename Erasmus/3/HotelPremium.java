/* UTF-8 codepage: Příliš žluťoučký kůň úpěl ďábelské ódy. ÷ × ¤
 * «Stereotype», Section mark-§, Copyright-©, Alpha-α, Beta-β, Smile-☺
 */




/*******************************************************************************
 * Instance třídy {@code HotelPremium} představují ...
 * The {@code HotelPremium} class instances represent ...
 *
 * @author  author name
 * @version 0.00.0000 — 20yy-mm-dd
 */
public class HotelPremium extends Hotel
{
    private double luxuryTax;
    
    public HotelPremium(){
        super();
        this.luxuryTax = 0;
        this.type = "Premium";
    }
    
    public HotelPremium(double luxuryTax, String code, String name, String location, int category, int numberOfRooms, int roomPrice){
        super(code, name, location, category, numberOfRooms, roomPrice);
        this.luxuryTax = luxuryTax;
        calculatePrice();
        this.type = "Premium";
    }
    
    public HotelPremium(HotelPremium hotel){
        super(hotel);
        this.luxuryTax = hotel.getLuxuryTax();
        calculatePrice();
        this.type = "Premium";
    }
    
    public void setLuxuryTax(double luxuryTax){
        calculatePrice(luxuryTax);
        this.luxuryTax = luxuryTax;
    }
    
    public double getLuxuryTax(){
        return this.luxuryTax;
    }
    
    public void calculatePrice(){
        this.setRoomPrice(this.getRoomPrice() + this.luxuryTax);
    }
    
    public void calculatePrice(double newTax){
        this.setRoomPrice(this.getRoomPrice() - this.luxuryTax + newTax);
    }
}
