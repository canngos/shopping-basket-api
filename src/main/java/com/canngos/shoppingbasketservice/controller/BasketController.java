package com.canngos.shoppingbasketservice.controller;

import com.canngos.shoppingbasketservice.request.BasketRequest;
import com.canngos.shoppingbasketservice.response.BasketResponse;
import com.canngos.shoppingbasketservice.response.DefaultMessageResponse;
import com.canngos.shoppingbasketservice.service.BasketService;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/v1/basket")
@RequiredArgsConstructor
public class BasketController {

    private final BasketService basketService;
    private static final String AUTHORIZATION = "Authorization";

    @PostMapping
    public ResponseEntity<DefaultMessageResponse> addToBasket(@Valid @RequestHeader(AUTHORIZATION) @NotBlank String token, @Valid @RequestBody BasketRequest basketRequest) {
        return new ResponseEntity<>(basketService.addToBasket(token.substring(7), basketRequest), HttpStatus.OK);
    }

    @GetMapping
    public ResponseEntity<BasketResponse> getBasket(@Valid @RequestHeader(AUTHORIZATION) @NotBlank String token) {
        return new ResponseEntity<>(basketService.getBasket(token.substring(7)), HttpStatus.OK);
    }

    @DeleteMapping("/clear")
    public ResponseEntity<DefaultMessageResponse> clearBasket(@Valid @RequestHeader(AUTHORIZATION) @NotBlank String token) {
        return new ResponseEntity<>(basketService.clearBasket(token.substring(7)), HttpStatus.OK);
    }

    @DeleteMapping("/item/{itemId}")
    public ResponseEntity<DefaultMessageResponse> removeItem(@Valid @RequestHeader(AUTHORIZATION) @NotBlank String token, @PathVariable Long itemId) {
        return new ResponseEntity<>(basketService.removeItem(token.substring(7), itemId), HttpStatus.OK);
    }

    @PutMapping("/item/{itemId}/quantity/{quantity}")
    public ResponseEntity<DefaultMessageResponse> updateItemQuantity(@Valid @RequestHeader(AUTHORIZATION) @NotBlank String token, @PathVariable Long itemId, @PathVariable Integer quantity) {
        return new ResponseEntity<>(basketService.updateItemQuantity(token.substring(7), itemId, quantity), HttpStatus.OK);
    }
}
