package edu.upc.dsa.models;
import edu.upc.dsa.util.RandomUtils;
import java.util.ArrayList;
import java.util.List;

public class User {
    private String dni;
    private List<Order> orderList;

    public User() {

    }
    public User(String dni) {
        this.dni = dni;
        this.orderList = new ArrayList<>();
    }

    public void addOrder(Order order) {
        orderList.add(order);
    }

    public List<Order> orders() {
        return orderList;
    }

    public String getDni() {
        return dni;
    }
}
