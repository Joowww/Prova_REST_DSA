package edu.upc.dsa.services;


import edu.upc.dsa.ProductManager;
import edu.upc.dsa.ProductManagerImpl;
import edu.upc.dsa.models.Order;
import edu.upc.dsa.models.Product;
import edu.upc.dsa.models.Track;
import edu.upc.dsa.models.User;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

import javax.ws.rs.*;
import javax.ws.rs.core.GenericEntity;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.List;

@Api(value = "/products", description = "Endpoint to Product Service")
@Path("/products")
public class ProductService {

    private ProductManager tm;

    public ProductService() {
        this.tm = ProductManagerImpl.getInstance();
        if (tm.sizeUsers()==0) {
            this.tm.addProduct("C1", "Coca-cola zero", 2, 2);
            this.tm.addProduct("C2", "Coca-cola", 2.5, 0);
            this.tm.addProduct("B1", "Lomo queso", 3, 7);
            this.tm.addProduct("C1", "bacon queso", 3.5, 4);
            User user1 = new User("123");
            this.tm.addUsuario(user1);
            Order order1 = new Order("123");
            this.tm.addOrder(order1);
            order1.addLP(2, "C1");
            order1.addLP(1, "C2");
        }

    }

    @GET
    @ApiOperation(value = "get all Products", notes = "asdasd")
    @ApiResponses(value = {
            @ApiResponse(code = 201, message = "Successful", response = Product.class, responseContainer="List"),
    })
    @Path("/all/products")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getProducts() {

        List<Product> pm = this.tm.findAll();

        GenericEntity<List<Product>> entity = new GenericEntity<List<Product>>(pm) {};
        return Response.status(201).entity(entity).build()  ;

    }

    @GET
    @ApiOperation(value = "get a Product", notes = "asdasd")
    @ApiResponses(value = {
            @ApiResponse(code = 201, message = "Successful", response = Product.class),
            @ApiResponse(code = 404, message = "Product not found")
    })
    @Path("/{id}/product/obtener")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getProduct(@PathParam("id") String id) {
        Product t = this.tm.getProduct(id);
        if (t == null) return Response.status(404).build();
        else  return Response.status(201).entity(t).build();
    }

    @GET
    @ApiOperation(value = "get all Products ordered by sales", notes = "asdasd")
    @ApiResponses(value = {
            @ApiResponse(code = 201, message = "Successful", response = Product.class, responseContainer="List"),
    })
    @Path("/products/sales")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getProductBySales() {
        List<Product> pm = this.tm.getProductsBySales();
        GenericEntity<List<Product>> entity = new GenericEntity<List<Product>>(pm) {};
        return Response.status(201).entity(entity).build()  ;
    }

    @GET
    @ApiOperation(value = "get all Products ordered by price", notes = "asdasd")
    @ApiResponses(value = {
            @ApiResponse(code = 201, message = "Successful", response = Product.class, responseContainer="List"),
    })
    @Path("/products/price")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getProductByPrice() {
        List<Product> pm = this.tm.getProductsByPrice();
        GenericEntity<List<Product>> entity = new GenericEntity<List<Product>>(pm) {};
        return Response.status(201).entity(entity).build()  ;
    }

    @DELETE
    @ApiOperation(value = "delete a Product", notes = "asdasd")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Successful"),
            @ApiResponse(code = 204, message = "Product not found")
    })
    @Path("/{id}/product/delete")
    public Response deleteProduct(@PathParam("id") String id) {
        Product t = this.tm.getProduct(id);
        if (t == null) return Response.status(404).build();
        else this.tm.deleteProduct(id);
        return Response.status(201).build();
    }

    @PUT
    @ApiOperation(value = "update a Product", notes = "asdasd")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Successful"),
            @ApiResponse(code = 204, message = "Product not found")
    })
    @Path("/update/product")
    public Response updateProduct(Product pm) {
        Product t = this.tm.updateProduct(pm);
        if (t == null) return Response.status(404).build();
        return Response.status(200).build();
    }



    @POST
    @ApiOperation(value = "create a new Product", notes = "asdasd")
    @ApiResponses(value = {
            @ApiResponse(code = 201, message = "Successful", response=Product.class),
            @ApiResponse(code = 500, message = "Validation Error")

    })

    @Path("/producto")
    @Consumes(MediaType.APPLICATION_JSON)
    public Response newProduct( Product pm) {
        if (pm.getNom()==null || pm.getId()==null)  return Response.status(500).entity(pm).build();
        this.tm.addProduct(pm.getId(), pm.getNom(), pm.getPrice(), pm.getSales());
        return Response.status(201).entity(pm).build();
    }

    @POST
    @ApiOperation(value = "create a new User", notes = "asdasd")
    @ApiResponses(value = {
            @ApiResponse(code = 201, message = "Successful", response=Product.class),
            @ApiResponse(code = 500, message = "Validation Error")

    })

    @Path("/user")
    @Consumes(MediaType.APPLICATION_JSON)
    public Response newUser( User pm) {
        if (pm.getDni()==null)  return Response.status(500).entity(pm).build();
        this.tm.addUser(pm.getDni(), pm);
        return Response.status(201).entity(pm).build();
    }

    @POST
    @ApiOperation(value = "create a new Order", notes = "asdasd")
    @ApiResponses(value = {
            @ApiResponse(code = 201, message = "Successful", response=Product.class),
            @ApiResponse(code = 500, message = "Validation Error")

    })

    @Path("/order")
    @Consumes(MediaType.APPLICATION_JSON)
    public Response newOrder( Order o) {
        if (o.getUser()==null)  return Response.status(500).entity(o).build();
        this.tm.addOrder(o);
        return Response.status(201).entity(o).build();
    }

}