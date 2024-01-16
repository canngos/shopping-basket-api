package com.canngos.shoppingbasketservice.service;

import com.canngos.shoppingbasketservice.entity.Basket;
import com.canngos.shoppingbasketservice.entity.Customer;
import com.canngos.shoppingbasketservice.exception.BusinessException;
import com.canngos.shoppingbasketservice.exception.Status;
import com.canngos.shoppingbasketservice.exception.TransactionCode;
import com.canngos.shoppingbasketservice.repository.CustomerRepository;
import com.canngos.shoppingbasketservice.request.LoginRequest;
import com.canngos.shoppingbasketservice.request.RegisterRequest;
import com.canngos.shoppingbasketservice.response.DefaultMessageResponse;
import com.canngos.shoppingbasketservice.response.LoginResponse;
import com.canngos.shoppingbasketservice.response.base.BaseBody;
import com.canngos.shoppingbasketservice.response.body.DefaultMessageBody;
import com.canngos.shoppingbasketservice.response.body.LoginResponseBody;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.BeanUtils;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class AuthServiceImpl implements AuthService {
    private final CustomerRepository customerRepository;
    private final PasswordEncoder passwordEncoder;
    private final AuthenticationManager authenticationManager;
    private final JwtService jwtService;
    @Override
    public LoginResponse login(LoginRequest loginRequest) {
        String email = loginRequest.getEmail();
        String password = loginRequest.getPassword();
        Optional<Customer> customerOptional = customerRepository.findByEmail(email);

        if (customerOptional.isEmpty()) {
            throw new BusinessException(TransactionCode.USER_NOT_FOUND);
        }

        try {
            authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(email, password));
        } catch (BadCredentialsException e) {
            throw new BusinessException(TransactionCode.INVALID_CREDENTIALS);
        }
        Customer customer = customerOptional.get();
        String token = jwtService.generateToken(customer);
        Date expirationDate = jwtService.extractExpiration(token);

        LoginResponse loginResponse = new LoginResponse();
        LoginResponseBody body = new LoginResponseBody();
        body.setToken(token);
        body.setExpirationDate(expirationDate);
        loginResponse.setBody(new BaseBody<>(body));
        loginResponse.setStatus(new Status(TransactionCode.SUCCESS));
        return loginResponse;
    }

    @Override
    public DefaultMessageResponse register(RegisterRequest registerRequest) {
        Optional<Customer> customerOptional = customerRepository.findByEmail(registerRequest.getEmail());
        if (customerOptional.isPresent()) {
            throw new BusinessException(TransactionCode.EMAIL_EXISTS);
        }

        Customer customer = new Customer();
        BeanUtils.copyProperties(registerRequest, customer);
        String encodedPassword = passwordEncoder.encode(registerRequest.getPassword());
        customer.setPassword(encodedPassword);
        Basket basket = new Basket();
        basket.setCustomer(customer);
        customer.setBasket(basket);
        customerRepository.save(customer);

        DefaultMessageResponse response = new DefaultMessageResponse();
        DefaultMessageBody body = new DefaultMessageBody("Customer registered successfully");
        response.setBody(new BaseBody<>(body));
        response.setStatus(new Status(TransactionCode.SUCCESS));
        return response;
    }
}
