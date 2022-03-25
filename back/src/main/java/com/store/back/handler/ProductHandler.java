package com.store.back.handler;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;

import com.similar.products.model.ProductDetail;
import com.store.back.exception.ProductException;
import com.store.back.services.ProductService;
import org.jboss.logging.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class ProductHandler {

  private static final Logger LOGGER = Logger.getLogger(ProductHandler.class);

  private final ProductService productService;

  @Autowired
  public ProductHandler(ProductService productService) {
    this.productService = productService;
  }

  public Set<ProductDetail> searchSimilarProducts(String productId) throws ProductException {
    LOGGER.info("Buscando productos similares al productId: " + productId);
    Set<ProductDetail> similarProductDetail = new HashSet<>();
    ArrayList<Integer> similarProductsIds = productService.getSimilarProducts(productId);

    for (Integer prodId : similarProductsIds) {
      ProductDetail product = null;
      try {
        LOGGER.info("Buscando producto con id: " + prodId);
        product = productService.getProduct(prodId);
      } catch (ProductException e) {
        LOGGER.error(e.getMessage());
      }
      if (product != null && product.getAvailability()) {
        similarProductDetail.add(product);
      }
    }
    return similarProductDetail;
  }
}
