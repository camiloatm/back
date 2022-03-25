package com.store.back.services.impl;

import java.util.ArrayList;

import com.similar.products.model.ProductDetail;
import com.store.back.exception.ProductException;
import com.store.back.services.ProductService;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class ProductServiceImpl implements ProductService {

  private final RestTemplate restTemplate;

  private static final String BASE_URL = "http://localhost:3001";

  public ProductServiceImpl(RestTemplateBuilder restTemplateBuilder) {
    this.restTemplate = restTemplateBuilder.build();
  }

  @Override
  public ArrayList<Integer> getSimilarProducts(String productId) throws ProductException {
    String url = BASE_URL + "/product/{productId}/similarids";
    try {
      return restTemplate.getForObject(url, ArrayList.class, productId);
    } catch (Exception ex) {
      throw new ProductException(HttpStatus.NOT_FOUND.value(), ex.getMessage());
    }
  }

  @Override
  public ProductDetail getProduct(Integer productId) throws ProductException {
    String url = BASE_URL + "/product/{productId}";
    try {
      return restTemplate.getForObject(url, ProductDetail.class, productId);
    } catch (Exception ex) {
      throw new ProductException(ex.getMessage());
    }
  }
}
