/* UTF-8 codepage: Příliš žluťoučký kůň úpěl ďábelské ódy. ÷ × ¤
 * «Stereotype», Section mark-§, Copyright-©, Alpha-α, Beta-β, Smile-☺
 */




/*******************************************************************************
 * Instance třídy {@code Product} představují ...
 * The {@code Product} class instances represent ...
 *
 * @author  author name
 * @version 0.00.0000 — 20yy-mm-dd
 */
public class Product
{
    private String code;
    private String description;
    private double price;
    private Percentage tax;
    private Percentage discount;
    
    public Product(){
        code = "n/a";
        description = "n/a";
        price = -1;
        tax = null;
        discount = null;
    }
    
    public Product(String code, String description, double price, Percentage tax, Percentage discount){
        this.code = code;
        this.description = description;
        this.price = price;
        this.tax = tax;
        this.discount = discount;
    }
    
    public Product(Product product){
        this.code = product.getCode();
        this.description = product.getDescription();
        this.price = product.getPrice();
        this.tax = product.getTax();
        this.discount = product.getDiscount();
    }
    
    public String getCode(){
        return code;
    }
    
    public String getDescription(){
        return description;
    }
    
    public double getPrice(){
        return price;
    }
    
    public Percentage getTax(){
        return tax;
    }
    
    public Percentage getDiscount(){
        return discount;
    }
    
    public void setCode(String code){
        this.code = code;
    }
    
    public void setDescription(String description){
        this.description = description;
    }
    
    public void setPrice(double price){
        this.price = price;
    }
    
    public void setTax(Percentage tax){
        this.tax = tax;
    }
    
    public void setDiscount(Percentage discount){
        this.discount = discount;
    }
    
    public double calculateOrderLineValue(){
        double discounts = calculateDiscountValue();
        double taxes = price/100 * tax.getVal();
        
        return price - discounts + taxes;
    }
    
    public double calculateDiscountValue(){
        return price/100 * discount.getVal();
    }
    
}
