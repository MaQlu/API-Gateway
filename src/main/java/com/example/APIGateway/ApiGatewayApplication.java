package com.example.APIGateway;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;


@SpringBootApplication
public class ApiGatewayApplication {
	@Autowired
	private AuthFilter authFilter;

	@Autowired
	private AuthFilterAdmin authFilterAdmin;

	public static void main(String[] args) {
		SpringApplication.run(ApiGatewayApplication.class, args);
	}

	@Bean
	public RouteLocator customRouteLocator(RouteLocatorBuilder builder) {
		return builder.routes()

				.route("library-service",r->r
						.path("/library/**")
						.filters(f -> {
							f.filter(authFilterAdmin);
							return f;
						})
						.uri("http://localhost:8001/"))

				.route("test-service",r->r.path("/test/**")
						.uri("http://localhost:8002/"))

				.build();
	}
}
