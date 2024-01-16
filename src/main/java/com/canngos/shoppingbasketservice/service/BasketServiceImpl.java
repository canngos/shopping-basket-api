package com.canngos.shoppingbasketservice.service;

import com.canngos.shoppingbasketservice.entity.Basket;
import com.canngos.shoppingbasketservice.entity.BasketItem;
import com.canngos.shoppingbasketservice.entity.Customer;
import com.canngos.shoppingbasketservice.entity.Product;
import com.canngos.shoppingbasketservice.exception.BusinessException;
import com.canngos.shoppingbasketservice.exception.Status;
import com.canngos.shoppingbasketservice.exception.TransactionCode;
import com.canngos.shoppingbasketservice.repository.BasketRepository;
import com.canngos.shoppingbasketservice.repository.CustomerRepository;
import com.canngos.shoppingbasketservice.repository.ProductRepository;
import com.canngos.shoppingbasketservice.request.BasketRequest;
import com.canngos.shoppingbasketservice.response.DefaultMessageResponse;
import com.canngos.shoppingbasketservice.response.base.BaseBody;
import com.canngos.shoppingbasketservice.response.body.DefaultMessageBody;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class BasketServiceImpl implements BasketService{

    private final CustomerRepository customerRepository;
    private final ProductRepository productRepository;
    private final BasketRepository basketRepository;
    private final JwtService jwtService;
    @Override
    public DefaultMessageResponse addToBasket(String token, BasketRequest basketRequest) {
        Customer customer = getCustomer(token);
        Basket basket = customer.getBasket();
        Product product = getProduct(basketRequest.getProductId());
        Integer quantity = basketRequest.getQuantity();

        if (quantity > product.getQuantity()) {
            throw new BusinessException(TransactionCode.NOT_ENOUGH_STOCK);
        }
        product.setQuantity(product.getQuantity() - quantity);

        BigDecimal price = BigDecimal.valueOf(product.getPrice() * quantity);

        basket.getBasketItems().stream()
                .filter(basketItem -> basketItem.getProduct().getId().equals(product.getId()))
                .findFirst()
                .ifPresentOrElse(
                        basketItem -> {
                            basketItem.setQuantity(basketItem.getQuantity() + quantity);
                            basketItem.setPrice(basketItem.getPrice().add(price));
                            basket.setTotalPrice(basket.getTotalPrice().add(price));
                        },
                        () -> {
                            BasketItem basketItem = new BasketItem();
                            basketItem.setBasket(basket);
                            basketItem.setProduct(product);
                            basketItem.setQuantity(quantity);
                            basketItem.setPrice(price);
                            basket.getBasketItems().add(basketItem);
                            basket.setTotalPrice(basket.getTotalPrice().add(price));
                        }
                );

        basketRepository.save(basket);
        productRepository.save(product);

        DefaultMessageResponse defaultMessageResponse = new DefaultMessageResponse();
        DefaultMessageBody body = new DefaultMessageBody("Product added to basket successfully");
        defaultMessageResponse.setBody(new BaseBody<>(body));
        defaultMessageResponse.setStatus(new Status(TransactionCode.SUCCESS));
        return defaultMessageResponse;
    }

    private Product getProduct(Long productId) {
        Optional<Product> productOptional = productRepository.findById(productId);

        if (productOptional.isEmpty()) {
            throw new BusinessException(TransactionCode.PRODUCT_NOT_FOUND);
        }

        return productOptional.get();
    }

    private Customer getCustomer(String token) {
        String customerEmail = jwtService.extractUsername(token);
        Optional<Customer> customerOptional = customerRepository.findByEmail(customerEmail);

        if (customerOptional.isEmpty()) {
            throw new BusinessException(TransactionCode.USER_NOT_FOUND);
        }

        return customerOptional.get();
    }
}
