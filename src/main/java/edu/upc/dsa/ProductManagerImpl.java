package edu.upc.dsa;

import edu.upc.dsa.models.Order;
import edu.upc.dsa.models.Product;
import edu.upc.dsa.models.User;
import edu.upc.dsa.services.ProductService;
import edu.upc.dsa.ProductManager;

import edu.upc.dsa.exceptions.*;
import edu.upc.dsa.models.*;
import java.util.*;
import java.util.logging.Logger;

public class ProductManagerImpl implements ProductManager {
    //private static ProductManager instance;
    private List<Product> productList;
    private Queue<Order> orderQueue;
    private HashMap<String, User> users;
    private HashMap<String, Product> productMap;
    private static ProductManagerImpl instance;
    final static Logger logger = Logger.getLogger(ProductManagerImpl.class.getName());


    public static ProductManagerImpl getInstance() {
        if (instance == null) {
            instance = new ProductManagerImpl();
        }
        return instance;
    }

    public ProductManagerImpl() {
        productList = new ArrayList<>();
        orderQueue = new LinkedList<>();
        users = new HashMap<>();
        productMap = new HashMap<>();
    }

    @Override
    public void addProduct(String id, String name, double price, int sales) {
        Product p = new Product(id, name, price, sales);
        productList.add(p);
        productMap.put(id, p);
    }

    @Override
    public List<Product> getProductsByPrice() {
        productList.sort(Comparator.comparingDouble(Product::getPrice).reversed());
        return productList;
    }

    @Override
    public List<Product> getProductsBySales(){
        List<Product> sortedList = new ArrayList<>(productList); // Clonar la lista para no modificar la original
        sortedList.sort((p1, p2) -> Integer.compare(p2.getSales(), p1.getSales()));
        return sortedList;
    }

    @Override
    public int sizeUsers() {
        return users.size();
    }

    @Override
    public void addUser(String dni, User pm) {
        User u = new User(dni);
        users.put(u.getDni(), u);
    }
    public User addUsuario(User usuario){
        logger.info("new usuario " + usuario);
        this.users.put(usuario.getDni(), usuario);
        logger.info("new usuario added");
        return usuario;
    };

    @Override
    public void addOrder(Order order) {
        orderQueue.add(order);
        String userDni = order.getUser().getDni();
        users.putIfAbsent(userDni, new User(userDni));
        User user = users.get(userDni);
        order.setUser(user);
    }

    @Override
    public int numOrders() {
        return orderQueue.size();
    }

    @Override
    public Order deliverOrder() {
        Order order = orderQueue.poll();
        if (order != null) {
            for (Order.LP lp : order.getProducts()) {
                Product product = productMap.get(lp.getProductId());
                if (product != null) {
                    product.increaseSales(lp.getQuantity());
                }
            }
            order.getUser().addOrder(order);
        }
        return order;
    }

    @Override
    public Product getProduct(String c1) {
        return productMap.get(c1);
    }

    @Override
    public User getUser(String dni) {
        return users.get(dni);
    }

    @Override
    public List<Product> findAll() {
        return productList;
    }

    @Override
    public void deleteProduct(String id) {
        for (Product p : productList) {
            if (p.getId().equals(id)){
                productList.remove(p);
            }
        }
    }

    public Product updateProduct(Product pm) {
        for (Product p : productList) {
            if (p.getId().equals(pm.getId())){
                return(p);
            }
        }
        return null;
    }
}
