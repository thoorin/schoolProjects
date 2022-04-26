/* UTF-8 codepage: Příliš žluťoučký kůň úpěl ďábelské ódy. ÷ × ¤
 * «Stereotype», Section mark-§, Copyright-©, Alpha-α, Beta-β, Smile-☺
 */




/*******************************************************************************
 * Instance třídy {@code Percentage} představují ...
 * The {@code Percentage} class instances represent ...
 *
 * @author  author name
 * @version 0.00.0000 — 20yy-mm-dd
 */
public class Percentage
{
    private String value;
    private int val;
    
    public Percentage(int value){
        this.value = Integer.toString(value)+"%";
        this.val = value;
    }
    
    public int getVal(){
        return val;
    }
}
