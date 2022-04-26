/* UTF-8 codepage: Příliš žluťoučký kůň úpěl ďábelské ódy. ÷ × ¤
 * «Stereotype», Section mark-§, Copyright-©, Alpha-α, Beta-β, Smile-☺
 */

import java.util.*;
import java.util.Scanner;


/*******************************************************************************
 * Instance třídy {@code UserInterface} představují ...
 * The {@code UserInterface} class instances represent ...
 *
 * @author  author name
 * @version 0.00.0000 — 20yy-mm-dd
 */
public class UserInterface
{
    private Customer customer;
    private Company company;
    private Companies companies;
    private ArrayList<Customer> customers = new ArrayList<>();
    
    public UserInterface(Customer customer, Companies companies, ArrayList<Customer> customers){
        this.customer = customer;
        this.companies = companies;
        this.customers = customers;
    }
    
    public void createOrder(){
        Scanner scanner = new Scanner(System.in);
        
        System.out.println("Choose type of service that you wish to use.");
        System.out.println("taxi");
        System.out.println("bus");
        System.out.println("big");
        System.out.println("urgent");
        
        String n = scanner.next();
        
        Bus b = new Bus();
        Taxi t = new Taxi();
        Big bg = new Big();
        Urgent u = new Urgent();
        
        System.out.println("You can choose company by writing its name or find CHEAPEST or FASTEST option. Type FILTER if you wish to show only the companies that fit your criteria. CANCEL if you do not want to continue your order.");
        if (n.equals("bus")){
            companies.printCompanies(b);
        } else if (n.equals("taxi")){
            companies.printCompanies(t);
        } else if (n.equals("big")){
            companies.printCompanies(bg);
        } else {
            companies.printCompanies(u);
        }
        
        n = scanner.next();
        
        Company comp = null;
        if (n.equals("cheapest")) comp = findCheapest();
        else if (n.equals("fastest")) comp = findFastest();
            else if (n.equals("filter")) filtr();
                else if (n.equals("cancel")) homeCustomer();
                    else comp = companies.findCompany(n);
        System.out.println("You chose " + comp.getName() + " for your delivery.");
        
        Order ord = new Order(customer.getPosition(), comp.getService(), comp.getName(), customer.getName());
        
        System.out.println("Delivery completed! Here is a summary: ");
        System.out.println("Delivered to: latitude of " + ord.getPosition().getLatitude() + ", longitude of " + ord.getPosition().getLongitude());
        System.out.println("Service type being used: " + comp.getService().getClass());
        customer.addHistory(ord);
        comp.addHistory(ord);
        
        scanner.close();
        
        repeatingOrder();
    }
    
    public void repeatingOrder(){
        Scanner scanner = new Scanner(System.in);
        
        System.out.println("Do you want to make another order?");
        
        String n = scanner.next();
        
        if (n.equals("yes")) createOrder();
        if (n.equals("no")) homeCustomer();
        
        scanner.close();
    }
    
    public Company findCheapest(){
        Company cheapest = null;
        double d1 = -1;
        double d2 = -1;
        for(Company comp : companies.getCompanies()){   
            d1 = distance(comp.getService().getPosition().getLatitude(),comp.getService().getPosition().getLongitude(),customer.getPosition().getLatitude(),customer.getPosition().getLongitude())*comp.getService().getPriceKm();
            if (d2 != -1) {
                if (d1 < d2) {
                    cheapest = comp;
                    d2 = d1;
                }
            } else {
                cheapest = comp;
                d2 = d1;
            }
        }
            return cheapest;
    }
    
    public Company findFastest(){
        Company fastest = null;
        double d1 = -1;
        double d2 = -1;
        for(Company comp : companies.getCompanies()){   
            d1 = distance(comp.getService().getPosition().getLatitude(),comp.getService().getPosition().getLongitude(),customer.getPosition().getLatitude(),customer.getPosition().getLongitude())*comp.getService().getTimeKm();
            if (d2 != -1) {
                if (d1 < d2) {
                    fastest = comp;
                    d2 = d1;
                }
            } else {
                fastest = comp;
                d2 = d1;
            }
        }
            return fastest;
    }
    
    public static double distance(double lat1, double lon1, double lat2, double lon2) {
        if ((lat1 == lat2) && (lon1 == lon2)) {
            return 0;
        }
        else {
            double theta = lon1 - lon2;
            double dist = Math.sin(Math.toRadians(lat1)) * Math.sin(Math.toRadians(lat2)) + Math.cos(Math.toRadians(lat1)) * Math.cos(Math.toRadians(lat2)) * Math.cos(Math.toRadians(theta));
            dist = Math.acos(dist);
            dist = Math.toDegrees(dist);
            dist = dist * 60 * 1.1515;
            
            dist = dist * 1.609344;
            
            return (dist);
                }
    }

