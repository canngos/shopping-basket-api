package com.canngos.shoppingbasketservice.service;

import com.canngos.shoppingbasketservice.dto.ProductDto;
import com.canngos.shoppingbasketservice.entity.Product;
import com.canngos.shoppingbasketservice.exception.BusinessException;
import com.canngos.shoppingbasketservice.exception.Status;
import com.canngos.shoppingbasketservice.exception.TransactionCode;
import com.canngos.shoppingbasketservice.repository.ProductRepository;
import com.canngos.shoppingbasketservice.request.ProductRequest;
import com.canngos.shoppingbasketservice.response.DefaultMessageResponse;
import com.canngos.shoppingbasketservice.response.ProductDetailResponse;
import com.canngos.shoppingbasketservice.response.ProductResponse;
import com.canngos.shoppingbasketservice.response.base.BaseBody;
import com.canngos.shoppingbasketservice.response.body.DefaultMessageBody;
import com.canngos.shoppingbasketservice.response.body.ProductDetailBody;
import com.canngos.shoppingbasketservice.response.body.ProductResponseBody;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ProductServiceImpl implements ProductService {

    private final ProductRepository productRepository;

    @Override
    public ProductResponse getProducts() {
        List<Product> products = productRepository.findAll();

        List<ProductDto> productDtoList = convertProductListToDto(products);

        ProductResponse productResponse = new ProductResponse();
        ProductResponseBody body = new ProductResponseBody();
        body.setProducts(productDtoList);
        productResponse.setBody(new BaseBody<>(body));
        productResponse.setStatus(new Status(TransactionCode.SUCCESS));
        return productResponse;
    }

    @Override
    public ProductDetailResponse getProduct(Long id) {
        Optional<Product> productOptional = productRepository.findById(id);
        if (productOptional.isEmpty()) {
            throw new BusinessException(TransactionCode.PRODUCT_NOT_FOUND);
        }
        Product product = productOptional.get();
        ProductDto productDto = new ProductDto();
        BeanUtils.copyProperties(product, productDto);

        ProductDetailResponse productDetailResponse = new ProductDetailResponse();
        ProductDetailBody body = new ProductDetailBody();
        body.setProduct(productDto);
        productDetailResponse.setBody(new BaseBody<>(body));
        productDetailResponse.setStatus(new Status(TransactionCode.SUCCESS));
        return productDetailResponse;
    }

    @Override
    public DefaultMessageResponse addProduct(ProductRequest productRequest) {
        Optional<Product> productOptional = productRepository.findByName(productRequest.getName());
        if (productOptional.isPresent()) {
            throw new BusinessException(TransactionCode.PRODUCT_ALREADY_EXIST);
        }
        Product product = new Product();
        BeanUtils.copyProperties(productRequest, product);
        productRepository.save(product);

        DefaultMessageResponse defaultMessageResponse = new DefaultMessageResponse();
        DefaultMessageBody body = new DefaultMessageBody("Product added successfully with id: " + product.getId());
        defaultMessageResponse.setBody(new BaseBody<>(body));
        defaultMessageResponse.setStatus(new Status(TransactionCode.SUCCESS));
        return defaultMessageResponse;
    }

    @Override
    public DefaultMessageResponse updateProduct(Long id, ProductRequest productRequest) {
        Optional<Product> productOptional = productRepository.findById(id);
        if (productOptional.isEmpty()) {
            throw new BusinessException(TransactionCode.PRODUCT_NOT_FOUND);
        }
        Product product = productOptional.get();
        BeanUtils.copyProperties(productRequest, product);
        productRepository.save(product);

        DefaultMessageResponse defaultMessageResponse = new DefaultMessageResponse();
        DefaultMessageBody body = new DefaultMessageBody("Product updated successfully");
        defaultMessageResponse.setBody(new BaseBody<>(body));
        defaultMessageResponse.setStatus(new Status(TransactionCode.SUCCESS));
        return defaultMessageResponse;
    }

    @Override
    public DefaultMessageResponse deleteProduct(Long id) {
        Optional<Product> productOptional = productRepository.findById(id);
        if (productOptional.isEmpty()) {
            throw new BusinessException(TransactionCode.PRODUCT_NOT_FOUND);
        }
        productRepository.deleteById(id);

        DefaultMessageResponse defaultMessageResponse = new DefaultMessageResponse();
        DefaultMessageBody body = new DefaultMessageBody("Product deleted successfully");
        defaultMessageResponse.setBody(new BaseBody<>(body));
        defaultMessageResponse.setStatus(new Status(TransactionCode.SUCCESS));
        return defaultMessageResponse;
    }

    private List<ProductDto> convertProductListToDto(List<Product> products) {
        return products.stream()
                .map(product -> {
                    ProductDto productDto = new ProductDto();
                    BeanUtils.copyProperties(product, productDto);
                    return productDto;
                })
                .toList();
    }
}
