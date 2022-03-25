package com.store.back.services;

import java.util.ArrayList;

import com.similar.products.model.ProductDetail;
import com.store.back.exception.ProductException;

public interface ProductService {

  ArrayList<Integer> getSimilarProducts(String productId) throws ProductException;

  ProductDetail getProduct(Integer productId) throws ProductException;
}
