package com.canngos.shoppingbasketservice.configuration;

import com.canngos.shoppingbasketservice.exception.BusinessException;
import com.canngos.shoppingbasketservice.service.JwtService;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.jsonwebtoken.Claims;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

@Component
@RequiredArgsConstructor
public class JwtAuthenticationFilter extends OncePerRequestFilter {

    private final JwtService jwtService;
    private final UserDetailsService userDetailsService;
    private final ObjectMapper mapper;

    @Override
    protected void doFilterInternal(
            @NonNull HttpServletRequest request,
            @NonNull HttpServletResponse response,
            @NonNull FilterChain filterChain)
            throws ServletException, IOException {
        Map<String, Object> errorDetails = new HashMap<>();

        try {
            final Optional<Claims> claimsOptional;
            final String email;
            final String token = jwtService.resolveToken(request);

            if (token == null) {
                filterChain.doFilter(request, response);
                return;
            }

            claimsOptional = jwtService.resolveClaims(request);
            if (claimsOptional.isPresent()) {
                Claims claims = claimsOptional.get();

                if (jwtService.validateClaims(claims)) {
                    email = claims.getSubject();
                    UserDetails userDetails = this.userDetailsService.loadUserByUsername(email);
                    Authentication authToken =
                            new UsernamePasswordAuthenticationToken(userDetails, null, userDetails.getAuthorities());

                    SecurityContextHolder.getContext().setAuthentication(authToken);
                }
            }

        } catch (BusinessException e) {
            errorDetails.put("message", "Authentication Error");
            errorDetails.put("details",e.getTransactionCode().getCode());
            response.setStatus(HttpStatus.FORBIDDEN.value());
            response.setContentType(MediaType.APPLICATION_JSON_VALUE);

            mapper.writeValue(response.getWriter(), errorDetails);
        } catch (Exception e) {
            errorDetails.put("message", "Internal Server Error");
            errorDetails.put("details",e.getMessage());
            response.setStatus(HttpStatus.INTERNAL_SERVER_ERROR.value());
            response.setContentType(MediaType.APPLICATION_JSON_VALUE);

            mapper.writeValue(response.getWriter(), errorDetails);
        }

        filterChain.doFilter(request, response);
    }
}
