/* UTF-8 codepage: Příliš žluťoučký kůň úpěl ďábelské ódy. ÷ × ¤
 * «Stereotype», Section mark-§, Copyright-©, Alpha-α, Beta-β, Smile-☺
 */




/*******************************************************************************
 * Instance třídy {@code HotelStandard} představují ...
 * The {@code HotelStandard} class instances represent ...
 *
 * @author  author name
 * @version 0.00.0000 — 20yy-mm-dd
 */
public class HotelStandard extends Hotel
{
    private Season season;
    private final double extraPrice = 20;
    
    public HotelStandard(){
        super();
        this.season = Season.LOW;
        this.type = "Standard";
    }
    
    public HotelStandard(Season season, String code, String name, String location, int category, int numberOfRooms, int roomPrice){
        super(code, name, location, category, numberOfRooms, roomPrice);
        this.season = season;
        if (season.equals(Season.HIGH)) this.setRoomPrice(getRoomPrice() + extraPrice);
        this.type = "Standard";
    }
    
    public HotelStandard(HotelStandard hotel){
        super(hotel);
        this.season = hotel.getSeason();
        this.type = "Standard";
    }
    
    public void setSeason(Season season){
        if (!this.season.equals(season)) {
            this.season = season;
            if (season.equals(Season.HIGH)) this.setRoomPrice(getRoomPrice() + extraPrice);
            else this.setRoomPrice(getRoomPrice() - extraPrice);
        }
    }
    
    public Season getSeason(){
        return this.season;
    }
}
