/* UTF-8 codepage: Příliš žluťoučký kůň úpěl ďábelské ódy. ÷ × ¤
 * «Stereotype», Section mark-§, Copyright-©, Alpha-α, Beta-β, Smile-☺
 */

import java.util.Objects;


/*******************************************************************************
 * Instance třídy {@code Hotel} představují ...
 * The {@code Hotel} class instances represent ...
 *
 * @author  author name
 * @version 0.00.0000 — 20yy-mm-dd
 */
public class Hotel
{
    private String code;
    private String name;
    private String location;
    private int category;
    private int numberOfRooms;
    protected double roomPrice;
    protected String type;
    
    public Hotel(String code, String name, String location, int category, int numberOfRooms, double roomPrice){
        this.code = code;
        this.name = name;
        this.location = location;
        setCategory(category);
        this.numberOfRooms = numberOfRooms;
        this.roomPrice = roomPrice;
    }
    
    public Hotel(Hotel hotel){
        this.code = hotel.getCode();
        this.name = hotel.getName();
        this.location = hotel.getLocation();
        this.category = hotel.getCategory();
        this.numberOfRooms = hotel.getNumberOfRooms();
        this.roomPrice = hotel.getRoomPrice();
    }
    
    public Hotel(){
        code = "na";
        name = "na";
        location = "na";
        category = 0;
        numberOfRooms = 0;
        roomPrice = 0; 
    }
    
    public void setCode(String code){
        this.code = code;
    }
    
    public void setName(String name){
        this.name = name;
    }
    
    public void setLocation(String location){
        this.location = location;
    }
    
    public void setCategory(int category){
        if (category > 5) this.category = 5;
        else if (category < 1) this.category = 1;
            else this.category = category;
    }
    
    public void setNumberOfRooms(int number){
        this.numberOfRooms = number;
    }
    
    public void setRoomPrice(double price){
        this.roomPrice = price;
    }
    
    public String getCode(){
        return code;
    }
    
    public String getName(){
        return name;
    }
    
    public String getLocation(){
        return location;
    }
    
    public int getCategory(){
        return category;
    }
    
    public int getNumberOfRooms(){
        return numberOfRooms;
    }
    
    public double getRoomPrice(){
        return roomPrice;
    }
    
    public String getType(){
        return this.type;
    }
   
    
    public int hashCode() {
        return Objects.hash(code, name, location, category, numberOfRooms, roomPrice);
    }
    
    public boolean equals(Object o){
        if (this == o) return true;
        if ((o == null)||(o.getClass() != this.getClass())) return false;
        
        Hotel hotel = (Hotel) o;
        return this.code.equals(hotel.getCode()) && this.name.equals(hotel.getName()) && this.location.equals(hotel.getLocation()) && 
        this.category == hotel.getCategory() && this.numberOfRooms == hotel.getNumberOfRooms() && this.roomPrice == hotel.getRoomPrice() ;
    }
    
    @Override
    public String toString(){
        StringBuilder str = new StringBuilder();

        str.append(this.code);
        str.append(this.name);
        str.append(this.location);
        str.append(this.category);
        str.append(this.numberOfRooms);
        str.append(this.roomPrice);

        return str.toString();
    }
    
    public double dailyRevenue(){
        return roomPrice*numberOfRooms;
    }
}
