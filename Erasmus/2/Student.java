/* UTF-8 codepage: Příliš žluťoučký kůň úpěl ďábelské ódy. ÷ × ¤
 * «Stereotype», Section mark-§, Copyright-©, Alpha-α, Beta-β, Smile-☺
 */




/*******************************************************************************
 * Instance třídy {@code Student} představují ...
 * The {@code Student} class instances represent ...
 *
 * @author  author name
 * @version 0.00.0000 — 20yy-mm-dd
 */
public class Student
{
    private String code;
    private String name;
    
    public Student(String code, String name){
        this.code = code;
        this.name = name;
    }
    
    public String getCode(){
        return code;
    }
    
    public String getName(){
        return name;
    }
    
    public int compareTo(Student student) {
        String thisName = this.getName();
        String otherName = student.getName();
        
        return thisName.compareTo(otherName);
    }
    
    public int hashCode() {
        int hash = 7;
        hash = hash * 37 + code.hashCode();
        hash = hash * 37 + name.hashCode();
        return hash;
    }
}
