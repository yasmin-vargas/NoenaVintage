package com.example.noenavintage_back;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import dataClasses.OrderData;
import dataClasses.ProductData;
import dataModels.Product;
import dataModels.Order;
import java.util.*;
import java.util.ArrayList;

@SpringBootApplication
public class NoenaVintageBackApplication {

    public static void main(String[] args) {
        SpringApplication.run(NoenaVintageBackApplication.class, args);
    }
    OrderData od = new OrderData();    //creates an instance of the OrderData class
    ProductData pd = new ProductData(); //creates an instance of the ProductData class

    //Access the orderList through the getOrderList() method on the OrderData instance "od"
    ArrayList<Order> orderList = od.getOrderList();
    // Iterates through the orderList and prints each order
        for (Order order: orderList){
        System.out.println(order);
    }

    //Access the productList through the getProductList() method on the ProductData instance "pd"
    ArrayList<Product> productList = pd.getProductList();
    // Iterates through the productList and prints each product
        for (Product product: productList){
        System.out.println(product);
    }

}