    public void registerCustomer(){
        Scanner scanner = new Scanner(System.in);
        
        System.out.println("              REGISTRATION");
        System.out.println("Name:");
        
        String name = scanner.next();
        
        System.out.println("Email:");
        
        String email = scanner.next();
        
        System.out.println("Password:");
        
        String password = scanner.next();
        
        System.out.println("Address:");
        
        String address = scanner.next();
        
        System.out.println("Latitude of your position:");
        
        double latitude = scanner.nextDouble();
        
        System.out.println("Longitude of your position:");
        
        double longitude = scanner.nextDouble();
        
        Customer cus = new Customer(email,name,password,address,null,new Position(latitude,longitude));
        
        customers.add(cus);
        
        System.out.println("You were succesfully registered, here is summary: email:" + cus.getEmail());
        System.out.println("name:" + cus.getName());
        System.out.println("password:" + cus.getPassword());
        System.out.println("address:" + cus.getAddress());
        System.out.println("Position: latitude - " + cus.getPosition().getLatitude() + ", longitude: " + cus.getPosition().getLongitude());
        
        login();
        scanner.close();
    }
    
    public void login(){
        Scanner scanner = new Scanner(System.in);
        
        System.out.println("              LOGIN");
        System.out.println("Name:");
        String name = scanner.next();
        
        System.out.println("Password:");
        String password = scanner.next();
        
        Customer loggedCustomer = null;
        Company loggedCompany = null;
        Actor loggedActor = null;
        Boolean logged = false;
        
        for(Customer cus : customers){
            if (cus.getName().equals(name) && cus.getPassword().equals(password)){
                loggedCustomer = cus;
                loggedActor = cus;
                logged = true;
                break;
            }
        }
        
        if (!logged) {
            for(Company comp : companies.getCompanies()){
            if (comp.getName().equals(name) && comp.getPassword().equals(password)){
                loggedCompany = comp;
                loggedActor = comp;
                logged = true;
                break;
            }
        }
        }
        
        if (!logged) {
            System.out.println("Wrong name or password");
            mainPage();
        }
        else {
            System.out.println("Login was succesfull. You are logged as " + loggedActor.getName());
            System.out.println("Email:" + loggedActor.getEmail());
            System.out.println("password:" + loggedActor.getPassword());
            System.out.println("address:" + loggedActor.getAddress());
            if (loggedCompany == null){
            System.out.println("Position: latitude - " + loggedCustomer.getPosition().getLatitude() + ", longitude: " + loggedCustomer.getPosition().getLongitude());
            customer = loggedCustomer; 
            homeCustomer();
        }   else {
            System.out.println("Service being offered: " + loggedCompany.getService().getClass());
            company = loggedCompany;
            homeCompany();
        }
        }
        
        scanner.close();
    }
    
    
    
    public void homeCustomer(){
        Scanner scanner = new Scanner(System.in);
        
        System.out.println("\nIf you want to make new order write command ORDER, if you wish to see history of your orders write LIST, for logging out type LOGOUT. \nYou can also view general STATISTICS.");
        String n = scanner.next();
        if (n.equals("order")) createOrder();
        if (n.equals("list")) showListCustomer();
        if (n.equals("logout")) mainPage();
        if (n.equals("statistics")) showStatistics(true);
        
        scanner.close();
    }
    
    public void homeCompany(){
        Scanner scanner = new Scanner(System.in);
        
        System.out.println("\nIf you want to edit your company services write UPDATE, if you wish to see history of your orders write LIST, for logging out type LOGOUT.\n With command SHOW you can view information about your company, you can also view general STATISTICS.");
        String n = scanner.next();
        if (n.equals("update")) update();
        if (n.equals("list")) showListCompany();
        if (n.equals("logout")) mainPage();
        if (n.equals("show")) show();
        if (n.equals("statistics")) showStatistics(false);
        
        scanner.close();
    }
    
    public void mainPage(){
        Scanner scanner = new Scanner(System.in);
        
        System.out.println("Do you want to LOGIN or make REGISTRATION?");
        
        String n = scanner.next();
        
        if (n.equals("login")) login();
        if (n.equals("registration")) register();
        
        scanner.close();
    }
    
    public void showListCustomer(){
        Scanner scanner = new Scanner(System.in);
        
            customer.printHistory();
            homeCustomer();
        
        scanner.close();
    }
    
    public void showListCompany(){
        Scanner scanner = new Scanner(System.in);
        
            company.printHistory();
            homeCompany();
        
        scanner.close();
    }
    
