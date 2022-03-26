package com.store.back.services;

import com.similar.products.model.ProductDetail;
import com.store.back.exception.ProductException;
import com.store.back.handler.ProductHandler;
import com.store.back.services.ProductService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.mock.web.MockHttpServletRequest;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import static org.assertj.core.api.Assertions.*;
import static org.hamcrest.Matchers.hasItems;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.when;

import java.math.BigDecimal;
import java.util.*;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.http.ResponseEntity;
import org.springframework.mock.web.MockHttpServletRequest;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

@ExtendWith(MockitoExtension.class)
class ProductServiceTest {

    @Mock
    ProductService productService;
    
    String productId = "1";
    
    @Test
    void getSimilarProducts_Found() throws ProductException {
        ArrayList<Integer> similarProducts = new ArrayList<>();
        similarProducts.add(2);
        similarProducts.add(3);
        similarProducts.add(4);
        when(productService.getSimilarProducts(anyString())).thenReturn(similarProducts);
        similarProducts = productService.getSimilarProducts(this.productId);
        assertThat(similarProducts).isNotNull();
    }
    @Test
    void getSimilarProducts_NotFound() throws ProductException {
        ArrayList<Integer> similarProducts = new ArrayList<>();
        similarProducts.add(2);
        similarProducts.add(3);
        similarProducts.add(4);
        when(productService.getSimilarProducts(anyString())).thenThrow(new ProductException(HttpStatus.NOT_FOUND.value(),HttpStatus.NOT_FOUND.getReasonPhrase()));
        ProductException thrown = assertThrows(ProductException.class, () ->  productService.getSimilarProducts(this.productId));
        assertTrue(thrown.getMessage().contains("Not Found"));
        assertEquals(404, thrown.getCode());
    }

    @Test
    void getProduct_Found() throws ProductException {
        ProductDetail productDetail1 = new ProductDetail();

        productDetail1.setId("1");
        productDetail1.setName("Shirt");
        productDetail1.setPrice(new BigDecimal("9.99"));
        productDetail1.setAvailability(true);
        
        when(productService.getProduct(anyInt())).thenReturn(productDetail1);
        ProductDetail productFounded = productService.getProduct(Integer.parseInt(this.productId));
        assertThat(productFounded).isNotNull();
    }
}