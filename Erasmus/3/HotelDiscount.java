/* UTF-8 codepage: Příliš žluťoučký kůň úpěl ďábelské ódy. ÷ × ¤
 * «Stereotype», Section mark-§, Copyright-©, Alpha-α, Beta-β, Smile-☺
 */




/*******************************************************************************
 * Instance třídy {@code HotelDiscount} představují ...
 * The {@code HotelDiscount} class instances represent ...
 *
 * @author  author name
 * @version 0.00.0000 — 20yy-mm-dd
 */
public class HotelDiscount extends Hotel
{
    private int occupation;
    
    public HotelDiscount(){
        super();
        this.occupation = 0;
        this.type = "Discount";
    }
    
    public HotelDiscount(int occupation, String code, String name, String location, int category, int numberOfRooms, int roomPrice){
        super(code, name, location, category, numberOfRooms, roomPrice);
        if (occupation < 0) this.occupation = 0;
        else if (occupation > 90) this.occupation = 90;
             else this.occupation = occupation;
        calculatePrice();
        this.type = "Discount";
    }
    
    public HotelDiscount(HotelDiscount hotel){
        super(hotel);
        this.occupation = hotel.getOccupation();
        this.type = "Discount";
    }
    
    public void setOccupation(int occupation){
        if (occupation < 0) {
            calculatePrice(occupation);
            this.occupation = 0;
        } else if (occupation > 90) {
                calculatePrice(occupation);
                this.occupation = 90;
            }else {
                calculatePrice(occupation);
                this.occupation = occupation;
            }
    }
    
    public int getOccupation(){
        return this.occupation;
    }
    
    public void calculatePrice(int newOccupation){
        double originalPrice = roomPrice*(1/(((occupation/0.9)*0.5+50)/100));
        super.setRoomPrice( originalPrice*(((newOccupation/0.9)*0.5+50)/100) );
    }
    
    public void calculatePrice(){
        super.setRoomPrice( roomPrice*(((occupation/0.9)*0.5+50)/100) );
    }
    
    @Override
    public void setRoomPrice(double price){
        roomPrice = price;
        calculatePrice();
    }
}
