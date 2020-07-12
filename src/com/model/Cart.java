package com.model;

import java.util.ArrayList;
import java.util.List;

import com.model.bean.ProductBean;

public class Cart {

    private List < ProductBean > products;
    private double total;

    public Cart() {
        products = new ArrayList < ProductBean > ();
        total = 0;
    }

    public void addProduct(ProductBean product) {

        for (ProductBean ordine: products) {
            if (ordine.getCode() == product.getCode()) {
                ordine.setQuantity(ordine.getQuantity() + 1);
                return;
            }
        }

        products.add(product);
    }

    public void deleteProduct(ProductBean product) {
        for (ProductBean prod: products) {
            if (prod.getCode() == product.getCode()) {
                products.remove(prod);
                break;
            }
        }
    }


    public double getTotal() {
        double tot = 0;

        if (!products.isEmpty()) {
            for (ProductBean prod: products) {
                tot += prod.getPrice() * prod.getQuantity();
            }
        }
        return tot;
    }

    public List < ProductBean > getProducts() {
        return products;
    }

    public void removeAll() {
        this.products.clear();
    }

    public void removeImage() {
        for (ProductBean b: products) {
            b.setPhotoBytes(null);
        }
    }
}