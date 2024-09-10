package com.example.APIGateway.config;


import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class GatewayConfig {
    @Bean
    public RouteLocator customRouteLocator(RouteLocatorBuilder builder) {
        return builder.routes()
                .route(p  -> p
                        .path("/first/**")
                        .uri("http://localhost:8001"))
                .route(p  -> p
                        .path("/aha/**")
                        .uri("http://localhost:8003"))
                .build();
    }
}
