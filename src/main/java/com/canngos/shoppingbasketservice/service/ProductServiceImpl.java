package com.canngos.shoppingbasketservice.service;

import com.canngos.shoppingbasketservice.dto.ProductDto;
import com.canngos.shoppingbasketservice.entity.Product;
import com.canngos.shoppingbasketservice.exception.Status;
import com.canngos.shoppingbasketservice.exception.TransactionCode;
import com.canngos.shoppingbasketservice.repository.ProductRepository;
import com.canngos.shoppingbasketservice.response.ProductResponse;
import com.canngos.shoppingbasketservice.response.base.BaseBody;
import com.canngos.shoppingbasketservice.response.body.ProductResponseBody;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ProductServiceImpl implements ProductService {

    private final ProductRepository productRepository;

    @Override
    public ProductResponse getProducts() {
        List<Product> products = productRepository.findAll();
        List<ProductDto> productDtos = convertProductToDto(products);

        ProductResponse productResponse = new ProductResponse();
        ProductResponseBody body = new ProductResponseBody();
        body.setProducts(productDtos);
        productResponse.setBody(new BaseBody<>(body));
        productResponse.setStatus(new Status(TransactionCode.SUCCESS));
        return productResponse;
    }

    private List<ProductDto> convertProductToDto(List<Product> products) {
        return products.stream()
                .map(product -> ProductDto.builder()
                        .name(product.getName())
                        .price(product.getPrice())
                        .type(product.getType().name())
                        .description(product.getDescription())
                        .quantity(product.getQuantity())
                        .build())
                .toList();
    }
}