    public void filtr(){
        Scanner scanner = new Scanner(System.in);
        
            ArrayList<Company> companiesFiltered = new ArrayList<>();
            companiesFiltered = companies.getCompanies();
            
            System.out.println("Maximum time:");
            double time = scanner.nextDouble();
            
            System.out.println("Maximum cost:");
            double cost = scanner.nextDouble();
            
            companiesFiltered = companies.findCompaniesTime(time, customer, companiesFiltered);
            companiesFiltered = companies.findCompaniesCost(cost, customer, companiesFiltered);
            
            System.out.println("\nThese companies fit your criteria. CANCEL to storno your order");
            for(Company comp : companiesFiltered){
                System.out.println(comp.getName());
            }
            
            String n = scanner.next();
            
            if (n.equals("cancel")) homeCustomer();
            Company comp = companies.findCompany(n);
            System.out.println("You chose " + comp.getName() + " for your delivery.");
        
            Order ord = new Order(customer.getPosition(), comp.getService(), comp.getName(), customer.getName());
        
            System.out.println("Delivery completed! Here is a summary: ");
            System.out.println("Delivered to: latitude of " + ord.getPosition().getLatitude() + ", longitude of " + ord.getPosition().getLongitude());
            System.out.println("Service type being used: " + comp.getService().getClass());
            customer.addHistory(ord);
            comp.addHistory(ord);
        
        scanner.close();
        
        repeatingOrder();
    }
    
    public void register(){
        Scanner scanner = new Scanner(System.in);
        
        System.out.println("              REGISTRATION");
        
        System.out.println("Are you CUSTOMER or COMPANY?");
        
        String n = scanner.next();
        
        if (n.equals("customer")) registerCustomer();
        if (n.equals("company")) registerCompany();
        
        scanner.close();
    }
    
    public void registerCompany(){
        Scanner scanner = new Scanner(System.in);
        
        System.out.println("              REGISTRATION");
        System.out.println("Name:");
        
        String name = scanner.next();
        
        System.out.println("Email:");
        
        String email = scanner.next();
        
        System.out.println("Password:");
        
        String password = scanner.next();
        
        System.out.println("Address:");
        
        String address = scanner.next();
        
        System.out.println("Service offered (bus, taxi, urgent, big):");
        
        Service service = null;
        
        String n = scanner.next();
        
        if (n.equals("bus")) service = new Bus();
        if (n.equals("taxi")) service = new Taxi();
        if (n.equals("big")) service = new Big();
        if (n.equals("urgent")) service = new Urgent();
        
        System.out.println("Price per km:");
        double priceKm = scanner.nextDouble();
        
        service.setPriceKm(priceKm);
        
        System.out.println("Time for km:");
        double timeKm = scanner.nextDouble();
        
        service.setTimeKm(timeKm);
        
        System.out.println("Latitude:");
        double latitude = scanner.nextDouble();
        
        System.out.println("Longitude:");
        double longitude = scanner.nextDouble();
        
        service.setPosition(new Position(latitude, longitude));
        
        Company comp = new Company(email,name,password,address,null,service);
        
        companies.addCompany(comp);
        
        System.out.println("You were succesfully registered, here is summary: email:" + comp.getEmail());
        System.out.println("name:" + comp.getName());
        System.out.println("password:" + comp.getPassword());
        System.out.println("address:" + comp.getAddress());
        System.out.println("Service provided: " + comp.getService().getClass());
        
        login();
        scanner.close();
    }
    
    public void update(){
        Scanner scanner = new Scanner(System.in);
        
        System.out.println("Do you want to update PRICE per km, TIME for km or LOCATION of your service?");
        
        String n = scanner.next();
        
        if (n.equals("price")) {
            System.out.println("New price per km: ");
            double price = scanner.nextDouble();
            
            company.getService().setPriceKm(price);
        }
        if (n.equals("time")){
            System.out.println("New time for km: ");
            
            double time = scanner.nextDouble();
            
            company.getService().setTimeKm(time);
        }
        if (n.equals("location")) {
            System.out.println("New location: ");
            
            String address = scanner.next();
            
            company.setAddress(address);
        }
        
        homeCompany();
        scanner.close();
    }
    
    public void show(){
            System.out.println("Name:" + company.getName());
            System.out.println("Email:" + company.getEmail());
            System.out.println("password:" + company.getPassword());
            System.out.println("address:" + company.getAddress());
            System.out.println("Service being offered: " + company.getService().getClass());
            
            homeCompany();
    }
    
    public void showStatistics(Boolean customer){
            Company mostActive = null;
            for(Company comp : companies.getCompanies()){
                if (mostActive == null) mostActive = comp;
                else if (mostActive.getHistory().size() < comp.getHistory().size()) mostActive = comp;
            }
            System.out.println("Most active company: " + mostActive.getName());
            
            Customer mostActiveCus = null;
            for(Customer cus : customers){
                if (mostActiveCus == null) mostActiveCus = cus;
                else if (mostActiveCus.getHistory().size() < cus.getHistory().size()) mostActiveCus = cus;
            }
            System.out.println("Most active customer: " + mostActiveCus.getName());
            
            if (customer) homeCustomer();
            else homeCompany();
            
    }
    
    
}
