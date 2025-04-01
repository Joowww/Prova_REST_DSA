package edu.upc.dsa.models;
import edu.upc.dsa.util.RandomUtils;

public class Product {

    private  String id;
    private  String name;
    private double price;
    private int sales;

    public Product() {
    }


    public Product(String id, String name, double price) {
        this.id = id;
        this.name = name;
        this.price = price;
        this.sales = 0;
    }

    public int getSales() {
        return sales;
    }

    public void increaseSales(int quantity) {
        this.sales += quantity;
    }

    public double getPrice() {
        return price;
    }

    public String getNom(){ return this.name; }

    public String getId(){ return this.id; }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public void setSales(int sales) {
        this.sales = sales;
    }
}
