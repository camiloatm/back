package com.store.back.services;

import com.similar.products.model.ProductDetail;

import java.util.ArrayList;

public interface ProductService {
    ArrayList<String> getSimilarProducts(String productId);
    ProductDetail getProduct(String productId);
}
