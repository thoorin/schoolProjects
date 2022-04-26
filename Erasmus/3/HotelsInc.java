/* UTF-8 codepage: Příliš žluťoučký kůň úpěl ďábelské ódy. ÷ × ¤
 * «Stereotype», Section mark-§, Copyright-©, Alpha-α, Beta-β, Smile-☺
 */


import java.util.*; 

/*******************************************************************************
 * Instance třídy {@code HotelsInc} představují ...
 * The {@code HotelsInc} class instances represent ...
 *
 * @author  author name
 * @version 0.00.0000 — 20yy-mm-dd
 */
public class HotelsInc
{
    Map<String, Hotel> hotels = new HashMap<>();
    
    public void add(Hotel h){
        hotels.put(h.getCode(), h);
    }
    
    public boolean hotelExists(String code){
        if (hotels.containsKey(code)) return true;
        else return false;
    }
    
    public int howMany(){
        return hotels.size();
    }
    
    public int howMany(String location){
        int count = 0;
        for (Map.Entry<String,Hotel> entry : hotels.entrySet())  
            if (entry.getValue().getLocation().equals(location)) count++;
        return count;
    }
    
    public int howManyOfType(String type){
        int count = 0;
        for (Map.Entry<String,Hotel> entry : hotels.entrySet())  
            if (entry.getValue().getType().equals(type)) count++;
        return count;
    }
    
    public Hotel getHotel(String code){
        return hotels.get(code);
    }
    
    public List<Hotel> getHotels(){      
        Collection<Hotel> values = hotels.values(); 
        return new ArrayList<Hotel>(values);
    }
    
    public void add(Set<Hotel> hs){
        for(Hotel h : hs){
            hotels.put(h.getCode(), h);
        }
    }
    
    public double dailyRevenue(){
        double revenue = 0;
        for (Map.Entry<String,Hotel> entry : hotels.entrySet())  
            revenue += entry.getValue().dailyRevenue();
            
        return revenue;
    }
}
