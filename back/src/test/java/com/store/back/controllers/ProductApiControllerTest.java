package com.store.back.controllers;

import com.similar.products.model.ProductDetail;
import com.store.back.exception.ProductException;
import com.store.back.handler.ProductHandler;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.mock.web.MockHttpServletRequest;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;
import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;

import java.math.BigDecimal;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.http.ResponseEntity;
import org.springframework.mock.web.MockHttpServletRequest;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;
@ExtendWith(MockitoExtension.class)
class ProductApiControllerTest {
    
    @InjectMocks
    ProductApiController productApiController;
    
    @Mock
    ProductHandler productHandler;
    

    @BeforeEach
    void setUp() {
        
    }

    @Test
    void getProductSimilar_OK() throws ProductException {
        String productId = "1";
        MockHttpServletRequest request = new MockHttpServletRequest();
        RequestContextHolder.setRequestAttributes(new ServletRequestAttributes(request));
        ProductDetail productDetail1 = new ProductDetail();
        ProductDetail productDetail2 = new ProductDetail();
        Set<ProductDetail> productDetails =  new HashSet<>();
        
        productDetail1.setId("1");
        productDetail1.setName("Shirt");
        productDetail1.setPrice(new BigDecimal("9.99"));
        productDetail1.setAvailability(true);
        
        productDetail2.setId("2");
        productDetail2.setName("Dress");
        productDetail2.setPrice(new BigDecimal("19.99"));
        productDetail2.setAvailability(true);
        productDetails.add(productDetail1);
        productDetails.add(productDetail2);
        
        when(productHandler.searchSimilarProducts(anyString())).thenReturn(productDetails);
        ResponseEntity<Set<ProductDetail>> responseEntity = productApiController.getProductSimilar(productId);
        assertThat(responseEntity.getStatusCode()).isEqualTo(HttpStatus.OK);
    }
}