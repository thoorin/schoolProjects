/* UTF-8 codepage: Příliš žluťoučký kůň úpěl ďábelské ódy. ÷ × ¤
 * «Stereotype», Section mark-§, Copyright-©, Alpha-α, Beta-β, Smile-☺
 */




/*******************************************************************************
 * Instance třídy {@code Order} představují ...
 * The {@code Order} class instances represent ...
 *
 * @author  author name
 * @version 0.00.0000 — 20yy-mm-dd
 */
import java.time.LocalDateTime;
import java.util.ArrayList;

public class Order
{
    private int number;
    private LocalDateTime date;
    private ArrayList<Product> products = new ArrayList<>();
    private Client client;
    
    public Order(){
        number = -1;
        date = null;
        products = null;
        client = null;
    }
    
    public Order(int number, Client client){
        this.number = number;
        this.date = LocalDateTime.now();
        this.client = client;
    }
    
    public Order(Order order){
        this.number = order.getNumber();
        this.date = order.getDate();
        this.products = order.getProducts();
        this.client = order.getClient();
    }
    
    public int getNumber(){
        return number;
    }
    
    public LocalDateTime getDate(){
        return date;
    }
    
    public ArrayList<Product> getProducts(){
        return products;
    }
    
    public Client getClient(){
        return client;
    }
    
    public void setNumber(int number){
        this.number = number;
    }
    
    public void setDate(LocalDateTime date){
        this.date = date;
    }
    
    public void setProducts(ArrayList<Product> products){
        this.products = products;
    }
    
    public void setClient(Client client){
        this.client = client;
    }
    
    public double calculateTotalValue(){
        double totalValue = 0;
        for( Product product : products){
            totalValue += product.calculateOrderLineValue();
        }
        return totalValue;
    }   
    
    public double calculateDiscountValue(){
        double discountValue = 0;
        for( Product product : products){
            discountValue += product.calculateDiscountValue();
        }
        return discountValue;
    }
    
    public int totalNumberOfProducts(){
        int numberOfProducts = 0;
        for( Product product : products){
            numberOfProducts += 1;
        }
        return numberOfProducts;
    }
    
    public boolean existsProduct(String productCode){
        for( Product product : products){
            if (product.getCode().equals(productCode)) return true;
        }
        return false;
    }
    
    public void addProduct(Product product){
        products.add(product);
    }
    
    public void removeProduct(String productCode){
        for( Product product : products){
            if (product.getCode().equals(productCode)) products.remove(product);
        }
    }
}
