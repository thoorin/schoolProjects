/* UTF-8 codepage: Příliš žluťoučký kůň úpěl ďábelské ódy. ÷ × ¤
 * «Stereotype», Section mark-§, Copyright-©, Alpha-α, Beta-β, Smile-☺
 */

import java.util.*;


/*******************************************************************************
 * Instance třídy {@code Companies} představují ...
 * The {@code Companies} class instances represent ...
 *
 * @author  author name
 * @version 0.00.0000 — 20yy-mm-dd
 */
public class Companies
{
    private ArrayList<Company> companies = new ArrayList<>();
    
    public void addCompany(Company comp){
        companies.add(comp);
    }
    
    public void printCompanies(Service service){
        for(Company comp : companies){
            if (comp.getService().getClass().equals(service.getClass()))
            System.out.println(comp.getName());
        }
    }
    
    public Company findCompany(String name){
        for(Company comp : companies){
            if (comp.getName().equals(name)) return comp;
        }
        return null;
    }
    
    public ArrayList<Company> getCompanies(){
        return companies;
    }
    
    public ArrayList<Company> findCompaniesTime(double time, Customer customer, ArrayList<Company> companiesTime){
        for(Company comp : companies){
            if (comp.getService().getTimeKm() * UserInterface.distance(
                            comp.getService().getPosition().getLatitude(),
                            comp.getService().getPosition().getLongitude(), customer.getPosition().getLatitude(),
                            customer.getPosition().getLongitude()
                            ) > time
            )
                companiesTime.remove(comp);
        }
        return companiesTime;
    }
    
    public ArrayList<Company> findCompaniesCost(double cost, Customer customer, ArrayList<Company> companiesCost){
        for(Company comp : companies){
            if (comp.getService().getPriceKm() * UserInterface.distance(
                            comp.getService().getPosition().getLatitude(),
                            comp.getService().getPosition().getLongitude(), customer.getPosition().getLatitude(),
                            customer.getPosition().getLongitude()
                            ) > cost
            )
                companiesCost.remove(comp);
        }
        return companiesCost;
    }
    
    
}
