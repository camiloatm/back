package com.store.back.handler;

import com.similar.products.model.ProductDetail;
import com.store.back.services.ProductService;
import org.jboss.logging.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class ProductHandler {
    private static final Logger LOGGER = Logger.getLogger(ProductHandler.class);
    private final ProductService productService;
    
    @Autowired
    public ProductHandler(ProductService productService) {
        this.productService = productService;
    }
    
    public void searchSimilarProducts(String productId){
        LOGGER.info("Buscando productos similares al productId: "+productId);
        List<ProductDetail> similarProductDetail = new ArrayList<>();
        ArrayList<String> similarProductsIds = productService.getSimilarProducts(productId);
        
        if(similarProductsIds != null){
            similarProductsIds.forEach(s -> {
                
            });
        }
        
    }
}
