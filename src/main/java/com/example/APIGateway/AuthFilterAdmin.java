package com.example.APIGateway;

import io.jsonwebtoken.Jwts;
import org.springframework.cloud.gateway.filter.GatewayFilter;
import org.springframework.cloud.gateway.filter.GatewayFilterChain;
import org.springframework.http.HttpStatus;
import org.springframework.http.server.reactive.ServerHttpResponse;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;

@Component
public class AuthFilterAdmin implements GatewayFilter {

    @Override
    public Mono<Void> filter(ServerWebExchange exchange, GatewayFilterChain chain) {
        String token = exchange.getRequest().getCookies().getFirst("JWT").getValue();
        if ((boolean) Jwts.parser().verifyWith(JWTUtil.getSecretKey()).build().parseSignedClaims(token).getPayload().get("ADMIN")) {
            return chain.filter(exchange);
        } else {
            ServerHttpResponse response = exchange.getResponse();
            response.setStatusCode(HttpStatus.FORBIDDEN);
            return response.setComplete();
        }
    }
}
