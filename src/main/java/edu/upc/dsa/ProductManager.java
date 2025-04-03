package edu.upc.dsa;

import edu.upc.dsa.util.RandomUtils;
import edu.upc.dsa.models.Order;
import edu.upc.dsa.models.Product;
import edu.upc.dsa.models.User;

import java.util.List;

public interface ProductManager {

    public void addProduct(String id, String name, double price, int sales);

    public List<Product> getProductsByPrice();

    public void addOrder(Order order);

    public int numOrders();


    public Order deliverOrder();

    Product getProduct(String c1);

    User getUser(String number);

    public List<Product> getProductsBySales();

    public int sizeUsers();

    void addUser(String dni, User pm);

    User addUsuario(User usuario);

    List<Product> findAll();

    void deleteProduct(String id);

    Product updateProduct(Product pm);
}
