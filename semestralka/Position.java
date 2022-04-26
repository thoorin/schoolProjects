/* UTF-8 codepage: Příliš žluťoučký kůň úpěl ďábelské ódy. ÷ × ¤
 * «Stereotype», Section mark-§, Copyright-©, Alpha-α, Beta-β, Smile-☺
 */




/*******************************************************************************
 * Instance třídy {@code Position} představují ...
 * The {@code Position} class instances represent ...
 *
 * @author  author name
 * @version 0.00.0000 — 20yy-mm-dd
 */
public class Position
{
    private double longitude;
    private double latitude;   
    
    public Position(double latitude, double longitude) {
        this.latitude  = latitude;
        this.longitude = longitude;
    }
    
    public double getLatitude(){
        return this.latitude;
    }
    
    public double getLongitude(){
        return this.longitude;
    }




//##############################################################################
//\CI== STATIC INITIALIZER (CLASS CONSTRUCTOR) =================================
//\CG== CLASS (STATIC) GETTERS AND SETTERS =====================================
//\CM== OTHER NON-PRIVATE CLASS (STATIC) METHODS ===============================
//\CP== PRIVATE AND AUXILIARY CLASS (STATIC) METHODS ===========================



//##############################################################################
//\IC== CONSTANT INSTANCE ATTRIBUTES (FIELDS) ==================================
//\IV== VARIABLE INSTANCE ATTRIBUTES (FIELDS) ==================================



//##############################################################################
//\II== CONSTRUCTORS AND FACTORY METHODS =======================================

    /***************************************************************************
     */
    public Position()
    {
    }



//\IA== ABSTRACT METHODS =======================================================
//\IG== INSTANCE GETTERS AND SETTERS ===========================================
//\IM== OTHER NON-PRIVATE INSTANCE METHODS =====================================
//\IP== PRIVATE AND AUXILIARY INSTANCE METHODS =================================



//##############################################################################
//\NT== NESTED DATA TYPES ======================================================
}
