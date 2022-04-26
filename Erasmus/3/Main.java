/* UTF-8 codepage: Příliš žluťoučký kůň úpěl ďábelské ódy. ÷ × ¤
 * «Stereotype», Section mark-§, Copyright-©, Alpha-α, Beta-β, Smile-☺
 */

import java.util.*; 


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
        HotelsInc h = new HotelsInc();
        
        Set<Hotel> hs = new HashSet<>();
        
        hs.add(new Hotel());
        hs.add(new Hotel("drk", "", "", 3, 5, 65));
        hs.add(new Hotel("dr", "", "", 3, 5, 65));
        
        h.add(hs);
        System.out.println(h.getHotels());
    }
}
