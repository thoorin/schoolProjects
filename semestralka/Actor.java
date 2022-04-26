/* UTF-8 codepage: Příliš žluťoučký kůň úpěl ďábelské ódy. ÷ × ¤
 * «Stereotype», Section mark-§, Copyright-©, Alpha-α, Beta-β, Smile-☺
 */

import java.time.LocalDate;


public abstract class Actor
{
    private String email;
    private String name;
    private String password;
    private String address;
    private LocalDate birthDate;
    
    public Actor(){
        email = "na";
        name = "na";
        password = "na";
        address = "na";
        birthDate = LocalDate.now();
    }
    
    public Actor(String email, String name, String password, String address, LocalDate birthDate){
        this.email = email;
        this.name = name;
        this.password = password;
        this.address = address;
        this.birthDate = birthDate;
    }
    
    public Actor(Actor actor){
        this.email = actor.getEmail();
        this.name = actor.getName();
        this.password = actor.getPassword();
        this.address = actor.getAddress();
        this.birthDate = actor.getBirthDate();
    }
    
    public String getEmail(){
        return email;
    }
    
    public String getName(){
        return name;
    }
    
    public String getPassword(){
        return password;
    }
    
    public String getAddress(){
        return address;
    }
    
    public LocalDate getBirthDate(){
        return birthDate;
    }
    
    public void setEmail(String email){
        this.email = email;
    }
    
    public void setName(String name){
        this.name = name;
    }
    
    public void setPassword(String password){
        this.password = password;
    }
    
    public void setAddress(String address){
        this.address = address;
    }
    
    public void setBirthDate(LocalDate birthDate){
        this.birthDate = birthDate;
    }
    
}
