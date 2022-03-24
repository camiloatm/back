package com.store.back.controllers;

import com.similar.products.api.ProductApi;
import com.similar.products.model.ProductDetail;
import com.store.back.handler.ProductHandler;
import org.jboss.logging.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

import java.util.Set;

@RestController
public class ProductApiController implements ProductApi {
    private static final Logger LOGGER = Logger.getLogger(ProductApiController.class);
    private final ProductHandler productHandler;
    
    @Autowired
    public ProductApiController(ProductHandler productHandler) {
        this.productHandler = productHandler;
    }

    @Override
    public ResponseEntity<Set<ProductDetail>> getProductSimilar(String productId) {
        LOGGER.info("Start getProductSimilar");
        this.productHandler.searchSimilarProducts(productId);
        return ProductApi.super.getProductSimilar(productId);
    }
}
