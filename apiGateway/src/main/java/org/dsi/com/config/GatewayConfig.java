package org.dsi.com.config;

import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
@Configuration
public class GatewayConfig {

    final AuthenticationFilter authenticationFilter;
    GatewayConfig(AuthenticationFilter authenticationFilter) {
        this.authenticationFilter = authenticationFilter;
    }

    @Bean
    public RouteLocator customRouteLocator(RouteLocatorBuilder builder) {
        return builder.routes()
                .route("question-service", r -> r.path("/question-service/**")
                        .filters(f -> f.filter(authenticationFilter))
                        .uri("lb://question-service"))
                .route("user-service", r -> r.path("/user-service/**")
                        .filters(f -> f.filter(authenticationFilter))
                        .uri("lb://user-service"))
                .route("approval-service", r -> r.path("/approval-service/**")
                        .filters(f -> f.filter(authenticationFilter))
                        .uri("lb://approval-service"))
                .route("discovery-server", r -> r.path("/eureka/web")
                        .filters(f -> f.setPath("/"))
                        .uri("http://localhost:8761"))
                .route("discovery-server-static", r -> r.path("/eureka/**")
                        .uri("http://localhost:8761"))
                .build();
    }
}
